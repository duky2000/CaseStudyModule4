package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import socialNetwork.model.Post;
import socialNetwork.model.friend.Friend;
import socialNetwork.service.ILikesService;
import socialNetwork.service.IMessageService;
import socialNetwork.service.INotificationService;
import socialNetwork.service.IPostService;
import socialNetwork.service.friend.IFriendService;
import socialNetwork.service.friend.IUserServices;
import socialNetwork.service.user.IUserService;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class HomeController {
    @Autowired
    IUserService userService;
    @Autowired
    IPostService postService;
    @Autowired
    ILikesService likesService;
    @Autowired
    IMessageService messageService;
    @Autowired
    INotificationService notificationService;
    @Autowired
    IFriendService iFriendService;

    @GetMapping("/")
    public ModelAndView showHomePage() {
        ModelAndView modelAndView = new ModelAndView("post/fbhome");
        ArrayList<Post> posts = (ArrayList<Post>) postService.findAllByOrderByPostTimeDesc();
        for (int i = 0; i < posts.size(); i++) {
            if (likesService.findSumLikes(posts.get(i).getId()) == null) posts.get(i).setLikes(0);
            else posts.get(i).setLikes(likesService.findSumLikes(posts.get(i).getId()));
        }
        ArrayList<Post> finalPosts = new ArrayList<>();
        ArrayList<Long> listFriends = iFriendService.getAllFriendPost(userService.getPrincipal().getId());
        listFriends.add(userService.getPrincipal().getId());
        for (Post p : posts) {
            if (listFriends.contains(p.getUser().getId())) {
                finalPosts.add(p);
            }
        }

        modelAndView.addObject("posts", finalPosts);
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("user", userService.getPrincipal());
        Long lastestSender;
        if (messageService.findAllSenderByTimeDesc(userService.getPrincipal().getId()).size() > 0) {
        lastestSender =  messageService.findAllSenderByTimeDesc(userService.getPrincipal().getId())
                .get(0).getSender().getId();
        modelAndView.addObject("lastestSender", lastestSender);
        } else modelAndView.addObject("lastestSender", 0);
        List<String> displayTimes = new ArrayList<>();
        Date date = new Date();
        long milliseconds2 = date.getTime();
        for (Post p : posts) {
            long milliseconds1 = p.getPostTime().getTime();
            int result1 = (int) ((milliseconds2 - milliseconds1)/1000);
            int result2 = (int) (result1/60);
            int result3 = (int) (result2/60);
            int result4 = (int) (result3/24);
            if (result1 < 60) displayTimes.add(result1 + " seconds ago");
            else if (result2 < 60) displayTimes.add(result2 + " minutes ago");
            else if (result3 < 24) displayTimes.add(result3 + " hours ago");
            else displayTimes.add(result4 + " days ago");

        };

        modelAndView.addObject("displayTimes", displayTimes);

//        Notify

        modelAndView.addObject("notifications", notificationService.findAllByReceiver(userService.getPrincipal().getId()));
        modelAndView.addObject("list", iFriendService.findNonFriend(userService.getPrincipal().getId()));
        return modelAndView;
    }

//    @GetMapping("/home")
//    public String getFbClone() {
//        return "fbhome";
//    }
//
//    @GetMapping("/profile-clone")
//    public String getProfileClone() {
//        return "/fbprofile";
//    }
}
