package socialNetwork.service.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.friend.Friend;
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
        return iFriendRepo.friendRequest(id_user);
    }

    @Override
    public Friend findInvitationsFriendById(long id) {
        return iFriendRepo.findInvitationsFriendById(id);
    }

    @Override
    public void delete(Friend friend) {
        iFriendRepo.delete(friend);
    }

    @Override
    public Long findId(long id_user, long id_friend) {
        return iFriendRepo.findId(id_user, id_friend);
    }

    @Override
    public ArrayList<Friend> findIdMyFriend(long id_user, long id_friend) {
        return iFriendRepo.findFriendById_friendAndAndId_user(id_friend, id_user);
    }


}
