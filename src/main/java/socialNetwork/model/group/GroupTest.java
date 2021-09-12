package socialNetwork.model.group;

import socialNetwork.model.User;

import javax.persistence.*;

@Entity

public class GroupTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameGroup;
    private String avatar;
    private String background;

    public GroupTest(){}

    public GroupTest(long id, String nameGroup, String avatar, String background) {
        this.id = id;
        this.nameGroup = nameGroup;
        this.avatar = avatar;
        this.background = background;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
