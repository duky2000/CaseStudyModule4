package socialNetwork.service.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.User;
import socialNetwork.repository.friend.IUserRepo;

import java.util.ArrayList;

@Service
public class UserServices implements IUserServices{
    @Autowired
    IUserRepo iUserRepo;

    @Override
    public void save(User user) {
        iUserRepo.save(user);
    }

    @Override
    public ArrayList<User> findAllUser(String username) {
        return iUserRepo.findAllUser(username);
    }

    @Override
    public User getUser(long id) {
        return iUserRepo.findById(id).get();
    }

    @Override
    public long findIdByUsername(String username) {
        return iUserRepo.findIdByUsername(username);
    }
}