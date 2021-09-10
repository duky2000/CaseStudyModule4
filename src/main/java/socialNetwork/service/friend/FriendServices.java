package socialNetwork.service.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.Friend;
import socialNetwork.repository.friend.IFriendRepo;

import java.util.ArrayList;

@Service
public class FriendServices implements IFriendService{

    @Autowired
    IFriendRepo iFriendRepo;

    @Override
    public void save(Friend friend) {
        iFriendRepo.save(friend);
    }

    @Override
    public ArrayList<Friend> findNonFriend(long id_user) {
        return iFriendRepo.findAllIdNon_Friend(id_user);
    }

    @Override
    public Friend findFriend(long id_user, long id_friend) {
        return iFriendRepo.findFriend(id_user, id_friend);
    }

    @Override
    public void delete(Friend friend) {
        iFriendRepo.delete(friend);
    }
}
