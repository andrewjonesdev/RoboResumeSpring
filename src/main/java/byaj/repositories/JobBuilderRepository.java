package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */

import byaj.models.JobBuilder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobBuilderRepository extends CrudRepository<JobBuilder, Integer> {
    public List<JobBuilder> findAllByJbRes(int num);
}