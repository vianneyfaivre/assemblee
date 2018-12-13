package re.vianneyfaiv.assemblee.model.search;

import re.vianneyfaiv.assemblee.model.Person;

public class PersonSearch {

    private String id;
    private String fullName;

    public PersonSearch(Person person) {
        this.id = person.getId();
        this.fullName = person.getLastName() + " " + person.getFirstName();
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}
