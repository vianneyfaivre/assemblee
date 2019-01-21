package re.vianneyfaiv.assemblee.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.pojo.PersonVote;
import re.vianneyfaiv.assemblee.model.pojo.VoteDetailsByGroup;
import re.vianneyfaiv.assemblee.model.pojo.VoteOverview;

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
            "order by s.date_scrutin";

    private static final String QUERY_GET_VOTE_DETAILS_BY_GROUP =
            "select " +
                "o.organe_id as politicalBodyId, " +
                "o.libelle as politicalBodyName, " +
                "sr.position_majoritaire as choice, " +
                "sr.pour as numberFor, " +
                "sr.contre as numberAgainst, " +
                "sr.abstention as numberAbstention, " +
                "sr.non_votant as numberNoVote " +
            "from " +
                "assemblee.scrutins_resultats sr " +
                "inner join assemblee.organes o on sr.organe_id = o.organe_id " +
            "where " +
                "sr.scrutin_id = ?";

    private static final String QUERY_GET_VOTE_OVERVIEW =
            "select " +
                "s.titre as title, " +
                "s.session_id as sessionId, " +
                "s.seance_id as meetingId, " +
                "s.date_scrutin as voteDate, " +
                "s.sort as result, " +
                "s.demandeur as applicant, " +
                "s.type_vote as voteType, " +
                "s.mode_publication_votes as votePublicationMode, " +
                "s.resultat_pour as numberFor, " +
                "s.resultat_contre as numberAgainst, " +
                "s.resultat_abstention as numberAbstention, " +
                "s.resultat_non_votant as numberNoVote " +
            "from assemblee.scrutins s " +
            "where s.scrutin_id = ?";

    private JdbcTemplate jdbcTemplate;

    public VoteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<VoteDetailsByGroup> getVoteDetailsByGroup(String voteId) {

        return jdbcTemplate.query(
                QUERY_GET_VOTE_DETAILS_BY_GROUP,
                new Object[]{voteId},
                BeanPropertyRowMapper.newInstance(VoteDetailsByGroup.class)
        );
    }

    public List<PersonVote> getVotesByPerson(String personId) {

        return jdbcTemplate.query(
                QUERY_GET_VOTES_BY_PERSON,
                new Object[]{personId},
                BeanPropertyRowMapper.newInstance(PersonVote.class)
        );
    }

    public VoteOverview getVoteOverview(String voteId) {
        return jdbcTemplate.queryForObject(
                QUERY_GET_VOTE_OVERVIEW,
                new Object[]{voteId},
                BeanPropertyRowMapper.newInstance(VoteOverview.class)
        );
    }

}
