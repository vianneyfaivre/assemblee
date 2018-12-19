package re.vianneyfaiv.assemblee.batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import re.vianneyfaiv.assemblee.model.db.Mandat;

import javax.sql.DataSource;

@Configuration
public class MandatWriter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ItemWriter<Mandat> writerMandats() {
        return new JdbcBatchItemWriterBuilder()
                .sql("INSERT INTO mandats (mandat_id, acteur_id, date_debut, date_prise_fonction, date_fin, num_place_hemicycle, qualite, cause) " +
                        "VALUES (:mandatId, :acteurId, :dateDebut, :datePriseFonction, :dateFin, :numPlaceHemicycle, :qualite, :cause)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Mandat>())
                .dataSource(dataSource)
                .build();
    }
}
