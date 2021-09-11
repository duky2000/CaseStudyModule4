package socialNetwork.repository.group;

import org.springframework.data.repository.CrudRepository;
import socialNetwork.model.group.Member;

public interface IMemberRepo extends CrudRepository<Member, Long> {
}
