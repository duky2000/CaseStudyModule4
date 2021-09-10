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
        //iUserServices.findIdByUsername(getPrincipal()) = id_user
        //id = id_friend
        Friend friend = iFriendService.findFriend(iUserServices.findIdByUsername(getPrincipal()), id);
        friend.setStatus(1);
        iFriendService.save(friend);
        return modelAndView;
    }

    @RequestMapping("/ignore/{id}")
    public ModelAndView ignore(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/fr/home");
        //iUserServices.findIdByUsername(getPrincipal()) = id_user
        //id = id_friend
        Friend friend = iFriendService.findFriend(iUserServices.findIdByUsername(getPrincipal()), id);
        iFriendService.delete(friend);
        return modelAndView;
    }

    @GetMapping("/friendList")
    public ModelAndView showUserList(){
        ModelAndView modelAndView = new ModelAndView("friend/view/friendList");
        modelAndView.addObject("showUserList", iUserServices.findAllUser(getPrincipal()));
        return modelAndView;
    }

}
