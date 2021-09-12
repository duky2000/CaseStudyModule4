package socialNetwork.service;

import org.springframework.data.repository.query.Param;
import socialNetwork.model.Message;

import java.util.List;

public interface IMessageService extends IGeneralService<Message>{
    List<Message> findAllByUserId(@Param("receiverid") Long rid, @Param("senderid") Long sid);
    List<Message> findAllSenderByTimeDesc(@Param("userid") Long uid);
    List<Message> findAllNewestSender(@Param("userid") Long uid);

}
