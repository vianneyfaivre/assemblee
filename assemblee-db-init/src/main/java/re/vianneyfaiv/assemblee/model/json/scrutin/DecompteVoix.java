package re.vianneyfaiv.assemblee.model.json.scrutin;

public class DecompteVoix {

    private int nonVotant;
    private int pour;
    private int contre;
    private int abstention;

    public int getNonVotant() {
        return nonVotant;
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
}
