package socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;
import socialNetwork.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);

   List<User>  findAllByUsernameContaining(String name);

   Boolean existsByUsername(String username);

   Boolean existsByPhone (String phone);

   Boolean existsByEmail (String email);


}
