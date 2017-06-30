package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */

import byaj.models.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SkillRepository extends CrudRepository<Skill, Integer> {
    public List<Skill> findAllBySkillResOrderBySkillNameAsc(int num);
}