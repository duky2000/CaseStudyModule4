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

    Boolean existsByUsername(String username);

    Boolean existsByPhone (String phone);

    Boolean existsByEmail (String email);



    List<User> findAllByUsernameContaining(String name);
    public User getPrincipal();
}
