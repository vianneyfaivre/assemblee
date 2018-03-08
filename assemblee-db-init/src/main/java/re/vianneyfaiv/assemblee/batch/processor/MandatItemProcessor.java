package re.vianneyfaiv.assemblee.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.db.Acteur;
import re.vianneyfaiv.assemblee.model.db.Mandat;
import re.vianneyfaiv.assemblee.model.db.MandatOrgane;
import re.vianneyfaiv.assemblee.model.json.acteur.ActeurJson;
import re.vianneyfaiv.assemblee.model.json.mandat.MandatJson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MandatItemProcessor implements ItemProcessor<MandatJson, Mandat> {

    @Override
    public Mandat process(MandatJson mandatJson) throws Exception {

        String id = mandatJson.getUid();
        String acteurId = mandatJson.getActeurRef();
        String organeType = mandatJson.getTypeOrgane();
        Date dateDebut = mandatJson.getDateDebut();
        Date dateFin = mandatJson.getDateFin();

        Date datePriseFonction = null;
        int numPlaceHemicycle = 0;
        if(mandatJson.getMandature() != null) {
            datePriseFonction = mandatJson.getMandature().getDatePriseFonction();
            numPlaceHemicycle = mandatJson.getMandature().getPlaceHemicycle();
        }

        String cause = null;
        if(mandatJson.getElection() != null) {
            cause = mandatJson.getElection().getCauseMandat();
        }

        List<MandatOrgane> organes = mandatJson.getOrganes()
                                                .getOrganeRef().stream()
                                                .map(organeId -> new MandatOrgane(id, organeId))
                                                .collect(Collectors.toList());

        return new Mandat(id, acteurId, organeType, dateDebut, datePriseFonction, dateFin, numPlaceHemicycle, cause, organes);
    }
}
