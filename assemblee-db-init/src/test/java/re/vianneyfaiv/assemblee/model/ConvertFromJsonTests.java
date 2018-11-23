package re.vianneyfaiv.assemblee.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import re.vianneyfaiv.assemblee.config.JsonMapperConfiguration;
import re.vianneyfaiv.assemblee.model.json.acteur.ActeurWrapper;
import re.vianneyfaiv.assemblee.model.json.mandat.MandatWrapper;
import re.vianneyfaiv.assemblee.model.json.organe.OrganeWrapper;
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
    }

    @Test
    public void convertOrgane() throws IOException {
        OrganeWrapper organe = mapper.readValue(new ClassPathResource("stubs/organe.json").getInputStream(), OrganeWrapper.class);

        assertThat(organe.getOrgane().getUid(), is("PO717460"));
    }

    @Test
    public void convertScrutin() throws IOException {
        ScrutinJson scrutin = mapper.readValue(new ClassPathResource("stubs/scrutin.json").getInputStream(), ScrutinJson.class);

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
}
