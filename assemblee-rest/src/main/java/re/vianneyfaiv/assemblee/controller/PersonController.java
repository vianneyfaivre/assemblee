package re.vianneyfaiv.assemblee.controller;

import org.springframework.web.bind.annotation.*;
import re.vianneyfaiv.assemblee.exception.PersonNotFound;
import re.vianneyfaiv.assemblee.model.jpa.Person;
import re.vianneyfaiv.assemblee.model.pojo.PersonMandates;
import re.vianneyfaiv.assemblee.service.PersonService;

import java.util.List;

@CrossOrigin("*")
@RestController
public class PersonController {

    private PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return this.service.findAll();
    }

    @GetMapping("/persons/{personId}")
    public Person getPerson(@PathVariable String personId) {
        return this.service
                .findById(personId)
                .orElseThrow(() -> new PersonNotFound("Person with id "+personId+" has not been found"));
    }

    @GetMapping("/persons/{personId}/mandates")
    public PersonMandates getPersonMandates(@PathVariable String personId) {
        return this.service
                .getPersonDetails(personId)
                .orElseThrow(() -> new PersonNotFound("Person with id "+personId+" has not been found or has not any mandates"));
    }

    @GetMapping("/persons/search")
    public List<Person> searchByLastName(@RequestParam String lastName) {
        return this.service.searchByLastName(lastName);
    }
}
