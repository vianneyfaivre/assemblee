package re.vianneyfaiv.assemblee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import re.vianneyfaiv.assemblee.model.Person;

public interface PersonRepository extends JpaRepository<Person, String> {
}
