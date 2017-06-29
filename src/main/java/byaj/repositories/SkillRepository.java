package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */

import byaj.models.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Integer> {

}