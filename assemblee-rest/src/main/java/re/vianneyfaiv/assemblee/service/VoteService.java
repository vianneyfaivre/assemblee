package re.vianneyfaiv.assemblee.service;

import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.dao.VoteDao;
import re.vianneyfaiv.assemblee.model.pojo.VoteOverview;

import java.util.List;

@Component
public class VoteService {

    private VoteDao voteDao;

    public VoteService(VoteDao voteDao) {
        this.voteDao = voteDao;
    }

    public List<VoteOverview> getOverview(String voteId) {
        return voteDao.getVoteOverview(voteId);
    }

}
