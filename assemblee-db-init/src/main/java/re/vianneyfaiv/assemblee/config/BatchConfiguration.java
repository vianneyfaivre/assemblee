package re.vianneyfaiv.assemblee.config;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import re.vianneyfaiv.assemblee.batch.processor.ActeurItemProcessor;
import re.vianneyfaiv.assemblee.batch.processor.MandatItemProcessor;
import re.vianneyfaiv.assemblee.batch.reader.ActeurItemReader;
import re.vianneyfaiv.assemblee.batch.reader.MandatItemReader;
import re.vianneyfaiv.assemblee.model.db.Acteur;
import re.vianneyfaiv.assemblee.model.db.Mandat;
import re.vianneyfaiv.assemblee.model.db.MandatOrgane;
import re.vianneyfaiv.assemblee.model.json.acteur.ActeurJson;
import re.vianneyfaiv.assemblee.model.json.mandat.MandatJson;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ActeurItemReader readerActeurs;

    @Autowired
    private ActeurItemProcessor processorActeurs;

    @Autowired
    private MandatItemReader readerMandats;

    @Autowired
    private MandatItemProcessor processorMandats;

    @Autowired
    private ItemWriter<MandatOrgane> writerMandatOrgane;

    @Bean
    public Job importActeursJob() {
        return jobBuilderFactory.get("importActeurs")
                .incrementer(new RunIdIncrementer())
                .start(stepActeurs())
                .next(stepMandats())
                .build();
    }

    public Step stepActeurs() {
        return stepBuilderFactory.get("stepActeurs")
                .<ActeurJson, Acteur> chunk(25)
                .reader(readerActeurs)
                .processor(processorActeurs)
                .writer(writerActeurs())
                .build();
    }

    public Step stepMandats() {
        return stepBuilderFactory.get("stepMandats")
                .<MandatJson, Mandat> chunk(25)
                .reader(readerMandats)
                .processor(processorMandats)
                .writer(writerMandats())
                .listener(new ItemWriteListener<Mandat>() {
                    @Override
                    public void beforeWrite(List<? extends Mandat> items) {
                    }

                    @Override
                    public void afterWrite(List<? extends Mandat> items) {

                        List<MandatOrgane> mandatOrganes = items.stream()
                                .flatMap(m -> m.getOrganes().stream())
                                .collect(Collectors.toList());

                        try {
                            writerMandatOrgane.write(mandatOrganes);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onWriteError(Exception exception, List<? extends Mandat> items) {

                    }
                })
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<MandatOrgane> writerMandatOrgane() {
        return new JdbcBatchItemWriterBuilder<MandatOrgane>()
                .dataSource(dataSource)
                .sql("INSERT INTO mandats_organes (mandat_id, organe_id) " +
                        "VALUES (:mandatId, :organeId)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<MandatOrgane>())
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Acteur> writerActeurs() {
        return new JdbcBatchItemWriterBuilder()
                .sql("INSERT INTO acteurs (acteur_id, civilite, prenom, nom) " +
                        "VALUES (:acteurId, :civilite, :prenom, :nom)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Acteur>())
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public ItemWriter<Mandat> writerMandats() {
        return new JdbcBatchItemWriterBuilder()
                        .sql("INSERT INTO mandats (mandat_id, acteur_id, organe_type, date_debut, date_prise_fonction, date_fin, num_place_hemicycle, cause) " +
                                "VALUES (:mandatId, :acteurId, :organeType, :dateDebut, :datePriseFonction, :dateFin, :numPlaceHemicycle, :cause)")
                        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Mandat>())
                        .dataSource(dataSource)
                        .build();
    }
}