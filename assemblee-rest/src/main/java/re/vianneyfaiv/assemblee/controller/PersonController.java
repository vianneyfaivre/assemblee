package re.vianneyfaiv.assemblee.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import re.vianneyfaiv.assemblee.dao.PersonRepository;
import re.vianneyfaiv.assemblee.exception.PersonNotFound;
import re.vianneyfaiv.assemblee.model.Person;
import re.vianneyfaiv.assemblee.model.search.PersonSearch;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
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

    @GetMapping("/persons/{personId}")
    public Person getPerson(@PathVariable String personId) {
        return this.personRepository
                    .findById(personId)
                    .orElseThrow(() -> new PersonNotFound("Person with id "+personId+" has not been found"));
    }

    @GetMapping("/persons/search")
    public List<PersonSearch> searchByLastName(@RequestParam String lastName) {
        return this.personRepository
                    .findByLastNameStartingWithIgnoreCaseOrderByLastName(lastName)
                    .stream()
                    .map(PersonSearch::new)
                    .collect(Collectors.toList());
    }
}
