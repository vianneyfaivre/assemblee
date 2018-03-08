package re.vianneyfaiv.assemblee.model.db;

public class MandatOrgane {

    private String mandatId;
    private String organeId;

    public MandatOrgane(String mandatId, String organeId) {
        this.mandatId = mandatId;
        this.organeId = organeId;
    }

    public String getMandatId() {
        return mandatId;
    }

    public String getOrganeId() {
        return organeId;
    }
}
