package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */

import byaj.models.Work;
import org.springframework.data.repository.CrudRepository;

public interface WorkRepository extends CrudRepository<Work, Integer> {

}