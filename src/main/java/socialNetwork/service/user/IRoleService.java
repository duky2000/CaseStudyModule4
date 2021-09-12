package socialNetwork.service.user;

import socialNetwork.model.Role;
import socialNetwork.model.User;


public interface IRoleService {
    Iterable<Role> findAll();
    Role findById(Long id);

}
