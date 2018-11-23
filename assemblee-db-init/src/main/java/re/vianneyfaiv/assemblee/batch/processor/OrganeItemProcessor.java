package re.vianneyfaiv.assemblee.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.db.Acteur;
import re.vianneyfaiv.assemblee.model.db.Organe;
import re.vianneyfaiv.assemblee.model.json.acteur.ActeurJson;
import re.vianneyfaiv.assemblee.model.json.organe.OrganeJson;

import java.util.Date;

@Component
public class OrganeItemProcessor implements ItemProcessor<OrganeJson, Organe> {

    @Override
    public Organe process(OrganeJson organeJson) {

        String organeId = organeJson.getUid();
        String type = organeJson.getCodeType();
        String libelle = organeJson.getLibelle();
        Date dateDebut = organeJson.getViMoDe().getDateDebut();
        Date dateFin = organeJson.getViMoDe().getDateFin();
        String regime = organeJson.getRegime();
        int legislature = organeJson.getLegislature();

        return new Organe(organeId, type, libelle, dateDebut, dateFin, regime, legislature);
    }
}
