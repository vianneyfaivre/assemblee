package re.vianneyfaiv.assemblee.model.json.scrutin;

public class DecompteVoix {

    private int nonVotants;
    private int pour;
    private int contre;
    private int abstention;
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

    public int getAbstention() {
        return abstention;
    }

    public int getNonVotantsVolontaires() {
        return nonVotantsVolontaires;
    }
}
