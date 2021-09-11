package socialNetwork.repository.group;

import org.springframework.data.repository.CrudRepository;
import socialNetwork.model.group.RoleGroup;

public interface IRoleGroupRepo extends CrudRepository<RoleGroup, Long> {
}
