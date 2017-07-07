package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */


import byaj.models.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobRepository extends CrudRepository<Job, Integer> {
    public Job findOneByJobRes(int num);
    public Job findTopByJobRes(int num);
    public List<Job> findAllByJobResOrderByJobStartYearDescJobStartMonthDesc(int num);
    public List <Job> findAllByOrderByJobTitleAscJobEmployerAsc();
    public List <Job> findAllByJobTitleOrdeOrderByJobStartYearDescOrderByStartMonthDesc(String jobTitle);
    public List<Job> findAllByJobEmployerOrderByJobResAsc(String employer);
}