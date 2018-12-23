package re.vianneyfaiv.assemblee.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.db.Scrutin;
import re.vianneyfaiv.assemblee.model.db.ScrutinChoix;
import re.vianneyfaiv.assemblee.model.db.ScrutinDetail;
import re.vianneyfaiv.assemblee.model.db.ScrutinResultat;
import re.vianneyfaiv.assemblee.model.json.scrutin.ScrutinGroupe;
import re.vianneyfaiv.assemblee.model.json.scrutin.ScrutinJson;
import re.vianneyfaiv.assemblee.model.json.scrutin.VotantRef;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ScrutinsItemProcessor implements ItemProcessor<ScrutinJson, Scrutin> {

    @Override
    public Scrutin process(ScrutinJson scrutinJson) {

        String scrutinId = scrutinJson.getUid();
        String titre = scrutinJson.getTitre();
        int numero = scrutinJson.getNumero();
        String organeId = scrutinJson.getOrganeRef();
        int legislature = scrutinJson.getLegislature();
        String sessionId = scrutinJson.getSessionRef();
        String seanceId = scrutinJson.getSeanceRef();
        Date dateScrutin = scrutinJson.getDateScrutin();
        String typeVote = scrutinJson.getTypeVote().getCodeTypeVote();
        String sort = scrutinJson.getSort().getCode();
        String demandeur = scrutinJson.getDemandeur().getTexte();
        String modePublicationVotes = scrutinJson.getModePublicationDesVotes();
        int resultatNombreVotants = scrutinJson.getSyntheseVote().getNombreVotants();
        int resultatPour = scrutinJson.getSyntheseVote().getDecompte().getPour();
        int resultatContre = scrutinJson.getSyntheseVote().getDecompte().getContre();
        int resultatAbstention = scrutinJson.getSyntheseVote().getDecompte().getAbstentions();
        int resultatNonVotant = scrutinJson.getSyntheseVote().getDecompte().getNonVotants();

        List<ScrutinDetail> scrutinDetails = new ArrayList<>();
        List<ScrutinResultat> scrutinResultats = new ArrayList<>();

        for(ScrutinGroupe groupe : scrutinJson.getVentilationVotes().getOrgane().getGroupes().getGroupe()) {

            String groupeOrganeId = groupe.getOrganeRef();

            if(groupe.getVote().getDecompteNominatif().getPour() != null) {
                scrutinDetails.addAll(extractDetails(scrutinId, groupe.getVote().getDecompteNominatif().getPour().getVotant(), groupeOrganeId, ScrutinChoix.POUR));
            }

            if(groupe.getVote().getDecompteNominatif().getContre() != null) {
                scrutinDetails.addAll(extractDetails(scrutinId, groupe.getVote().getDecompteNominatif().getContre().getVotant(), groupeOrganeId, ScrutinChoix.CONTRE));
            }

            if(groupe.getVote().getDecompteNominatif().getAbstentions() != null) {
                scrutinDetails.addAll(extractDetails(scrutinId, groupe.getVote().getDecompteNominatif().getAbstentions().getVotant(), groupeOrganeId, ScrutinChoix.ABSTENTION));
            }

            if(groupe.getVote().getDecompteNominatif().getNonVotants() != null) {
                scrutinDetails.addAll(extractDetails(scrutinId, groupe.getVote().getDecompteNominatif().getNonVotants().getVotant(), groupeOrganeId, ScrutinChoix.NON_VOTANT));
            }

            String positionMajoritaire = groupe.getVote().getPositionMajoritaire();
            int pour = groupe.getVote().getDecompteVoix().getPour();
            int contre = groupe.getVote().getDecompteVoix().getContre();
            int abstention = groupe.getVote().getDecompteVoix().getAbstention();
            int nonVotant = groupe.getVote().getDecompteVoix().getNonVotant();

            scrutinResultats.add(new ScrutinResultat(scrutinId, groupeOrganeId, positionMajoritaire, pour, contre, abstention, nonVotant));
        }

        return new Scrutin(scrutinId, titre, numero, organeId, legislature, sessionId, seanceId, dateScrutin,
                typeVote, sort, demandeur, modePublicationVotes, resultatNombreVotants, resultatPour, resultatContre,
                resultatAbstention, resultatNonVotant, scrutinDetails, scrutinResultats);
    }

    private List<ScrutinDetail> extractDetails(String scrutinId, List<VotantRef> votants, String groupeOrganeId, ScrutinChoix choix) {
        List<ScrutinDetail> scrutinDetails = new ArrayList<>();

        if(votants != null) {
            for(VotantRef votant : votants) {
                scrutinDetails.add(
                        new ScrutinDetail(scrutinId, votant.getActeurRef(), groupeOrganeId,
                                            votant.getMandatRef(), choix,
                                            Optional.ofNullable(votant.getCausePositionVote())));
            }
        }

        return scrutinDetails;
    }
}
