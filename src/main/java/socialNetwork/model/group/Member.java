package socialNetwork.model.group;

import socialNetwork.model.User;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private GroupTest groupTest;
    @ManyToOne
    private RoleGroup roleGroup;

    public Member() {
    }

    public Member(long id, User user, GroupTest groupTest, RoleGroup roleGroup) {
        this.id = id;
        this.user = user;
        this.groupTest = groupTest;
        this.roleGroup = roleGroup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GroupTest getGroupTest() {
        return groupTest;
    }

    public void setGroupTest(GroupTest groupTest) {
        this.groupTest = groupTest;
    }

    public RoleGroup getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(RoleGroup roleGroup) {
        this.roleGroup = roleGroup;
    }
}