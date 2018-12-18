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
import java.util.EnumSet;
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
        List<Mandate> personMandates = this.dao.getPersonMandates(personId);

        Mandate assembleeMandate = null;
        List<Mandate> politicalMandates = new ArrayList<>();
        List<Mandate> governmentMandates = new ArrayList<>();
        List<Mandate> otherMandates = new ArrayList<>();

        EnumSet<PoliticalBodyType> politicalTypes = EnumSet.of(PoliticalBodyType.GROUPE_POLITIQUE, PoliticalBodyType.PARTI_POLITIQUE);
        EnumSet<PoliticalBodyType> governmentTypes = EnumSet.of(PoliticalBodyType.GOUVERNEMENT, PoliticalBodyType.MINISTERE);

        // Let's group mandates
        for (Mandate mandate : personMandates) {

            PoliticalBodyType type = mandate.getPoliticalBodyType();

            if (PoliticalBodyType.ASSEMBLEE_NATIONALE == type) {
                assembleeMandate = mandate;
            }
            else if(politicalTypes.contains(type)) {
                politicalMandates.add(mandate);
            }
            else if(governmentTypes.contains(type)) {
                governmentMandates.add(mandate);
            } else{
                otherMandates.add(mandate);
            }
        }

        if (assembleeMandate == null || personMandates.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new PersonMandates(assembleeMandate, politicalMandates, governmentMandates, otherMandates));
    }

    public Optional<Person> findById(String personId) {
        return this.repo.findById(personId);
    }

    public List<Person> findAll() {
        return this.repo.findAll(Sort.by("lastName", "firstName"));
    }
}
