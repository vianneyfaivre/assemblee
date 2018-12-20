package re.vianneyfaiv.assemblee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.dao.PoliticalBodyDao;
import re.vianneyfaiv.assemblee.dao.PoliticalBodyRepository;
import re.vianneyfaiv.assemblee.exception.NoMembersFound;
import re.vianneyfaiv.assemblee.model.jpa.PoliticalBody;
import re.vianneyfaiv.assemblee.model.pojo.Mandate;
import re.vianneyfaiv.assemblee.model.pojo.PoliticalBodyDetails;
import re.vianneyfaiv.assemblee.model.pojo.PoliticalBodyMember;
import re.vianneyfaiv.assemblee.model.pojo.PoliticalBodyMemberRow;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PoliticalBodyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PoliticalBodyService.class);

    private PoliticalBodyRepository repo;
    private PoliticalBodyDao dao;

    public PoliticalBodyService(PoliticalBodyRepository politicalBodyRepository, PoliticalBodyDao politicalBodyDao) {
        this.repo = politicalBodyRepository;
        this.dao = politicalBodyDao;
    }

    public List<PoliticalBody> findAll() {
        return this.repo.findAll(Sort.by("label"));
    }

    public PoliticalBodyDetails getPoliticalBodyMembers(String politicalBodyId) {
        List<PoliticalBodyMemberRow> rows = this.dao.getPoliticalBodyMembers(politicalBodyId);

        Map<String, List<PoliticalBodyMemberRow>> groupedByPersonId = new HashMap<>();
        Date startDate = null;
        Date endDate = null;

        // Group members who had multiple roles in the same political body member by person ID
        // Also get the min start date and the max end date
        for(PoliticalBodyMemberRow row : rows) {

            List<PoliticalBodyMemberRow> mandates = groupedByPersonId.getOrDefault(row.getPersonId(), new ArrayList<>());
            mandates.add(row);
            groupedByPersonId.put(row.getPersonId(), mandates);

            if(startDate == null) {
                startDate = row.getStartDate();
            } else if(row.getStartDate() != null && startDate.after(row.getStartDate())) {
                startDate = row.getStartDate();
            }

            if(endDate == null) {
                endDate = row.getEndDate();
            } else if(row.getEndDate() != null && endDate.before(row.getEndDate())) {
                endDate = row.getEndDate();
            }
        }

        List<PoliticalBodyMember> members = new ArrayList<>();
        String politicalBodyLabel = "";
        int legislature = 0;

        // Then convert the map to a list
        for(Map.Entry<String, List<PoliticalBodyMemberRow>> person : groupedByPersonId.entrySet()) {

            List<Mandate> mandates = person.getValue().stream().map(Mandate::new).collect(Collectors.toList());
            if(mandates.isEmpty()) {
                LOGGER.warn("The person with id {} has no mandates", person.getKey());
                continue;
            }
            mandates.sort(Comparator.comparing(Mandate::getStartDate));

            PoliticalBodyMemberRow firstMandate = person.getValue().get(0);
            politicalBodyLabel = firstMandate.getPoliticalBodyLabel();
            legislature = firstMandate.getLegislature();

            members.add(new PoliticalBodyMember(person.getKey(), firstMandate.getGender(),
                                                firstMandate.getFirstName(), firstMandate.getLastName(), mandates));
        }

        if(members.isEmpty()) {
            throw new NoMembersFound("No members found for political body "+politicalBodyId);
        }

        // sort members by last name
        members.sort(Comparator.comparing(PoliticalBodyMember::getLastName));

        // Get organe start and end date, if they are null, just take the ones we got from the mandates list
        if(rows.get(0).getStartDate() != null) {
            startDate = rows.get(0).getStartDate();
        }
        if(rows.get(0).getEndDate() != null) {
            endDate = rows.get(0).getEndDate();
        }

        return new PoliticalBodyDetails(startDate, endDate, politicalBodyLabel, legislature, members);
    }

    public List<PoliticalBody> searchByLastName(String name) {
        return this.repo.findFirst50ByLabelStartingWithIgnoreCaseOrderByLabelAscStartDateAsc(name);
    }
}
