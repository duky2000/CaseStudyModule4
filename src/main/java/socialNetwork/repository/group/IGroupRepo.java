package socialNetwork.repository.group;

import org.springframework.data.repository.CrudRepository;
import socialNetwork.model.group.GroupTest;

public interface IGroupRepo extends CrudRepository<GroupTest, Long> {
}
