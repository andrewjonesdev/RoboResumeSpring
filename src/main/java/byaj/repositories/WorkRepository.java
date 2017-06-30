package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */


import byaj.models.Work;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkRepository extends CrudRepository<Work, Integer> {
    public Work findOneByWorkRes(int num);
    public List<Work> findAllByWorkResOrderByWorkEndYearDescWorkEndMonthDesc(int num);
}