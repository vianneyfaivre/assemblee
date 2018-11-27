package re.vianneyfaiv.assemblee.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import re.vianneyfaiv.assemblee.dao.PersonRepository;
import re.vianneyfaiv.assemblee.model.Person;

import java.util.List;

@RestController
public class PersonController {

    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return this.personRepository.findAll(Sort.by("lastName", "firstName"));
    }

    /*
    TODO:
    - search by full name
    - get by id
     */
}
