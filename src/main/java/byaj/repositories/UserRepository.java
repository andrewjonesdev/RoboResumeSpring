package byaj.repositories;

import byaj.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
    public User findOneByUsername(String num);
}
