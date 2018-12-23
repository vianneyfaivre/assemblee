package re.vianneyfaiv.assemblee.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.pojo.PersonVote;

import java.util.List;

@Component
public class VoteDao {
    
    private static final String QUERY_GET_VOTES_BY_PERSON = 
            "select " +
                "s.scrutin_id as scrutinId, " +
                "s.titre as title, " +
                "s.session_id as sessionId, " +
                "s.seance_id as meetingId, " +
                "s.date_scrutin as voteDate, " +
                "s.sort as result, " +
                "s.demandeur as applicant, " +
                "sd.position_scrutin as choice, " +
                "sd.cause_position_vote as choiceCause, " +
                "o.organe_id as politicalBodyId, " +
                "o.libelle as politicalBodyName " +
            "from " +
                "scrutins_details sd " +
                "inner join scrutins s on sd.scrutin_id = s.scrutin_id " +
                "inner join organes o on sd.organe_id = o.organe_id " +
            "where  " +
                "sd.acteur_id = ? " +
            "order by s.numero";

    private JdbcTemplate jdbcTemplate;

    public VoteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PersonVote> getVotesByPerson(String personId) {

        return jdbcTemplate.query(
                QUERY_GET_VOTES_BY_PERSON,
                new Object[]{personId},
                BeanPropertyRowMapper.newInstance(PersonVote.class)
        );
    }
}
