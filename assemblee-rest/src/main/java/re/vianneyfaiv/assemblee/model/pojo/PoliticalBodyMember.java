package re.vianneyfaiv.assemblee.model.pojo;

import java.util.List;

public class PoliticalBodyMember {

    private String personId;
    private Gender gender;
    private String firstName;
    private String lastName;
    private List<Mandate> mandates;

    public PoliticalBodyMember(String personId, Gender gender, String firstName, String lastName, List<Mandate> mandates) {
        this.personId = personId;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mandates = mandates;
    }

    public String getPersonId() {
        return personId;
    }

    public Gender getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Mandate> getMandates() {
        return mandates;
    }
}
