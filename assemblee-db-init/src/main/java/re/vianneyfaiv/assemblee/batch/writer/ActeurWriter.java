package re.vianneyfaiv.assemblee.batch.writer;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import re.vianneyfaiv.assemblee.model.db.Acteur;

import javax.sql.DataSource;

@Configuration
public class ActeurWriter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcBatchItemWriter<Acteur> writerActeurs() {
        return new JdbcBatchItemWriterBuilder()
                .sql("INSERT INTO acteurs (acteur_id, civilite, prenom, nom) " +
                        "VALUES (:acteurId, :civilite, :prenom, :nom)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Acteur>())
                .dataSource(dataSource)
                .build();
    }
}
