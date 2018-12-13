package re.vianneyfaiv.assemblee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import re.vianneyfaiv.assemblee.model.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String> {

    List<Person> findByLastNameStartingWithOrderByLastName(String lastName);
}
