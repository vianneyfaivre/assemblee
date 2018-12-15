package re.vianneyfaiv.assemblee.model.pojo;

import java.util.List;

public class PersonMandates {

    private Mandate mainMandate;
    private List<Mandate> otherMandates;

    public PersonMandates(Mandate mainMandate, List<Mandate> otherMandates) {
        this.mainMandate = mainMandate;
        this.otherMandates = otherMandates;
    }

    public Mandate getMainMandate() {
        return mainMandate;
    }

    public List<Mandate> getOtherMandates() {
        return otherMandates;
    }

    @Override
    public String toString() {
        return "PersonMandates{" +
                "mainMandate=" + mainMandate +
                ", otherMandates=" + otherMandates +
                '}';
    }
}
