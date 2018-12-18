package re.vianneyfaiv.assemblee.model.pojo;

import java.util.List;

public class PersonMandates {

    private Mandate mainMandate;
    private List<Mandate> politicalMandates;
    private List<Mandate> governmentMandates;
    private List<Mandate> otherMandates;

    public PersonMandates(Mandate mainMandate, List<Mandate> politicalMandates, List<Mandate> governmentMandates, List<Mandate> otherMandates) {
        this.mainMandate = mainMandate;
        this.politicalMandates = politicalMandates;
        this.governmentMandates = governmentMandates;
        this.otherMandates = otherMandates;
    }

    public Mandate getMainMandate() {
        return mainMandate;
    }

    public List<Mandate> getPoliticalMandates() {
        return politicalMandates;
    }

    public List<Mandate> getGovernmentMandates() {
        return governmentMandates;
    }

    public List<Mandate> getOtherMandates() {
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
