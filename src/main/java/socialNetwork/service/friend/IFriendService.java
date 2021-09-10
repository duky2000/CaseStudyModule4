package socialNetwork.service.friend;

import socialNetwork.model.Friend;
import socialNetwork.model.User;

import java.util.ArrayList;

public interface IFriendService {
    void save(Friend friend);

    ArrayList<Friend> findNonFriend(long id_user);

    Friend findInvitationsFriendById(long id);

    void delete(Friend friend);

    Long findId(long id_user, long id_friend);


}
