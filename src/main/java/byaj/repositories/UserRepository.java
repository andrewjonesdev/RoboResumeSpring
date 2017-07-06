package byaj.repositories;

import byaj.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
    public User findOneByUsername(String num);
    User findByEmail(String email);
    int countByEmail(String email);
    int countByUsername(String username);

}
