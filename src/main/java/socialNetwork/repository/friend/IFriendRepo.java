package socialNetwork.repository.friend;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import socialNetwork.model.Friend;
import socialNetwork.model.User;

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




}
