package socialNetwork.repository.friend;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import socialNetwork.model.friend.Friend;

import java.util.ArrayList;

public interface IFriendRepo extends CrudRepository<Friend, Long> {

    // select ra loi moi ket ban
    @Query(value = "select * from case_study_4.friend where id_friend_id = :id_user and status = 3", nativeQuery = true)
    public ArrayList<Friend> friendRequest(@Param("id_user") long id_user);

//
//    @Query(value = "select * from case_study_4.friend where case_study_4.friend.id_friend_id = ?1 and case_study_4.friend.id_user_id = ?2", nativeQuery = true)
//    public Friend findFriend(long id_user, long id_friend);

    @Query(value = "SELECT * FROM case_study_4.friend WHERE case_study_4.friend.id = :id", nativeQuery = true)
    public Friend findInvitationsFriendById(@Param("id") long id);

    @Query(value = "SELECT id FROM case_study_4.friend WHERE case_study_4.friend.id_friend_id = :user AND case_study_4.friend.id_user_id = :friend", nativeQuery = true)
    public Long findId(@Param("user") long id_user, @Param("friend") long id_friend);

//    @Query(value = "select friend.id, friend.status, friend.id_friend_id, friend.id_user_id from case_study_4.user  inner join friend on friend.id_friend_id = user.id where (friend.id_friend_id = :friend and friend.id_user_id = :user) or (friend.id_friend_id = :user and friend.id_user_id = friend) and friend.status = 1", nativeQuery = true)
//    public ArrayList<Friend> findIdMyFriend(@Param("user") long id_user, @Param("friend") long id_friend);

    @Query(value = "select * from case_study_4.friend where (friend.id_friend_id = :friend and friend.id_user_id = :user) or (friend.id_friend_id = :user and friend.id_user_id = :friend) and friend.status = 1", nativeQuery = true)
    public ArrayList<Friend> findFriendById_friendAndAndId_user(@Param("friend")long id_friend, @Param("user") long id_user);

}
