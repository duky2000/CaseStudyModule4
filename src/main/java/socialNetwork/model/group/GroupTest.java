package socialNetwork.model.group;

import socialNetwork.model.User;

import javax.persistence.*;

@Entity

public class GroupTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameGroup;

    public GroupTest(){}

    public GroupTest(long id, String nameGroup) {
        this.id = id;
        this.nameGroup = nameGroup;
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


}
