package socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;
import socialNetwork.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);

   List<User>  findAllByUsernameContaining(String name);


}
