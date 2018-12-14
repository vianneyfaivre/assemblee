package re.vianneyfaiv.assemblee.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.dao.model.GetPersonDetailsRow;
import re.vianneyfaiv.assemblee.model.PersonDetails;

import java.util.List;

@Component
public class PersonDetailsDao {

    private static final String GET_PERSON_DETAILS =
            "select " +
                "m.mandat_id as mandateId, " +
                "m.date_debut as startDate, " +
                "m.date_fin as endDate, " +
                "o.organe_id as politicalBodyId, " +
                "m.organe_type as politicalBodyType, " +
                "o.libelle as politicalBodyLabel " +
            "from " +
                "mandats m " +
                "inner join acteurs a on a.acteur_id = m.acteur_id " +
                "inner join mandats_organes mo on mo.mandat_id = m.mandat_id " +
                "inner join organes o on mo.organe_id = o.organe_id " +
            "where " +
                "a.acteur_id = ? " +
            "order by m.date_debut";

    private JdbcTemplate jdbcTemplate;

    public PersonDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GetPersonDetailsRow> getPersonDetails(String personId) {

        return jdbcTemplate.query(
                    GET_PERSON_DETAILS,
                    new Object[]{personId},
                    BeanPropertyRowMapper.newInstance(GetPersonDetailsRow.class)
        );
    }
}
