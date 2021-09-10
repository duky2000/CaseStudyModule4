package socialNetwork.service.friend.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import socialNetwork.model.Friend;
import socialNetwork.model.User;
import socialNetwork.repository.friend.IFriendRepo;
import socialNetwork.repository.friend.IUserRepo;

import java.util.ArrayList;

public class Test {
//    @Autowired
//    IUserRepo iUserRepo;
//
//    @Autowired
//    IFriendRepo iFriendRepo;
//
//    private String getPrincipal() {
//        String userName = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            userName = ((UserDetails) principal).getUsername();
//        } else {
//            userName = principal.toString();
//        }
//        return userName;
//    }
//
//    public ArrayList<User> listUser = iUserRepo.findAllUser(getPrincipal());
//
////    public ArrayList<Friend> listFriend = iFriendRepo
//
//    public void appListFriend(){
//
//    }
}
