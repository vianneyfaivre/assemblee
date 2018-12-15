package re.vianneyfaiv.assemblee.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.dao.PersonDetailsDao;
import re.vianneyfaiv.assemblee.dao.PersonRepository;
import re.vianneyfaiv.assemblee.model.pojo.Mandate;
import re.vianneyfaiv.assemblee.model.jpa.Person;
import re.vianneyfaiv.assemblee.model.pojo.PersonMandates;
import re.vianneyfaiv.assemblee.model.pojo.PoliticalBodyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonService {

    private PersonRepository repo;
    private PersonDetailsDao dao;

    public PersonService(PersonRepository repo, PersonDetailsDao dao) {
        this.repo = repo;
        this.dao = dao;
    }

    public List<Person> searchByLastName(String lastName) {
        return this.repo.findByLastNameStartingWithIgnoreCaseOrderByLastName(lastName);
    }

    public Optional<PersonMandates> getPersonDetails(String personId) {
        List<Mandate> personDetails = this.dao.getPersonDetails(personId);

        Mandate assembleeMandate = null;
        List<Mandate> otherMandates = new ArrayList<>();

        for (Mandate detail : personDetails) {

            if (PoliticalBodyType.ASSEMBLEE_NATIONALE.getCode().equalsIgnoreCase(detail.getPoliticalBodyType())) {
                assembleeMandate = detail;
            } else {
                otherMandates.add(detail);
            }
        }

        if (assembleeMandate == null || personDetails.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new PersonMandates(assembleeMandate, otherMandates));
    }

    public Optional<Person> findById(String personId) {
        return this.repo.findById(personId);
    }

    public List<Person> findAll() {
        return this.repo.findAll(Sort.by("lastName", "firstName"));
    }
}
