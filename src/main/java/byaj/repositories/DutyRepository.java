package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */

import byaj.models.Duty;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DutyRepository extends CrudRepository<Duty, Integer> {
    public List<Duty> findAllByDutyWorkOrderByDutyTitleAsc(int num);
    public List<Duty> findAllByDutyResOrderByDutyWorkAscDutyTitleAsc(int num);
}