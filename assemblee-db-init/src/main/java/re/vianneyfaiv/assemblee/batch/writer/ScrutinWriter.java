package re.vianneyfaiv.assemblee.batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import re.vianneyfaiv.assemblee.model.db.Scrutin;

import javax.sql.DataSource;

@Configuration
public class ScrutinWriter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ItemWriter<Scrutin> writerScrutins() {
        return new JdbcBatchItemWriterBuilder()
                .sql("INSERT INTO scrutins (scrutin_id, titre, numero, organe_id, legislature, session_id, seance_id, date_scrutin, type_vote, sort, demandeur, mode_publication_votes, resultat_nombre_votants, resultat_pour, resultat_contre, resultat_abstention, resultat_non_votant) " +
                        "VALUES (:scrutinId, :titre, :numero, :organeId, :legislature, :sessionId, :seanceId, :dateScrutin, :typeVote, :sort, :demandeur, :modePublicationVotes, :resultatNombreVotants, :resultatPour, :resultatContre, :resultatAbstention, :resultatNonVotant)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Scrutin>())
                .dataSource(dataSource)
                .build();
    }
}
