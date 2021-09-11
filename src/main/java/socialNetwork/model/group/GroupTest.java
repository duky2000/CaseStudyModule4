package socialNetwork.model;

import javax.persistence.*;

@Entity

public class GroupTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameGroup;
    private int role_group;
    @ManyToOne
    private User user;

    public GroupTest(){}

    public GroupTest(long id, String nameGroup, int role_group, User user) {
        this.id = id;
        this.nameGroup = nameGroup;
        this.role_group = role_group;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public int getRole_group() {
        return role_group;
    }

    public void setRole_group(int role_group) {
        this.role_group = role_group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
