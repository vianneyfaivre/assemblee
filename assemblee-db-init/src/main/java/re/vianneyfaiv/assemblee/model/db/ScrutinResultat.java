package re.vianneyfaiv.assemblee.model.db;

public class ScrutinResultat {

    private String scrutinId;
    private String organeId;
    private String positionMajoritaire;
    private int pour;
    private int contre;
    private int abstention;
    private int nonVotant;

    public ScrutinResultat(String scrutinId, String organeId, String positionMajoritaire, int pour, int contre, int abstention, int nonVotant) {
        this.scrutinId = scrutinId;
        this.organeId = organeId;
        this.positionMajoritaire = positionMajoritaire;
        this.pour = pour;
        this.contre = contre;
        this.abstention = abstention;
        this.nonVotant = nonVotant;
    }

    public String getScrutinId() {
        return scrutinId;
    }

    public String getOrganeId() {
        return organeId;
    }

    public String getPositionMajoritaire() {
        return positionMajoritaire;
    }

    public int getPour() {
        return pour;
    }

    public int getContre() {
        return contre;
    }

    public int getAbstention() {
        return abstention;
    }

    public int getNonVotant() {
        return nonVotant;
    }
}
