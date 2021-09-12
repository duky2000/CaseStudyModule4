package socialNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.Message;
import socialNetwork.repository.IMessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService {
    @Autowired
    IMessageRepository messageRepository;
    @Override
    public Iterable<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }


    @Override
    public List<Message> findAllByUserId(Long rid, Long sid) {
        return messageRepository.findAllByUserId(rid, sid);
    }

    @Override
    public List<Message> findAllSenderByTimeDesc(Long uid) {
        return messageRepository.findAllSenderByTimeDesc(uid);
    }

    @Override
    public List<Message> findAllNewestSender(Long uid) {
        return messageRepository.findAllSenderByTimeDesc(uid);
    }
}
