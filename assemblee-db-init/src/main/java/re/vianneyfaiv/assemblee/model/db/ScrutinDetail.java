package re.vianneyfaiv.assemblee.model.db;

public class ScrutinDetail {

    private String scrutinId;
    private String acteurId;
    private String organeId;
    private String mandatId;
    private String positionScrutin;

    public ScrutinDetail(String scrutinId, String acteurId, String organeId, String mandatId, String positionScrutin) {
        this.scrutinId = scrutinId;
        this.acteurId = acteurId;
        this.organeId = organeId;
        this.mandatId = mandatId;
        this.positionScrutin = positionScrutin;
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
        return positionScrutin;
    }
}
