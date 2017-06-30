package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */

import byaj.models.Education;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EducationRepository extends CrudRepository<Education, Integer> {
public List<Education> findAllByEduResOrderByEduGradYearDesc(int num);
}