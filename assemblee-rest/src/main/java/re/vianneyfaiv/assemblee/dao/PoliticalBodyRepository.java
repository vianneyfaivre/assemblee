package re.vianneyfaiv.assemblee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import re.vianneyfaiv.assemblee.model.PoliticalBody;

public interface PoliticalBodyRepository extends JpaRepository<PoliticalBody, String> {
}
