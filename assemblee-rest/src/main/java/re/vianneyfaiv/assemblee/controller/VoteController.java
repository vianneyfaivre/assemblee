package re.vianneyfaiv.assemblee.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import re.vianneyfaiv.assemblee.model.pojo.VoteDetailsByGroup;
import re.vianneyfaiv.assemblee.model.pojo.VoteOverview;
import re.vianneyfaiv.assemblee.service.VoteService;

import java.util.List;

@CrossOrigin("*")
@RestController
public class VoteController {

    private VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/scrutins/{voteId}/details-par-groupe")
    public List<VoteDetailsByGroup> getVoteDetailsByGroup(@PathVariable String voteId) {
        return this.voteService.getVoteDetailsByGroup(voteId);
    }

    @GetMapping("/scrutins/{voteId}/overview")
    public VoteOverview getVoteOverview(@PathVariable String voteId) {
        return this.voteService.getVoteOverview(voteId);
    }
}
