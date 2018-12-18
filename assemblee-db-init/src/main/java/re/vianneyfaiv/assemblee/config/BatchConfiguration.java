package re.vianneyfaiv.assemblee.config;

import org.springframework.batch.core.ItemWriteListener;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import re.vianneyfaiv.assemblee.batch.processor.ActeurItemProcessor;
import re.vianneyfaiv.assemblee.batch.processor.MandatItemProcessor;
import re.vianneyfaiv.assemblee.batch.processor.OrganeItemProcessor;
import re.vianneyfaiv.assemblee.batch.processor.ScrutinsItemProcessor;
import re.vianneyfaiv.assemblee.batch.reader.ActeurItemReader;
import re.vianneyfaiv.assemblee.batch.reader.MandatItemReader;
import re.vianneyfaiv.assemblee.batch.reader.OrganeItemReader;
import re.vianneyfaiv.assemblee.batch.reader.ScrutinsItemReader;
import re.vianneyfaiv.assemblee.model.db.*;
import re.vianneyfaiv.assemblee.model.json.acteur.ActeurJson;
import re.vianneyfaiv.assemblee.model.json.mandat.MandatJson;
import re.vianneyfaiv.assemblee.model.json.organe.OrganeJson;
import re.vianneyfaiv.assemblee.model.json.scrutin.ScrutinJson;

import javax.sql.DataSource;
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

    @Autowired
    private ScrutinsItemReader readerScrutins;

    @Autowired
    private ScrutinsItemProcessor processorScrutins;

    @Autowired
    private OrganeItemReader readerOrganes;

    @Autowired
    private OrganeItemProcessor processorOrganes;

    @Bean
    public Job importDataJob() {
        return jobBuilderFactory.get("Import-Job")
                .incrementer(new RunIdIncrementer())
                .start(stepActeurs())
                .next(stepMandats())
                .next(stepScrutins())
                .next(stepOrganes())
                .build();
    }

    public Step stepActeurs() {
        return stepBuilderFactory.get("Import-Acteurs")
                .<ActeurJson, Acteur> chunk(50)
                .reader(readerActeurs)
                .processor(processorActeurs)
                .writer(writerActeurs())
                .build();
    }

    public Step stepMandats() {
        return stepBuilderFactory.get("Import-Mandats")
                .<MandatJson, Mandat> chunk(50)
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

    public Step stepScrutins() {
        return stepBuilderFactory.get("Import-Scrutins")
                .<ScrutinJson, Scrutin> chunk(50)
                .reader(readerScrutins)
                .processor(processorScrutins)
                .writer(writerScrutins())
                .build();
    }

    public Step stepOrganes() {
        return stepBuilderFactory.get("Import-Organes")
                .<OrganeJson, Organe> chunk(50)
                .reader(readerOrganes)
                .processor(processorOrganes)
                .writer(writerOrganes())
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
                .sql("INSERT INTO mandats (mandat_id, acteur_id, date_debut, date_prise_fonction, date_fin, num_place_hemicycle, qualite, cause) " +
                        "VALUES (:mandatId, :acteurId, :dateDebut, :datePriseFonction, :dateFin, :numPlaceHemicycle, :qualite, :cause)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Mandat>())
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public ItemWriter<Scrutin> writerScrutins() {
        return new JdbcBatchItemWriterBuilder()
                .sql("INSERT INTO scrutins (scrutin_id, titre, numero, organe_id, legislature, session_id, seance_id, date_scrutin, type_vote, sort, demandeur, mode_publication_votes, resultat_nombre_votants, resultat_pour, resultat_contre, resultat_abstention, resultat_non_votant) " +
                        "VALUES (:scrutinId, :titre, :numero, :organeId, :legislature, :sessionId, :seanceId, :dateScrutin, :typeVote, :sort, :demandeur, :modePublicationVotes, :resultatNombreVotants, :resultatPour, :resultatContre, :resultatAbstention, :resultatNonVotant)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Scrutin>())
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public ItemWriter<Organe> writerOrganes() {
        return new JdbcBatchItemWriterBuilder()
                .sql("INSERT INTO organes (organe_id, type, libelle, date_debut, date_fin, regime, legislature) " +
                        "VALUES (:organeId, :type, :libelle, :dateDebut, :dateFin, :regime, :legislature)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Organe>())
                .dataSource(dataSource)
                .build();
    }
}