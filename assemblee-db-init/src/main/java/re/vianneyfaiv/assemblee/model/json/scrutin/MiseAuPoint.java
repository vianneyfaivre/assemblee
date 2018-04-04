package re.vianneyfaiv.assemblee.model.json.scrutin;

public class MiseAuPoint {

    private VotantsWrapper nonVotants;
    private VotantsWrapper pour;
    private VotantsWrapper contre;
    private VotantsWrapper abstentions;
    private VotantsWrapper nonVotantsVolontaires;

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

    public VotantsWrapper getNonVotantsVolontaires() {
        return nonVotantsVolontaires;
    }

    public void setNonVotantsVolontaires(VotantsWrapper nonVotantsVolontaires) {
        this.nonVotantsVolontaires = nonVotantsVolontaires;
    }
}
