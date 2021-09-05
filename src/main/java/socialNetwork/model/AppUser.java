package socialNetwork.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 2;
    @Pattern(regexp = "^[^\\d]+$" , message = "Name Not Number")
    @Size(min = 2,message = "Name > 2")
    private String userName;
    @Size(min = 6,message = "passWord > 6")
    private String passWord;

    @ManyToOne
    Role role;

    public AppUser() {
    }

    public AppUser(long id, String userName, String passWord, Role role) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
