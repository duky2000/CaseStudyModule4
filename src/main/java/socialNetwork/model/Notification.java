package socialNetwork.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Post post;
    @Column(columnDefinition = "TEXT")
    private String content;
    private Timestamp notifyTime;
    @OneToOne
    private User sender;

    public Notification() {
    }

    public Notification(Post post, String content, Timestamp notifyTime, User sender) {
        this.post = post;
        this.content = content;
        this.notifyTime = notifyTime;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Timestamp notifyTime) {
        this.notifyTime = notifyTime;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
