package re.vianneyfaiv.assemblee.model.pojo;

import java.util.Date;

public class Mandate {

    private String mandateId;
    private Date startDate;
    private Date endDate;
    private String politicalBodyId;
    private PoliticalBodyType politicalBodyType;
    private String politicalBodyLabel;
    private String legislature;
    private String cause;

    public String getMandateId() {
        return mandateId;
    }

    public void setMandateId(String mandateId) {
        this.mandateId = mandateId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPoliticalBodyId() {
        return politicalBodyId;
    }

    public void setPoliticalBodyId(String politicalBodyId) {
        this.politicalBodyId = politicalBodyId;
    }

    public PoliticalBodyType getPoliticalBodyType() {
        return politicalBodyType;
    }

    public void setPoliticalBodyType(String politicalBodyType) {
        this.politicalBodyType = PoliticalBodyType.fromCode(politicalBodyType);
    }

    public String getPoliticalBodyLabel() {
        return politicalBodyLabel;
    }

    public void setPoliticalBodyLabel(String politicalBodyLabel) {
        this.politicalBodyLabel = politicalBodyLabel;
    }

    public String getLegislature() {
        return legislature;
    }

    public String getCause() {
        return cause;
    }
}
