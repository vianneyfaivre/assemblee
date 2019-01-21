package re.vianneyfaiv.assemblee.model.pojo;

import java.util.Date;

public class VoteOverview {

    private String title;
    private String sessionId;
    private String meetingId;
    private Date voteDate;
    private VoteResult result;
    private String applicant;
    private VoteType voteType;
    private VoteMode votePublicationMode;
    private int numberFor;
    private int numberAgainst;
    private int numberAbstention;
    private int numberNoVote;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public Date getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }

    public VoteResult getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = VoteResult.fromCode(result);
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public VoteMode getVotePublicationMode() {
        return votePublicationMode;
    }

    public void setVotePublicationMode(String votePublicationMode) {
        this.votePublicationMode = VoteMode.fromCode(votePublicationMode);
    }

    public int getNumberFor() {
        return numberFor;
    }

    public void setNumberFor(int numberFor) {
        this.numberFor = numberFor;
    }

    public int getNumberAgainst() {
        return numberAgainst;
    }

    public void setNumberAgainst(int numberAgainst) {
        this.numberAgainst = numberAgainst;
    }

    public int getNumberAbstention() {
        return numberAbstention;
    }

    public void setNumberAbstention(int numberAbstention) {
        this.numberAbstention = numberAbstention;
    }

    public int getNumberNoVote() {
        return numberNoVote;
    }

    public void setNumberNoVote(int numberNoVote) {
        this.numberNoVote = numberNoVote;
    }
}
