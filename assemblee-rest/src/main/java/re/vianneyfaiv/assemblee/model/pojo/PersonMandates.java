package re.vianneyfaiv.assemblee.model.pojo;

import java.util.List;

public class PersonMandates {

    private Mandate mainMandate;
    private List<MandateGrouped> politicalPartyMandates;
    private List<MandateGrouped> politicalGroupMandates;
    private List<MandateGrouped> governmentMandates;
    private List<MandateGrouped> otherMandates;

    public PersonMandates(Mandate mainMandate, List<MandateGrouped> politicalPartyMandates, List<MandateGrouped> politicalGroupMandates, List<MandateGrouped> governmentMandates, List<MandateGrouped> otherMandates) {
        this.mainMandate = mainMandate;
        this.politicalPartyMandates = politicalPartyMandates;
        this.politicalGroupMandates = politicalGroupMandates;
        this.governmentMandates = governmentMandates;
        this.otherMandates = otherMandates;
    }

    public Mandate getMainMandate() {
        return mainMandate;
    }

    public List<MandateGrouped> getPoliticalPartyMandates() {
        return politicalPartyMandates;
    }

    public List<MandateGrouped> getPoliticalGroupMandates() {
        return politicalGroupMandates;
    }

    public List<MandateGrouped> getGovernmentMandates() {
        return governmentMandates;
    }

    public List<MandateGrouped> getOtherMandates() {
        return otherMandates;
    }
}
