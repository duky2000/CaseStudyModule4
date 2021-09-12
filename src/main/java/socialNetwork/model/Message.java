package socialNetwork.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    private Timestamp messageTime;

    public Message() {
    }

    public Message(User sender, String content, User receiver, Timestamp messageTime) {
        this.sender = sender;
        this.content = content;
        this.receiver = receiver;
        this.messageTime = messageTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }
}
