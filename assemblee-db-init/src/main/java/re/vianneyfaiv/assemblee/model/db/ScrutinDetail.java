package re.vianneyfaiv.assemblee.model.db;

import java.util.Optional;

public class ScrutinDetail {

    private String scrutinId;
    private String acteurId;
    private String organeId;
    private String mandatId;
    private ScrutinChoix positionScrutin;
    private String causePositionVote;

    public ScrutinDetail(String scrutinId, String acteurId, String organeId, String mandatId, ScrutinChoix positionScrutin, Optional<String> causePositionVote) {
        this.scrutinId = scrutinId;
        this.acteurId = acteurId;
        this.organeId = organeId;
        this.mandatId = mandatId;
        this.positionScrutin = positionScrutin;
        this.causePositionVote = causePositionVote.orElse(null);
    }

    public String getScrutinId() {
        return scrutinId;
    }

    public String getActeurId() {
        return acteurId;
    }

    public String getOrganeId() {
        return organeId;
    }

    public String getMandatId() {
        return mandatId;
    }

    public String getPositionScrutin() {
        return positionScrutin.name();
    }

    public String getCausePositionVote() {
        return causePositionVote;
    }
}
