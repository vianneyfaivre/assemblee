package re.vianneyfaiv.assemblee.batch;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
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
    private ItemWriter<Acteur> writerActeurs;

    @Autowired
    private MandatItemReader readerMandats;

    @Autowired
    private MandatItemProcessor processorMandats;

    @Autowired
    private ItemWriter<Mandat> writerMandats;

    @Autowired
    private ItemWriter<MandatOrgane> writerMandatOrgane;

    @Autowired
    private ScrutinsItemReader readerScrutins;

    @Autowired
    private ScrutinsItemProcessor processorScrutins;

    @Autowired
    private ItemWriter<ScrutinDetail> writerScrutinDetails;

    @Autowired
    private ItemWriter<ScrutinResultat> writerScrutinResultats;

    @Autowired
    private ItemWriter<Scrutin> writerScrutins;

    @Autowired
    private OrganeItemReader readerOrganes;

    @Autowired
    private OrganeItemProcessor processorOrganes;

    @Autowired
    private ItemWriter<Organe> writerOrganes;

    @Bean
    public Job importDataJob() {
        return jobBuilderFactory.get("Import-Job")
                .incrementer(new RunIdIncrementer())
                .start(stepActeurs())
                .next(stepOrganes())
                .next(stepMandats())
                .next(stepScrutins())
                .build();
    }

    public Step stepActeurs() {
        return stepBuilderFactory.get("Import-Acteurs")
                .<ActeurJson, Acteur> chunk(100)
                .reader(readerActeurs)
                .processor(processorActeurs)
                .writer(writerActeurs)
                .build();
    }

    public Step stepMandats() {
        return stepBuilderFactory.get("Import-Mandats")
                .<MandatJson, Mandat> chunk(100)
                .reader(readerMandats)
                .processor(processorMandats)
                .writer(writerMandats)
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
                .<ScrutinJson, Scrutin> chunk(100)
                .reader(readerScrutins)
                .processor(processorScrutins)
                .writer(writerScrutins)
                .listener(new ItemWriteListener<Scrutin>() {
                    @Override
                    public void beforeWrite(List<? extends Scrutin> items) {
                    }

                    @Override
                    public void afterWrite(List<? extends Scrutin> items) {
                        List<ScrutinDetail> details = items.stream()
                                .flatMap(m -> m.getScrutinDetails().stream())
                                .collect(Collectors.toList());

                        try {
                            writerScrutinDetails.write(details);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        List<ScrutinResultat> resultats = items.stream()
                                .flatMap(m -> m.getScrutinResultats().stream())
                                .collect(Collectors.toList());

                        try {
                            writerScrutinResultats.write(resultats);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onWriteError(Exception exception, List<? extends Scrutin> items) {
                    }
                })
                .build();
    }

    public Step stepOrganes() {
        return stepBuilderFactory.get("Import-Organes")
                .<OrganeJson, Organe> chunk(100)
                .reader(readerOrganes)
                .processor(processorOrganes)
                .writer(writerOrganes)
                .build();
    }
}