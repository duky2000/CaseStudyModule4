package socialNetwork.model;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Post post;

    @ManyToOne
    @JoinColumn
    private User user;

    private int score;

    public Likes() {
    }

    public Likes(Post post, User user, int score) {
        this.post = post;
        this.user = user;
        this.score = score;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
