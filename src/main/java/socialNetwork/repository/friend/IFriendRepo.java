package socialNetwork.repository.friend;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import socialNetwork.model.Friend;

import java.util.ArrayList;

public interface IFriendRepo extends CrudRepository<Friend, Long> {

    @Query(value = "select * from case_study_4.friend where id_friend_id = :id_user and status = 0", nativeQuery = true)
    public ArrayList<Friend> findAllIdNon_Friend(@Param("id_user") long id_user);


    @Query(value = " select * from case_study_4.friend where case_study_4.friend.id_friend_id = :id_user and case_study_4.friend.id_user_id = :id_friend", nativeQuery = true)
    public Friend findFriend(@Param("id_user") long id_user, @Param("id_friend") long id_friend);
}
