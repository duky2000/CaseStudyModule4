package socialNetwork.service.friend;

import socialNetwork.model.Friend;

import java.util.ArrayList;

public interface IFriendService {
    void save(Friend friend);

    ArrayList<Friend> findNonFriend(long id_user);

    Friend findFriend(long id_user, long id_friend);

    void delete(Friend friend);
}
