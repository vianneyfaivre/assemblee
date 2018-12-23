package re.vianneyfaiv.assemblee.model.pojo;

import java.util.Date;

public class PersonVote {

    private String scrutinId;
    private String title;
    private String sessionId;
    private String meetingId;
    private Date voteDate;
    private VoteResult result;
    private String applicant;
    private VoteChoice choice;
    private String choiceCause;
    private String politicalBodyId;
    private String politicalBodyName;

    public String getScrutinId() {
        return scrutinId;
    }

    public void setScrutinId(String scrutinId) {
        this.scrutinId = scrutinId;
    }

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

    public VoteChoice getChoice() {
        return choice;
    }

    public void setChoice(VoteChoice choice) {
        this.choice = choice;
    }

    public String getChoiceCause() {
        return choiceCause;
    }

    public void setChoiceCause(String choiceCause) {
        this.choiceCause = choiceCause;
    }

    public String getPoliticalBodyId() {
        return politicalBodyId;
    }

    public void setPoliticalBodyId(String politicalBodyId) {
        this.politicalBodyId = politicalBodyId;
    }

    public String getPoliticalBodyName() {
        return politicalBodyName;
    }

    public void setPoliticalBodyName(String politicalBodyName) {
        this.politicalBodyName = politicalBodyName;
    }
}
