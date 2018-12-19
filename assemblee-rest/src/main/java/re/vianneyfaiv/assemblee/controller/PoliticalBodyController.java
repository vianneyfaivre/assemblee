package re.vianneyfaiv.assemblee.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import re.vianneyfaiv.assemblee.model.jpa.PoliticalBody;
import re.vianneyfaiv.assemblee.model.pojo.PoliticalBodyDetails;
import re.vianneyfaiv.assemblee.service.PoliticalBodyService;

import java.util.List;

@CrossOrigin("*")
@RestController
public class PoliticalBodyController {

    private PoliticalBodyService service;

    public PoliticalBodyController(PoliticalBodyService service) {
        this.service = service;
    }

    @GetMapping("/political-bodies")
    public List<PoliticalBody> getAllPoliticalBodies() {
        return this.service.findAll();
    }

    @GetMapping("/political-bodies/{politicalBodyId}/members")
    public PoliticalBodyDetails getPoliticalBodyMembers(@PathVariable String politicalBodyId) {
        return this.service.getPoliticalBodyMembers(politicalBodyId);
    }
}
