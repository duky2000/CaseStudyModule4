package socialNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import socialNetwork.model.Notification;

import java.util.List;

public interface INotificationRepository extends JpaRepository<Notification, Long> {
    @Query(nativeQuery = true, value = "select * from notifications n where n.post_id in (select p.id from posts p where p.user_id = :userid ) order by n.notifyTime desc ")
    List<Notification> findAllByReceiver(@Param("userid") Long userid);
    @Query(nativeQuery = true, value = "select * from notifications n where n.content = 'like' and n.post_id = :postid and n.sender_id = :senderid")
    Notification findLikeNotifyByPostAndSender(@Param("postid") Long id, @Param("senderid") Long sid);
}
