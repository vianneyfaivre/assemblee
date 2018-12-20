package re.vianneyfaiv.assemblee.model.json.scrutin;

public class DecompteNominatif {

    private VotantsWrapper nonVotants;
    private VotantsWrapper pour;
    private VotantsWrapper contre;
    private VotantsWrapper abstentions;

    public VotantsWrapper getNonVotants() {
        return nonVotants;
    }

    public void setNonVotants(VotantsWrapper nonVotants) {
        this.nonVotants = nonVotants;
    }

    public VotantsWrapper getPour() {
        return pour;
    }

    public void setPour(VotantsWrapper pour) {
        this.pour = pour;
    }

    public VotantsWrapper getContre() {
        return contre;
    }

    public void setContre(VotantsWrapper contre) {
        this.contre = contre;
    }

    public VotantsWrapper getAbstentions() {
        return abstentions;
    }

    public void setAbstentions(VotantsWrapper abstentions) {
        this.abstentions = abstentions;
    }
}
