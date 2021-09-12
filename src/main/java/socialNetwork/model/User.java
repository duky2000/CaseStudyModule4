package socialNetwork.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    @Size(min = 6,message = "passWord > 6")
    private String password;
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-z]+\\.[a-z]+$", message = "sai email")
    private String email;
    @Pattern(regexp = "^[0][0-9]{9}$", message = "sdt phai bat dau tu 0 va 9 so")
    private String phone;
    @NotEmpty(message = "Not null")
    private String address;
    private Date dateOfBirthday;
    private String repass;
    private String avatar;

    @ManyToOne
    @JoinColumn
    private Role role;


    public User() {
    }

    public User(String username, String password, String email, String phone, String address, Date dateOfBirthday, String repass) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfBirthday = dateOfBirthday;
        this.repass = repass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(long id, String username, String password, String email, String phone, String address, Date dateOfBirthday, String repass, String avatar, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfBirthday = dateOfBirthday;
        this.repass = repass;
        this.avatar = avatar;
        this.role = role;

    }
}
