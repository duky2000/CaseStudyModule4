package socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;
import socialNetwork.model.AppUser;

public interface UserRepository extends CrudRepository<AppUser,Long> {
    AppUser findAllByUserNameContaining(String name);
}
