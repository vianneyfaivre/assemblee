package re.vianneyfaiv.assemblee.batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import re.vianneyfaiv.assemblee.model.db.ScrutinResultat;

import javax.sql.DataSource;

@Configuration
public class ScrutinResultatsWriter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ItemWriter<ScrutinResultat> writerScrutinResultats() {

        return new JdbcBatchItemWriterBuilder()
                .sql("INSERT INTO scrutins_resultats (scrutin_id, organe_id, position_majoritaire, pour, contre, abstention, non_votant) " +
                        "VALUES (:scrutinId, :organeId, :positionMajoritaire, :pour, :contre, :abstention, :nonVotant)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ScrutinResultat>())
                .dataSource(dataSource)
                .build();
    }
}
