package re.vianneyfaiv.assemblee.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import re.vianneyfaiv.assemblee.config.JsonMapperConfiguration;
import re.vianneyfaiv.assemblee.model.json.acteur.ActeurWrapper;
import re.vianneyfaiv.assemblee.model.json.mandat.MandatWrapper;
import re.vianneyfaiv.assemblee.model.json.organe.OrganeWrapper;
import re.vianneyfaiv.assemblee.model.json.scrutin.ScrutinGroupe;
import re.vianneyfaiv.assemblee.model.json.scrutin.ScrutinJson;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConvertFromJsonTests {

    ObjectMapper mapper = new JsonMapperConfiguration().jsonMapper();

    @Test
    public void convertActeur() throws IOException {
        ActeurWrapper depute = mapper.readValue(new ClassPathResource("stubs/acteur.json").getInputStream(), ActeurWrapper.class);

        assertThat(depute.getActeur().getEtatCivil().getIdent().getNom(), is("Mélenchon"));
        assertThat(depute.getActeur().getEtatCivil().getIdent().getCiv(), is("M."));
        assertThat(depute.getActeur().getUid().getId(), is("PA2150"));
    }

    @Test
    public void convertMandat() throws IOException {
        MandatWrapper mandat = mapper.readValue(new ClassPathResource("stubs/mandat.json").getInputStream(), MandatWrapper.class);

        assertThat(mandat.getMandat().getActeurRef(), is("PA2150"));
        assertThat(mandat.getMandat().getMandature().getPlaceHemicycle(), is(601));
        assertThat(mandat.getMandat().getOrganes().getOrganeRef(), is(Arrays.asList("PO717460")));
        assertThat(mandat.getMandat().getInfosQualite().getCodeQualite(), is("membre"));
    }

    @Test
    public void convertOrgane() throws IOException {
        OrganeWrapper organe = mapper.readValue(new ClassPathResource("stubs/organe.json").getInputStream(), OrganeWrapper.class);

        assertThat(organe.getOrgane().getUid(), is("PO717460"));
        assertThat(organe.getOrgane().getCodeType(), is("ASSEMBLEE"));
        assertThat(organe.getOrgane().getLegislature(), is(15));
    }

    @Test
    public void convertScrutinDissidents() throws IOException {
        ScrutinJson scrutin = mapper.readValue(new ClassPathResource("stubs/scrutin_dissidents.json").getInputStream(), ScrutinJson.class);

        assertThat(scrutin.getOrganeRef(), is("PO644420"));
        assertThat(scrutin.getTypeVote().getCodeTypeVote(), is("SPO"));
        assertThat(scrutin.getSort().getCode(), is("adopt\u00e9"));
        assertThat(scrutin.getSyntheseVote().getNombreVotants(), is(110));
        assertThat(scrutin.getSyntheseVote().getDecompte().getPour(), is(61));
        assertThat(scrutin.getVentilationVotes().getOrgane().getGroupes().getGroupe().get(0).getNombreMembresGroupe(), is(295));
        assertThat(scrutin.getVentilationVotes().getOrgane().getGroupes().getGroupe().get(0).getVote().getPositionMajoritaire(), is("pour"));
        assertThat(scrutin.getMiseAuPoint().getPour().getVotant().get(0).getActeurRef(), is("PA2051"));
        assertThat(scrutin.getMiseAuPoint().getContre().getVotant().get(0).getMandatRef(), is("PM645474"));
    }

    @Test
    public void convertScrutinNominatifs() throws IOException {
        ScrutinJson scrutin = mapper.readValue(new ClassPathResource("stubs/scrutin_nominatif.json").getInputStream(), ScrutinJson.class);

        // nombreVotants = pour + contre + abstentions
        int totalVotants = scrutin.getSyntheseVote().getNombreVotants();

        // suffragesExprimes = pour + contre
        int totalExprimes = scrutin.getSyntheseVote().getSuffragesExprimes();

        int pourDecompte = scrutin.getSyntheseVote().getDecompte().getPour();
        int contreDecompte = scrutin.getSyntheseVote().getDecompte().getContre();
        int abstentionDecompte = scrutin.getSyntheseVote().getDecompte().getAbstentions();

        assertThat(totalVotants, is(pourDecompte+contreDecompte+abstentionDecompte));
        assertThat(totalExprimes, is(pourDecompte+contreDecompte));

        int pourDecompteVoix = 0;
        int pourDecompteVoixNominatif = 0;
        int contreDecompteVoix = 0;
        int contreDecompteVoixNominatif = 0;
        int abstentionDecompteVoix = 0;
        int abstentionDecompteVoixNominatif = 0;

        // Ventilation
        for(ScrutinGroupe groupe : scrutin.getVentilationVotes().getOrgane().getGroupes().getGroupe()) {

            pourDecompteVoix += groupe.getVote().getDecompteVoix().getPour();
            if(groupe.getVote().getDecompteNominatif().getPour() != null) {
                pourDecompteVoixNominatif += groupe.getVote().getDecompteNominatif().getPour().getVotant().size();
            }

            contreDecompteVoix += groupe.getVote().getDecompteVoix().getContre();
            if(groupe.getVote().getDecompteNominatif().getContre() != null) {
                contreDecompteVoixNominatif += groupe.getVote().getDecompteNominatif().getContre().getVotant().size();
            }

            abstentionDecompteVoix += groupe.getVote().getDecompteVoix().getAbstention();
            if(groupe.getVote().getDecompteNominatif().getAbstentions() != null) {
                abstentionDecompteVoixNominatif += groupe.getVote().getDecompteNominatif().getAbstentions().getVotant().size();
            }
        }

        // Verification
        assertThat(pourDecompteVoix, is(pourDecompte));
        assertThat(pourDecompteVoixNominatif, is(pourDecompte));
        assertThat(contreDecompteVoix, is(contreDecompte));
        assertThat(contreDecompteVoixNominatif, is(contreDecompte));
        assertThat(abstentionDecompteVoix, is(abstentionDecompte));
        assertThat(abstentionDecompteVoixNominatif, is(abstentionDecompte));
    }
}
