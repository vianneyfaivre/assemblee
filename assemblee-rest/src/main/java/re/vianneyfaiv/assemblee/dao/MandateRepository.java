package re.vianneyfaiv.assemblee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import re.vianneyfaiv.assemblee.model.Mandate;

public interface MandateRepository extends JpaRepository<Mandate, String> {

}
