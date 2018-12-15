package re.vianneyfaiv.assemblee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import re.vianneyfaiv.assemblee.model.jpa.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String> {

    /**
     * Search for people by Last Name that starts with lastName value. Case insensitive.
     */
    List<Person> findByLastNameStartingWithIgnoreCaseOrderByLastName(String lastName);
}
