package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */

import byaj.models.Resume;
import org.springframework.data.repository.CrudRepository;

public interface ResumeRepository extends CrudRepository<Resume, Integer> {
    public Resume findOneByResAcc(int num);
}