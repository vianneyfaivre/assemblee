package re.vianneyfaiv.assemblee.model;

import re.vianneyfaiv.assemblee.dao.GenderConverter;

import javax.persistence.*;

@Entity(name = "acteurs")
public class Person {

    @Id
    @Column(name = "acteur_id")
    private String id;

    @Convert(converter = GenderConverter.class)
    @Column(name = "civilite")
    private Gender gender;

    @Column(name = "prenom")
    private String firstName;

    @Column(name = "nom")
    private String lastName;

    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", gender=" + gender +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
