package re.vianneyfaiv.assemblee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import re.vianneyfaiv.assemblee.dao.VoteRepository;
import re.vianneyfaiv.assemblee.model.jpa.Vote;

import java.util.List;

@RestController
public class VoteController {

    private VoteRepository voteRepository;

    public VoteController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping("/votes")
    public List<Vote> getAllVotes() {
        return this.voteRepository.findAll();
    }

}
