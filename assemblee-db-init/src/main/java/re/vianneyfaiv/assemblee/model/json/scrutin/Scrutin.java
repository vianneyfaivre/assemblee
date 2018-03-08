package re.vianneyfaiv.assemblee.model.json.scrutin;

public class Scrutin {

    private String organeRef;
    private int nombreMembresGroupe;
    private Vote vote;

    public String getOrganeRef() {
        return organeRef;
    }

    public int getNombreMembresGroupe() {
        return nombreMembresGroupe;
    }

    public Vote getVote() {
        return vote;
    }
}

