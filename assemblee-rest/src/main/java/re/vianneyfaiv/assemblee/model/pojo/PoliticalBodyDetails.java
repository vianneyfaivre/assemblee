package re.vianneyfaiv.assemblee.model.pojo;

import java.util.Date;
import java.util.List;

public class PoliticalBodyDetails {

    private Date startDate;
    private Date endDate;
    private String politicalBodyLabel;
    private int legislature;
    private List<PoliticalBodyMember> members;

    public PoliticalBodyDetails(Date startDate, Date endDate, String politicalBodyLabel, int legislature, List<PoliticalBodyMember> members) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.politicalBodyLabel = politicalBodyLabel;
        this.legislature = legislature;
        this.members = members;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getPoliticalBodyLabel() {
        return politicalBodyLabel;
    }

    public int getLegislature() {
        return legislature;
    }

    public List<PoliticalBodyMember> getMembers() {
        return members;
    }
}