package re.vianneyfaiv.assemblee.model.json.scrutin;

public class ScrutinGroupe {

    private String organeRef;
    private int nombreMembresGroupe;
    private ScrutinVote vote;

    public String getOrganeRef() {
        return organeRef;
    }

    public void setOrganeRef(String organeRef) {
        this.organeRef = organeRef;
    }

    public int getNombreMembresGroupe() {
        return nombreMembresGroupe;
    }

    public void setNombreMembresGroupe(int nombreMembresGroupe) {
        this.nombreMembresGroupe = nombreMembresGroupe;
    }

    public ScrutinVote getVote() {
        return vote;
    }

    public void setVote(ScrutinVote vote) {
        this.vote = vote;
    }
}
