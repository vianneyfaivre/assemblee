package re.vianneyfaiv.assemblee.model.jpa;

import re.vianneyfaiv.assemblee.dao.VoteModeConverter;
import re.vianneyfaiv.assemblee.dao.VoteResultConverter;
import re.vianneyfaiv.assemblee.model.pojo.VoteMode;
import re.vianneyfaiv.assemblee.model.pojo.VoteResult;
import re.vianneyfaiv.assemblee.model.pojo.VoteType;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "scrutins")
public class Vote {

    @Id
    @Column(name = "scrutinId")
    private String id;

    @Column(name = "titre")
    private String title;

    @Column(name = "numero")
    private int number;

    @Column(name = "organeId")
    private String politicalBodyId;

    private int legislature;

    /* a session contains multiple meetings */
    private String sessionId;

    @Column(name = "seanceId")
    private String meetingId;

    @Column(name = "dateScrutin")
    private Date date;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "typeVote")
    private VoteType type;

    @Convert(converter = VoteResultConverter.class)
    @Column(name = "sort")
    private VoteResult result;

    @Column(name = "demandeur")
    private String applicant;

    @Convert(converter = VoteModeConverter.class)
    @Column(name = "modePublicationVotes")
    private VoteMode mode;

    @Column(name = "resultatNombreVotants")
    private int resultCount;

    @Column(name = "resultatPour")
    private int resultYes;

    @Column(name = "resultatContre")
    private int resultNo;

    private int resultatAbstention;

    @Column(name = "resultatNonVotant")
    private int resultDidNotVote;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

    public String getPoliticalBodyId() {
        return politicalBodyId;
    }

    public int getLegislature() {
        return legislature;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public Date getDate() {
        return date;
    }

    public VoteType getType() {
        return type;
    }

    public VoteResult getResult() {
        return result;
    }

    public String getApplicant() {
        return applicant;
    }

    public VoteMode getMode() {
        return mode;
    }

    public int getResultCount() {
        return resultCount;
    }

    public int getResultYes() {
        return resultYes;
    }

    public int getResultNo() {
        return resultNo;
    }

    public int getResultatAbstention() {
        return resultatAbstention;
    }

    public int getResultDidNotVote() {
        return resultDidNotVote;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", politicalBodyId='" + politicalBodyId + '\'' +
                ", legislature=" + legislature +
                ", sessionId='" + sessionId + '\'' +
                ", meetingId='" + meetingId + '\'' +
                ", date=" + date +
                ", type=" + type +
                ", result=" + result +
                ", applicant='" + applicant + '\'' +
                ", mode=" + mode +
                ", resultCount=" + resultCount +
                ", resultYes=" + resultYes +
                ", resultNo=" + resultNo +
                ", resultatAbstention=" + resultatAbstention +
                ", resultDidNotVote=" + resultDidNotVote +
                '}';
    }
}
