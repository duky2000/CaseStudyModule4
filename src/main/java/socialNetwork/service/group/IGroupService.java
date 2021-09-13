package socialNetwork.service.group;

import socialNetwork.model.friend.Friend;
import socialNetwork.model.group.GroupTest;

import java.util.ArrayList;

public interface IGroupService {

    void saveGroup(GroupTest groupTest);

    ArrayList<GroupTest> getAllGroupByIdUser(long id_user);
}
