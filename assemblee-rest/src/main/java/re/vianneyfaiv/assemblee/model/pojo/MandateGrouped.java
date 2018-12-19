package re.vianneyfaiv.assemblee.model.pojo;

import java.util.List;

/**
 * List of mandates for a particular person and political body
 */
public class MandateGrouped {

    private String politicalBodyId;
    private String politicalBodyLabel;
    private int legislature;
    private List<Mandate> mandates;

    public MandateGrouped(String politicalBodyId, String politicalBodyLabel, int legislature, List<Mandate> mandates) {
        this.politicalBodyId = politicalBodyId;
        this.politicalBodyLabel = politicalBodyLabel;
        this.legislature = legislature;
        this.mandates = mandates;
    }

    public String getPoliticalBodyId() {
        return politicalBodyId;
    }

    public String getPoliticalBodyLabel() {
        return politicalBodyLabel;
    }

    public int getLegislature() {
        return legislature;
    }

    public List<Mandate> getMandates() {
        return mandates;
    }
}
