package socialNetwork.repository.friend;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import socialNetwork.model.User;

import java.util.ArrayList;

public interface IUserRepo extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM case_study_4.user where username not like concat('',:username,'')", nativeQuery = true)
    public ArrayList<User> findAllUser(@Param("username") String username);

    @Query(value = "SELECT case_study_4.user.id FROM case_study_4.user where username like concat('',:username,'')", nativeQuery = true)
    public long findIdByUsername(@Param("username") String username);

    @Query(value = "select user.id, user.address, user.avatar, user.dateOfBirthday, user.email, user.password, user.phone, user.repass, user.username, user.role_id from case_study_4.user  inner join friend on friend.id_friend_id = user.id where friend.id_user_id = :user and friend.status = 1;", nativeQuery = true)
    public ArrayList<User> findAllMyFriend(@Param("user") long user);

    @Query(value = "select user.id from case_study_4.user  inner join friend on friend.id_friend_id = user.id where friend.id_user_id = :user and friend.status = 1;", nativeQuery = true)
    public ArrayList<Long> findIdTest(@Param("user") long user);
}
