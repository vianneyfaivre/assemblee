package re.vianneyfaiv.assemblee.service;

import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.dao.VoteDao;
import re.vianneyfaiv.assemblee.model.pojo.VoteDetailsByGroup;

import java.util.List;

@Component
public class VoteService {

    private VoteDao voteDao;

    public VoteService(VoteDao voteDao) {
        this.voteDao = voteDao;
    }

    public List<VoteDetailsByGroup> getVoteDetailsByGroup(String voteId) {
        return voteDao.getVoteDetailsByGroup(voteId);
    }

}
