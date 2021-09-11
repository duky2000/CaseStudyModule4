package socialNetwork.model.group;

import socialNetwork.model.User;

import javax.persistence.*;

@Entity

public class GroupTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameGroup;
    @ManyToOne
    private TypeGroup typeGroup;

    public GroupTest(){}

    public GroupTest(long id, String nameGroup, TypeGroup typeGroup) {
        this.id = id;
        this.nameGroup = nameGroup;
        this.typeGroup = typeGroup;
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

    public TypeGroup getTypeGroup() {
        return typeGroup;
    }

    public void setTypeGroup(TypeGroup typeGroup) {
        this.typeGroup = typeGroup;
    }

}
