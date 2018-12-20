package re.vianneyfaiv.assemblee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.dao.PersonDetailsDao;
import re.vianneyfaiv.assemblee.dao.PersonRepository;
import re.vianneyfaiv.assemblee.model.jpa.Person;
import re.vianneyfaiv.assemblee.model.pojo.Mandate;
import re.vianneyfaiv.assemblee.model.pojo.MandateGrouped;
import re.vianneyfaiv.assemblee.model.pojo.PersonMandates;
import re.vianneyfaiv.assemblee.model.pojo.PoliticalBodyType;

import java.util.*;

@Component
public class PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

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
        List<Mandate> politicalPartyMandates = new ArrayList<>();
        List<Mandate> politicalGroupMandates = new ArrayList<>();
        List<Mandate> governmentMandates = new ArrayList<>();
        List<Mandate> otherMandates = new ArrayList<>();

        EnumSet<PoliticalBodyType> governmentTypes = EnumSet.of(PoliticalBodyType.GOUVERNEMENT, PoliticalBodyType.MINISTERE);

        // Sort mandates based on their type
        for (Mandate mandate : personMandates) {

            PoliticalBodyType type = mandate.getPoliticalBodyType();

            if (PoliticalBodyType.ASSEMBLEE_NATIONALE == type) {
                assembleeMandate = mandate;
            }
            else if(PoliticalBodyType.PARTI_POLITIQUE == type) {
                politicalPartyMandates.add(mandate);
            }
            else if(PoliticalBodyType.GROUPE_POLITIQUE == type) {
                politicalGroupMandates.add(mandate);
            }
            else if(governmentTypes.contains(type)) {
                governmentMandates.add(mandate);
            } else{
                otherMandates.add(mandate);
            }
        }

        // If no assemblée mandate has been found or if that person does not have any mandates, return nothing
        if (assembleeMandate == null || personMandates.isEmpty()) {
            return Optional.empty();
        }

        // Group all mandates list by political body (because the same person may have had multiple roles in the same political body)
        List<MandateGrouped> politicalPartyMandatesGrouped = groupByPoliticalBody(personId, politicalPartyMandates);
        List<MandateGrouped> politicalGroupMandatesGrouped = groupByPoliticalBody(personId, politicalGroupMandates);
        List<MandateGrouped> governmentMandatesGrouped = groupByPoliticalBody(personId, governmentMandates);
        List<MandateGrouped> otherMandatesGrouped = groupByPoliticalBody(personId, otherMandates);

        return Optional.of(new PersonMandates(assembleeMandate, politicalPartyMandatesGrouped, politicalGroupMandatesGrouped, governmentMandatesGrouped, otherMandatesGrouped));
    }

    public Optional<Person> findById(String personId) {
        return this.repo.findById(personId);
    }

    public List<Person> findAll() {
        return this.repo.findAll(Sort.by("lastName", "firstName"));
    }

    private List<MandateGrouped> groupByPoliticalBody(String personId, List<Mandate> allMandates) {
        // Group members who had multiple roles in the same political body member by political body ID
        Map<String, List<Mandate>> groupedByPoliticalBodyId = new HashMap<>();
        for(Mandate mandate : allMandates) {

            List<Mandate> mandates = groupedByPoliticalBodyId.getOrDefault(mandate.getPoliticalBodyId(), new ArrayList<>());
            mandates.add(mandate);
            groupedByPoliticalBodyId.put(mandate.getPoliticalBodyId(), mandates);
        }

        List<MandateGrouped> mandatesGrouped = new ArrayList<>();

        // Then convert the map to a list
        for(Map.Entry<String, List<Mandate>> politicalBody : groupedByPoliticalBodyId.entrySet()) {

            List<Mandate> mandates = politicalBody.getValue();
            if(mandates.isEmpty()) {
                LOGGER.warn("The person with id {} has no mandates for the political body with id {}", personId, politicalBody.getKey());
                continue;
            }

            mandates.sort(Comparator.comparing(Mandate::getStartDate));

            String politicalBodyId = politicalBody.getKey();
            String politicalBodyLabel = mandates.get(0).getPoliticalBodyLabel();
            int legislature = mandates.get(0).getLegislature();

            mandatesGrouped.add(new MandateGrouped(politicalBodyId, politicalBodyLabel, legislature, mandates));
        }

        // Sort all mandates grouped, by the start date of the very first mandate
        mandatesGrouped.sort((o1, o2) -> {
            Date minDate1 = o1.getMandates().get(0).getStartDate();
            Date minDate2 = o2.getMandates().get(0).getStartDate();

            return minDate1.compareTo(minDate2);
        });

        return mandatesGrouped;
    }
}
