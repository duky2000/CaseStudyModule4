package socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;
import socialNetwork.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String name);


}
