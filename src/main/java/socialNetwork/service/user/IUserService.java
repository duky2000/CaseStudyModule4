package socialNetwork.service.user;

import socialNetwork.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Iterable<User> findAll();

    User findByName(String name);

    Optional<User> findById(Long id);

    void save(User appUser);

    void edit(User appUser);

    void delete(User appUser);

    User findByUsername(String username);



    List<User> findAllByUsernameContaining(String name);
    User getPrincipal();
}
