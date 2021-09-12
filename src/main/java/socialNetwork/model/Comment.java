package socialNetwork.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "Hay binh luan gi do")
    private String content;
    private Timestamp commentTime;
    private int likes;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    public Comment() {
    }

    public Comment(@NotEmpty String content, Timestamp commentTime, int likes, Post post) {
        this.content = content;
        this.commentTime = commentTime;
        this.likes = likes;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void upLikes() {
        this.likes++;
    }

    public void disLikes() {
        this.likes--;
    }
}
