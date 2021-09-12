package socialNetwork.service.friend;

import socialNetwork.model.friend.Friend;

import java.util.ArrayList;

public interface IFriendService {
    void save(Friend friend);

    ArrayList<Friend> findNonFriend(long id_user);

    Friend findInvitationsFriendById(long id);

    void delete(Friend friend);

    Long findId(long id_user, long id_friend);

    ArrayList<Friend> findIdMyFriend(long id_user, long id_friend);

    ArrayList<Friend> check(long id_user, long id_friend);
}