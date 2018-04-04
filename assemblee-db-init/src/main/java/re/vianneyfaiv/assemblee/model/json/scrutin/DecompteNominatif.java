package re.vianneyfaiv.assemblee.model.json.scrutin;

public class DecompteNominatif {

    private VotantsWrapper nonVotants;
    private VotantsWrapper pours;
    private VotantsWrapper contres;
    private VotantsWrapper abstentions;

    public VotantsWrapper getNonVotants() {
        return nonVotants;
    }

    public void setNonVotants(VotantsWrapper nonVotants) {
        this.nonVotants = nonVotants;
    }

    public VotantsWrapper getPours() {
        return pours;
    }

    public void setPours(VotantsWrapper pours) {
        this.pours = pours;
    }

    public VotantsWrapper getContres() {
        return contres;
    }

    public void setContres(VotantsWrapper contres) {
        this.contres = contres;
    }

    public VotantsWrapper getAbstentions() {
        return abstentions;
    }

    public void setAbstentions(VotantsWrapper abstentions) {
        this.abstentions = abstentions;
    }
}
