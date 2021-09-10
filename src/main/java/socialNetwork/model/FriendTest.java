package socialNetwork.model;

import javax.persistence.Entity;


public class FriendTest {
    private long id;
    private String name;
    private String address;
    private String gmail;
    private int status;

    public FriendTest() {
    }

    public FriendTest(long id, String name, String address, String gmail, int status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gmail = gmail;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
