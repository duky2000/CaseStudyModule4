package socialNetwork.service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.Role;
import socialNetwork.repository.RoleRepository;
import socialNetwork.service.IAppRoleService;

import java.util.List;

@Service
public class AppRoleService implements IAppRoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }
}
