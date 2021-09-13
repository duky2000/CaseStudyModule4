package socialNetwork.repository.group;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import socialNetwork.model.group.GroupTest;

import java.util.ArrayList;

public interface IGroupRepo extends CrudRepository<GroupTest, Long> {

    @Query(value = "select grouptest.id, grouptest.avatar, grouptest.background, grouptest.nameGroup from grouptest inner join member on member.grouptest_id = grouptest.id where member.user_id = :user;", nativeQuery = true)
    public ArrayList<GroupTest> getAllByIdUser(@Param("user") long id_user);
}
