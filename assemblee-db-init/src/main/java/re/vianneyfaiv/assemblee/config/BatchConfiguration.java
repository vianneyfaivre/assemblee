package re.vianneyfaiv.assemblee.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import re.vianneyfaiv.assemblee.batch.processor.ActeurItemProcessor;
import re.vianneyfaiv.assemblee.batch.processor.MandatItemProcessor;
import re.vianneyfaiv.assemblee.batch.reader.ActeurItemReader;
import re.vianneyfaiv.assemblee.batch.reader.MandatItemReader;
import re.vianneyfaiv.assemblee.model.db.Acteur;
import re.vianneyfaiv.assemblee.model.db.Mandat;
import re.vianneyfaiv.assemblee.model.json.acteur.ActeurJson;
import re.vianneyfaiv.assemblee.model.json.mandat.MandatJson;

import javax.sql.DataSource;
import java.util.Arrays;

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
                .wr
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
    public CompositeItemWriter<Mandat> writerMandats() {
        ItemWriter<Mandat> mandatsWriter =
            new JdbcBatchItemWriterBuilder()
                .sql("INSERT INTO mandats (mandat_id, acteur_id, organe_type, organe_id, date_debut, date_prise_fonction, date_fin, num_place_hemicycle, cause) " +
                        "VALUES (:mandatId, :acteurId, :organeType, :organeId, :dateDebut, :datePriseFonction, :dateFin, :numPlaceHemicycle, :cause)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Acteur>())
                .dataSource(dataSource)
                .build();


        CompositeItemWriter<Mandat> result = new CompositeItemWriter<>();

        result.setDelegates(Arrays.asList(mandatsWriter, mandatsOrganesWriter));

        return result;
    }
}