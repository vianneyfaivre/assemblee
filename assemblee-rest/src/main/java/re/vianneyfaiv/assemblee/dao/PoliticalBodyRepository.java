package re.vianneyfaiv.assemblee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import re.vianneyfaiv.assemblee.model.jpa.Person;
import re.vianneyfaiv.assemblee.model.jpa.PoliticalBody;

import java.util.List;

public interface PoliticalBodyRepository extends JpaRepository<PoliticalBody, String> {

    /**
     * Search for political bodies by Name that starts with lastName value.
     * Case insensitive.
     * Ordered by Label and Start Date (asc)
     */
    List<PoliticalBody> findByLabelStartingWithIgnoreCaseOrderByLabelAscStartDateAsc(String label);
}
