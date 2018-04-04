package re.vianneyfaiv.assemblee.model.json.scrutin;

public class ScrutinOrgane {

    private String organeRef;
    private ScrutinGroupes groupes;

    public String getOrganeRef() {
        return organeRef;
    }

    public void setOrganeRef(String organeRef) {
        this.organeRef = organeRef;
    }

    public ScrutinGroupes getGroupes() {
        return groupes;
    }

    public void setGroupes(ScrutinGroupes groupes) {
        this.groupes = groupes;
    }
}
