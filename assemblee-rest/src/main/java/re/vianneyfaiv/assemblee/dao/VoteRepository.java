package re.vianneyfaiv.assemblee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import re.vianneyfaiv.assemblee.model.jpa.Vote;

public interface VoteRepository extends JpaRepository<Vote, String> {
}
