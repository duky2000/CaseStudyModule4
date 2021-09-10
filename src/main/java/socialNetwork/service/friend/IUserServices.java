package socialNetwork.service.friend;

import socialNetwork.model.User;

import java.util.ArrayList;

public interface IUserServices {
    void save(User user);

    ArrayList<User> findAllUser(String username);

    User getUser(long id);

    long findIdByUsername(String username);

    ArrayList<User> findAllMyFriend(long user);
}
