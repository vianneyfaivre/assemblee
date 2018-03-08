package re.vianneyfaiv.assemblee.model.json.scrutin;

public class DecompteVoix {

    private int nonVotants;
    private int pour;
    private int contre;
    private int abstentions;
    private int nonVotantsVolontaires;

    public int getNonVotants() {
        return nonVotants;
    }

    public int getPour() {
        return pour;
    }

    public int getContre() {
        return contre;
    }

    public int getAbstentions() {
        return abstentions;
    }

    public int getNonVotantsVolontaires() {
        return nonVotantsVolontaires;
    }
}
