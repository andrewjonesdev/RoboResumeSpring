package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */

import byaj.models.Duty;
import org.springframework.data.repository.CrudRepository;

public interface DutyRepository extends CrudRepository<Duty, Integer> {

}