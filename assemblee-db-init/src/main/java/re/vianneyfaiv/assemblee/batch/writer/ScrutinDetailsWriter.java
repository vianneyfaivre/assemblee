package re.vianneyfaiv.assemblee.batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import re.vianneyfaiv.assemblee.model.db.ScrutinDetail;

import javax.sql.DataSource;

@Configuration
public class ScrutinDetailsWriter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ItemWriter<ScrutinDetail> writerScrutinDetails() {

        return new JdbcBatchItemWriterBuilder()
                .sql("INSERT INTO scrutins_details (scrutin_id, acteur_id, organe_id, mandat_id, position_scrutin, cause_position_vote) " +
                        "VALUES (:scrutinId, :acteurId, :organeId, :mandatId, :positionScrutin, :causePositionVote)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ScrutinDetail>())
                .dataSource(dataSource)
                .build();
    }
}
