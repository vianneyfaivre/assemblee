package re.vianneyfaiv.assemblee.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.pojo.PoliticalBodyMemberRow;

import java.util.List;

@Component
public class PoliticalBodyDao {

    private static final String QUERY_GET_POLITICAL_BODY_MEMBERS =
            "select " +
                    "a.acteur_id as personId, " +
                    "a.civilite as gender, " +
                    "a.prenom as firstName, " +
                    "a.nom as lastName, " +
                    "m.mandat_id as mandateId, " +
                    "m.date_debut as startDate, " +
                    "m.date_fin as endDate, " +
                    "o.organe_id as politicalBodyId, " +
                    "o.\"type\" as politicalBodyType, " +
                    "o.libelle as politicalBodyLabel, " +
                    "o.legislature, " +
                    "m.cause, " +
                    "m.qualite as quality, " +
                    "o.date_debut as politicalBodyStartDate, " +
                    "o.date_fin as politicalBodyEndDate " +
            "from " +
                "mandats m " +
                "inner join acteurs a on a.acteur_id = m.acteur_id " +
                "inner join mandats_organes mo on mo.mandat_id = m.mandat_id " +
                "inner join organes o on mo.organe_id = o.organe_id " +
            "where " +
                "mo.organe_id = ? " +
            "order by a.nom asc";

    private JdbcTemplate jdbcTemplate;

    public PoliticalBodyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PoliticalBodyMemberRow> getPoliticalBodyMembers(String politicalBodyId) {

        return jdbcTemplate.query(
                QUERY_GET_POLITICAL_BODY_MEMBERS,
                new Object[]{politicalBodyId},
                BeanPropertyRowMapper.newInstance(PoliticalBodyMemberRow.class)
        );
    }
}