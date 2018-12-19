package re.vianneyfaiv.assemblee.batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import re.vianneyfaiv.assemblee.model.db.Organe;

import javax.sql.DataSource;

@Configuration
public class OrganeWriter {

    @Autowired
    private DataSource dataSource;

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
