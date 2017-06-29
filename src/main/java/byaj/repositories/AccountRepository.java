package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */

import byaj.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    public Account findOneByAccNumber(String num);
}