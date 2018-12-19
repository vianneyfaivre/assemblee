package re.vianneyfaiv.assemblee.batch.writer;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import re.vianneyfaiv.assemblee.model.db.MandatOrgane;

import javax.sql.DataSource;

@Configuration
public class MandatOrganeWriter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcBatchItemWriter<MandatOrgane> writerMandatOrgane() {
        return new JdbcBatchItemWriterBuilder<MandatOrgane>()
                .dataSource(dataSource)
                .sql("INSERT INTO mandats_organes (mandat_id, organe_id) " +
                        "VALUES (:mandatId, :organeId)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<MandatOrgane>())
                .build();
    }
}
