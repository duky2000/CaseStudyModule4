package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import socialNetwork.model.Friend;
import socialNetwork.model.User;
import socialNetwork.service.friend.IFriendService;
import socialNetwork.service.friend.IUserServices;

@Controller
@RequestMapping("/fr")
public class FriendController {

    @Autowired
    IUserServices iUserServices;

    @Autowired
    IFriendService iFriendService;

    @ModelAttribute("getUser")
    public User user(){
        return iUserServices.getUser(iUserServices.findIdByUsername(getPrincipal()));
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @RequestMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("friend/view/home");
        modelAndView.addObject("list", iFriendService.findNonFriend(iUserServices.findIdByUsername(getPrincipal())));
        return modelAndView;
    }

    @RequestMapping("/accept/{id}")
    public ModelAndView accept(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/fr/home");

        //iUserServices.findIdByUsername(getPrincipal()) = id_user(doi tuong dang dap nhap)
        //id = id_friend (id nguoi muon ket ban)


        long id1 = iUserServices.findIdByUsername(getPrincipal());
        long id2 = id;
        System.out.println(id1);
        System.out.println(id2);

        // lúc làm bị rối lên làm cái dưới hơi a đuồi tí

        long a = iFriendService.findId(id1, id2);
        System.out.println(a);
        Friend friend1 = iFriendService.findInvitationsFriendById(a);
        friend1.setStatus(1);
        iFriendService.save(friend1);

        long b = a + 1;
        Friend friend2 = iFriendService.findInvitationsFriendById(b);
        friend2.setStatus(1);
        iFriendService.save(friend2);

        return modelAndView;
    }

    @RequestMapping("/ignore/{id}")
    public ModelAndView ignore(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/fr/home");

        long id1 = iUserServices.findIdByUsername(getPrincipal());
        long id2 = id;

        long a = iFriendService.findId(id1, id2);
        Friend friend1 = iFriendService.findInvitationsFriendById(a);
        long b = a + 1;
        Friend friend2 = iFriendService.findInvitationsFriendById(b);
        iFriendService.delete(friend1);
        iFriendService.delete(friend2);
        return modelAndView;
    }

    @GetMapping("/friendList")
    public ModelAndView showUserList(){
        ModelAndView modelAndView = new ModelAndView("friend/view/friendList");
        modelAndView.addObject("showUserList", iUserServices.findAllUser(getPrincipal()));
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetailUser(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("friend/view/detail");
        modelAndView.addObject("detail", iUserServices.getUser(id));
        return modelAndView;
    }

    @GetMapping("/app/{id}")
    public ModelAndView sendInvitations(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/fr/friendList");

        // doi tuong singUp
        User user1 = iUserServices.getUser(iUserServices.findIdByUsername(getPrincipal()));
        // doi tuong muon ket ban
        User user2 = iUserServices.getUser(id);

        Friend friend1 = new Friend();
        friend1.setStatus(3);
        friend1.setId_user(user1);
        friend1.setId_friend(user2);
        iFriendService.save(friend1);

        Friend friend2 = new Friend();
        friend2.setStatus(0);
        friend2.setId_user(user2);
        friend2.setId_friend(user1);
        iFriendService.save(friend2);

        return modelAndView;
    }

    @GetMapping("/showMyFriend")
    public ModelAndView showMyFriend(){
        ModelAndView modelAndView = new ModelAndView("friend/view/showMyFriend");
        modelAndView.addObject("friendList", iUserServices.findAllMyFriend(iUserServices.findIdByUsername(getPrincipal())));
        return modelAndView;
    }

}
