package re.vianneyfaiv.assemblee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import re.vianneyfaiv.assemblee.model.pojo.VoteOverview;
import re.vianneyfaiv.assemblee.service.VoteService;

import java.util.List;

@RestController
public class VoteController {

    private VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/scrutins/{voteId}")
    public List<VoteOverview> getVoteOverview(@PathVariable String voteId) {
        return this.voteService.getOverview(voteId);
    }
}
