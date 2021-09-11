package socialNetwork.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.group.RoleGroup;
import socialNetwork.repository.group.IRoleGroupRepo;

@Service
public class RoleGroupService implements IRoleGroupService {
    @Autowired
    IRoleGroupRepo iRoleGroupRepo;

    @Override
    public RoleGroup getRoleGroupById(long id) {
        return iRoleGroupRepo.findById(id).get();
    }
}
