package re.vianneyfaiv.assemblee.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import re.vianneyfaiv.assemblee.dao.PersonRepository;
import re.vianneyfaiv.assemblee.dao.PoliticalBodyRepository;
import re.vianneyfaiv.assemblee.model.Person;
import re.vianneyfaiv.assemblee.model.PoliticalBody;

import java.util.List;

@RestController
public class PoliticalBodyController {

    private PoliticalBodyRepository politicalBodyRepository;

    public PoliticalBodyController(PoliticalBodyRepository politicalBodyRepository) {
        this.politicalBodyRepository = politicalBodyRepository;
    }

    @GetMapping("/political-bodies")
    public List<PoliticalBody> getAllPoliticalBodies() {
        return this.politicalBodyRepository.findAll(Sort.by("label"));
    }

    /*
    TODO:
    - search by label
    - search by date range
    - search by legislature and regime
    - get by id
     */
}
