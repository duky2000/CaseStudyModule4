package socialNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.Notification;
import socialNetwork.repository.INotificationRepository;


import java.util.List;
import java.util.Optional;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    INotificationRepository notificationRepository;


    @Override
    public List<Notification> findAllByReceiver(Long userid) {
        return notificationRepository.findAllByReceiver(userid);
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Notification findLikeNotifyByPostAndSender(Long id, Long sid) {
        return notificationRepository.findLikeNotifyByPostAndSender(id, sid);
    }
}
