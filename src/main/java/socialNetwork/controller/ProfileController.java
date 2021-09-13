package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import socialNetwork.model.Comment;
import socialNetwork.model.Post;
import socialNetwork.service.ILikesService;
import socialNetwork.service.IPostService;
import socialNetwork.service.friend.IFriendService;
import socialNetwork.service.friend.IUserServices;
import socialNetwork.service.user.IUserService;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    IUserService userService;
    @Autowired
    IPostService postService;
    @Autowired
    ILikesService likesService;
    @Autowired
    IFriendService iFriendService;
    @Autowired
    IUserServices iUserServices;

    //    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login";
//    }
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

    @GetMapping("/profile")
    public ModelAndView getProfile(@RequestParam("iduser") Long iduser, @ModelAttribute("errorCode") String errorCode) {
        ModelAndView modelAndView = new ModelAndView("post/fbprofile");
//        ArrayList<Post> posts = (ArrayList<Post>) postService.findByUserOrderByPostTimeDesc(userService.getPrincipal());
        ArrayList<Post> posts = (ArrayList<Post>) postService.findByUserOrderByPostTimeDesc(userService.findById(iduser).get());

        for (int i = 0; i < posts.size(); i++) {
            if (likesService.findSumLikes(posts.get(i).getId()) == null) posts.get(i).setLikes(0);
            else posts.get(i).setLikes(likesService.findSumLikes(posts.get(i).getId()));
        }
        modelAndView.addObject("user", userService.findById(iduser).get());
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("currentuser", userService.getPrincipal());
        modelAndView.addObject("check", 1);
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("comment", new Comment());
        if (errorCode == null) ;
        else modelAndView.addObject("errorCode", errorCode);
        List<String> displayTimes = new ArrayList<>();
        Date date = new Date();
        long milliseconds2 = date.getTime();
        for (Post p : posts) {
            long milliseconds1 = p.getPostTime().getTime();
            int result1 = (int) ((milliseconds2 - milliseconds1) / 1000);
            int result2 = (int) (result1 / 60);
            int result3 = (int) (result2 / 60);
            int result4 = (int) (result3 / 24);
            if (result1 < 60) displayTimes.add(result1 + " seconds ago");
            else if (result2 < 60) displayTimes.add(result2 + " minutes ago");
            else if (result3 < 24) displayTimes.add(result3 + " hours ago");
            else displayTimes.add(result4 + " days ago");

        }
        ;

        modelAndView.addObject("displayTimes", displayTimes);
        return modelAndView;
    }
}
