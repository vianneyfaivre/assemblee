package re.vianneyfaiv.assemblee.model.pojo;

public class VoteDetailsByGroup {

    private String politicalBodyId;
    private String politicalBodyName;
    private VoteChoice choice;
    private int numberFor;
    private int numberAgainst;
    private int numberAbstention;
    private int numberNoVote;

    public void setPoliticalBodyId(String politicalBodyId) {
        this.politicalBodyId = politicalBodyId;
    }

    public void setPoliticalBodyName(String politicalBodyName) {
        this.politicalBodyName = politicalBodyName;
    }

    public void setChoice(String choice) {
        this.choice = VoteChoice.valueOf(choice.toUpperCase());
    }

    public void setNumberFor(int numberFor) {
        this.numberFor = numberFor;
    }

    public void setNumberAgainst(int numberAgainst) {
        this.numberAgainst = numberAgainst;
    }

    public void setNumberAbstention(int numberAbstention) {
        this.numberAbstention = numberAbstention;
    }

    public void setNumberNoVote(int numberNoVote) {
        this.numberNoVote = numberNoVote;
    }

    public String getPoliticalBodyId() {
        return politicalBodyId;
    }

    public String getPoliticalBodyName() {
        return politicalBodyName;
    }

    public VoteChoice getChoice() {
        return choice;
    }

    public int getNumberFor() {
        return numberFor;
    }

    public int getNumberAgainst() {
        return numberAgainst;
    }

    public int getNumberAbstention() {
        return numberAbstention;
    }

    public int getNumberNoVote() {
        return numberNoVote;
    }
}
