package re.vianneyfaiv.assemblee.model.pojo;

import java.util.List;

public class PersonMandates {

    private Mandate mainMandate;
    private List<MandateGrouped> politicalMandates;
    private List<MandateGrouped> governmentMandates;
    private List<MandateGrouped> otherMandates;

    public PersonMandates(Mandate mainMandate, List<MandateGrouped> politicalMandates, List<MandateGrouped> governmentMandates, List<MandateGrouped> otherMandates) {
        this.mainMandate = mainMandate;
        this.politicalMandates = politicalMandates;
        this.governmentMandates = governmentMandates;
        this.otherMandates = otherMandates;
    }

    public Mandate getMainMandate() {
        return mainMandate;
    }

    public List<MandateGrouped> getPoliticalMandates() {
        return politicalMandates;
    }

    public List<MandateGrouped> getGovernmentMandates() {
        return governmentMandates;
    }

    public List<MandateGrouped> getOtherMandates() {
        return otherMandates;
    }

    @Override
    public String toString() {
        return "PersonMandates{" +
                "mainMandate=" + mainMandate +
                ", politicalMandates=" + politicalMandates +
                ", governmentMandates=" + governmentMandates +
                ", otherMandates=" + otherMandates +
                '}';
    }
}
