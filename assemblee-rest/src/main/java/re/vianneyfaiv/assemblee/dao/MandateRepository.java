package re.vianneyfaiv.assemblee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import re.vianneyfaiv.assemblee.model.jpa.Mandate;

public interface MandateRepository extends JpaRepository<Mandate, String> {

}
