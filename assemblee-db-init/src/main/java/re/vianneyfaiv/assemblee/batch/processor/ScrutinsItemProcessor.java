package re.vianneyfaiv.assemblee.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.db.Scrutin;
import re.vianneyfaiv.assemblee.model.json.scrutin.ScrutinJson;

import java.util.Date;

@Component
public class ScrutinsItemProcessor implements ItemProcessor<ScrutinJson, Scrutin> {

    @Override
    public Scrutin process(ScrutinJson scrutinJson) {

        String scrutinId;
        String titre;
        int numero;
        String organeId;
        int legislature;
        String sessionId;
        String seanceId;
        Date dateScrutin;
        String typeVote;
        String sort;
        String demandeur;
        String objet;
        String modePublicationVotes;
        int resultatNombreVotants;
        int resultatPour;
        int resultatContre;
        int resultatAbstention;
        int resultatNonVotant;

        scrutinId = scrutinJson.getUid();
        titre = scrutinJson.getTitre();
        numero = scrutinJson.getNumero();
        organeId = scrutinJson.getOrganeRef();
        legislature = scrutinJson.getLegislature();
        sessionId = scrutinJson.getSessionRef();
        seanceId = scrutinJson.getSeanceRef();
        dateScrutin = scrutinJson.getDateScrutin();
        typeVote = scrutinJson.getTypeVote().getCodeTypeVote();
        sort = scrutinJson.getSort().getCode();
        demandeur = scrutinJson.getDemandeur().getTexte();
        objet = scrutinJson.getObjet().getLibelle();
        modePublicationVotes = scrutinJson.getModePublicationDesVotes();
        resultatNombreVotants = scrutinJson.getSyntheseVote().getNombreVotants();
        resultatPour = scrutinJson.getSyntheseVote().getDecompte().getPour();
        resultatContre = scrutinJson.getSyntheseVote().getDecompte().getContre();
        resultatAbstention = scrutinJson.getSyntheseVote().getDecompte().getAbstentions();
        resultatNonVotant = scrutinJson.getSyntheseVote().getDecompte().getNonVotants();

        return new Scrutin(scrutinId, titre, numero, organeId, legislature, sessionId, seanceId, dateScrutin, typeVote, sort, demandeur, objet, modePublicationVotes, resultatNombreVotants, resultatPour, resultatContre, resultatAbstention, resultatNonVotant);
    }
}
