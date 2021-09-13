package socialNetwork.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.friend.Friend;
import socialNetwork.model.group.GroupTest;
import socialNetwork.repository.group.IGroupRepo;

import java.util.ArrayList;

@Service
public class GroupService implements IGroupService {

    @Autowired
    IGroupRepo iGroupRepo;

    @Override
    public void saveGroup(GroupTest groupTest) {
        iGroupRepo.save(groupTest);
    }

    @Override
    public ArrayList<GroupTest> getAllGroupByIdUser(long id_user) {
        return iGroupRepo.getAllByIdUser(id_user);
    }
}
