package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */

import byaj.models.ResumeBuilder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResumeBuilderRepository extends CrudRepository<ResumeBuilder, Integer> {
    public List<ResumeBuilder> findAllByRbRes(int num);
}