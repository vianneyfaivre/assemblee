package re.vianneyfaiv.assemblee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import re.vianneyfaiv.assemblee.dao.MandateRepository;
import re.vianneyfaiv.assemblee.exception.MandatNotFound;
import re.vianneyfaiv.assemblee.model.jpa.Mandate;

import java.util.List;

@RestController
public class MandateController {

    private MandateRepository mandateRepository;

    public MandateController(MandateRepository mandateRepository) {
        this.mandateRepository = mandateRepository;
    }

    @GetMapping("/mandates")
    public List<Mandate> getAllMandates() {
        return this.mandateRepository.findAll();
    }

    @GetMapping("/mandates/{mandatId}")
    public Mandate getMandatById(@PathVariable String mandatId) {
        return this.mandateRepository
                    .findById(mandatId)
                    .orElseThrow(() -> new MandatNotFound("Mandat with id "+mandatId+" has not been found"));
    }
}
