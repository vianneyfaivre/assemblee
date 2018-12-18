package re.vianneyfaiv.assemblee.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import re.vianneyfaiv.assemblee.dao.PoliticalBodyDao;
import re.vianneyfaiv.assemblee.dao.PoliticalBodyRepository;
import re.vianneyfaiv.assemblee.exception.PersonNotFound;
import re.vianneyfaiv.assemblee.model.jpa.Person;
import re.vianneyfaiv.assemblee.model.jpa.PoliticalBody;
import re.vianneyfaiv.assemblee.model.pojo.PoliticalBodyMember;

import java.util.List;

@CrossOrigin("*")
@RestController
public class PoliticalBodyController {

    private PoliticalBodyRepository politicalBodyRepository;
    private PoliticalBodyDao politicalBodyDao;

    public PoliticalBodyController(PoliticalBodyRepository politicalBodyRepository, PoliticalBodyDao politicalBodyDao) {
        this.politicalBodyRepository = politicalBodyRepository;
        this.politicalBodyDao = politicalBodyDao;
    }

    @GetMapping("/political-bodies")
    public List<PoliticalBody> getAllPoliticalBodies() {
        return this.politicalBodyRepository.findAll(Sort.by("label"));
    }

    @GetMapping("/political-bodies/{politicalBodyId}/members")
    public List<PoliticalBodyMember> getPoliticalBodyMembers(@PathVariable String politicalBodyId) {
        return this.politicalBodyDao.getPoliticalBodyMembers(politicalBodyId);
    }

    /*
    TODO:
    - search by label
    - search by date range
    - search by legislature and regime
     */
}
