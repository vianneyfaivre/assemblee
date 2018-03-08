package re.vianneyfaiv.assemblee.model.json.scrutin;

public class DecompteNominatif {

    private VotantsWrapper nonVotants;
    private VotantsWrapper pours;
    private VotantsWrapper contres;
    private VotantsWrapper abstentions;

    public VotantsWrapper getNonVotants() {
        return nonVotants;
    }

    public VotantsWrapper getPours() {
        return pours;
    }

    public VotantsWrapper getContres() {
        return contres;
    }

    public VotantsWrapper getAbstentions() {
        return abstentions;
    }
}
