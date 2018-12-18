package re.vianneyfaiv.assemblee.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.pojo.Mandate;

import java.util.List;

@Component
public class PersonDetailsDao {

    private static final String QUERY_GET_PERSON_MANDATES =
            "select " +
                "m.mandat_id as mandateId, " +
                "m.date_debut as startDate, " +
                "m.date_fin as endDate, " +
                "o.organe_id as politicalBodyId, " +
                "o.\"type\" as politicalBodyType, " +
                "o.libelle as politicalBodyLabel, " +
                "o.legislature, " +
                "m.cause " +
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

    public List<Mandate> getPersonMandates(String personId) {

        return jdbcTemplate.query(
                QUERY_GET_PERSON_MANDATES,
                    new Object[]{personId},
                    BeanPropertyRowMapper.newInstance(Mandate.class)
        );
    }
}
