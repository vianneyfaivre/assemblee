package re.vianneyfaiv.assemblee.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.db.Acteur;
import re.vianneyfaiv.assemblee.model.json.acteur.ActeurJson;

@Component
public class ActeurItemProcessor implements ItemProcessor<ActeurJson, Acteur> {

    @Override
    public Acteur process(ActeurJson acteurJson) throws Exception {

        String id = acteurJson.getUid().getId();
        String civilite = acteurJson.getEtatCivil().getIdent().getCiv();
        String nom = acteurJson.getEtatCivil().getIdent().getPrenom();
        String prenom = acteurJson.getEtatCivil().getIdent().getNom();

        return new Acteur(id, civilite, prenom, nom);
    }
}
