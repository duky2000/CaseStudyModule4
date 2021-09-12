package socialNetwork.service;

import org.springframework.data.repository.query.Param;
import socialNetwork.model.Notification;


import java.util.List;
import java.util.Optional;

public interface INotificationService {
    List<Notification> findAllByReceiver(@Param("userid") Long userid);
    Optional<Notification> findById(Long id);
    Notification save(Notification notification);
    void deleteById(Long id);
    Notification findLikeNotifyByPostAndSender(@Param("postid") Long id, @Param("senderid") Long sid);
}
