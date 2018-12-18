package re.vianneyfaiv.assemblee.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.db.Mandat;
import re.vianneyfaiv.assemblee.model.db.MandatOrgane;
import re.vianneyfaiv.assemblee.model.json.mandat.MandatJson;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MandatItemProcessor implements ItemProcessor<MandatJson, Mandat> {

    @Override
    public Mandat process(MandatJson mandatJson) {

        String id = mandatJson.getUid();
        String acteurId = mandatJson.getActeurRef();
        Date dateDebut = mandatJson.getDateDebut();
        Date dateFin = mandatJson.getDateFin();
        String qualite = mandatJson.getInfosQualite().getLibQualiteSex();

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

        return new Mandat(id, acteurId, dateDebut, datePriseFonction, dateFin, numPlaceHemicycle, qualite, cause, organes);
    }
}
