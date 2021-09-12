package socialNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import socialNetwork.model.Message;


import java.util.List;

public interface IMessageRepository extends JpaRepository<Message, Long> {
    @Query(nativeQuery = true, value = "select * from messages where (receiver_id = :receiverid and sender_id = :senderid) or (receiver_id = :senderid and sender_id = :receiverid) order by messageTime")
    List<Message> findAllByUserId(@Param("receiverid") Long rid, @Param("senderid") Long sid);
    @Query(nativeQuery = true, value = "select l.* from messages l inner join (select sender_id, max(messageTime) as latest from messages where receiver_id = :userid group by sender_id) r on l.messageTime = r.latest and l.sender_id = r.sender_id  where receiver_id = :userid order by messageTime desc")
    List<Message> findAllSenderByTimeDesc(@Param("userid") Long uid);
    @Query(nativeQuery = true, value = "select * from messages where (receiver_id = :userid or sender_id = :userid) and (receiver_id, messageTime) in (select receiver_id, max(messageTime) from messages group by sender_id) order by messageTime desc")
    List<Message> findAllNewestSender(@Param("userid") Long uid);


}
