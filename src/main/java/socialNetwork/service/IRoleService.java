package socialNetwork.service;

import socialNetwork.model.Role;


public interface IRoleService {
    Iterable<Role> findAll();
    Role findById(Long id);

}
