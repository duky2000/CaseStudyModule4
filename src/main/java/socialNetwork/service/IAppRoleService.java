package socialNetwork.service;

import socialNetwork.model.Role;


public interface IAppRoleService {
    Iterable<Role> findAll();
    Role findById(Long id);

}
