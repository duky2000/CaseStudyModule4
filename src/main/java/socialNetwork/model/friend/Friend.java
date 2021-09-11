package socialNetwork.model.friend;

import socialNetwork.model.User;

import javax.persistence.*;

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "int default 0")
    private int status;
    @ManyToOne
    private User id_friend;
    @ManyToOne
    private User id_user;

    public Friend() {
    }

    public Friend(long id, int status, User id_friend, User id_user) {
        this.id = id;
        this.status = status;
        this.id_friend = id_friend;
        this.id_user = id_user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getId_friend() {
        return id_friend;
    }

    public void setId_friend(User id_friend) {
        this.id_friend = id_friend;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }
}
