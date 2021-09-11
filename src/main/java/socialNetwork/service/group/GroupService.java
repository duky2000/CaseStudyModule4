package socialNetwork.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.group.GroupTest;
import socialNetwork.repository.group.IGroupRepo;

@Service
public class GroupService implements IGroupService {

    @Autowired
    IGroupRepo iGroupRepo;

    @Override
    public void saveGroup(GroupTest groupTest) {
        iGroupRepo.save(groupTest);
    }
}
