package re.vianneyfaiv.assemblee.model.pojo;

import java.util.Date;

public class PoliticalBodyMember {

    private String personId;
    private Gender gender;
    private String firstName;
    private String lastName;
    private String mandateId;
    private Date startDate;
    private Date endDate;
    private String politicalBodyId;
    private PoliticalBodyType politicalBodyType;
    private String politicalBodyLabel;
    private int legislature;
    private String cause;
    private String quality;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(String civilite) {
        this.gender = Gender.fromCivilite(civilite);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public int getLegislature() {
        return legislature;
    }

    public void setLegislature(int legislature) {
        this.legislature = legislature;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
