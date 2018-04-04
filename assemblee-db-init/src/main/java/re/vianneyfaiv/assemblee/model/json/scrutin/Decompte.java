package re.vianneyfaiv.assemblee.model.json.scrutin;

public class Decompte {

    private int pour;
    private int contre;
    private int abstentions;
    private int nonVotants;

    public int getPour() {
        return pour;
    }

    public void setPour(int pour) {
        this.pour = pour;
    }

    public int getContre() {
        return contre;
    }

    public void setContre(int contre) {
        this.contre = contre;
    }

    public int getAbstentions() {
        return abstentions;
    }

    public void setAbstentions(int abstentions) {
        this.abstentions = abstentions;
    }

    public int getNonVotants() {
        return nonVotants;
    }

    public void setNonVotants(int nonVotants) {
        this.nonVotants = nonVotants;
    }
}
