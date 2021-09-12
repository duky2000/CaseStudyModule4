package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import socialNetwork.model.Likes;
import socialNetwork.model.Notification;
import socialNetwork.service.ILikesService;
import socialNetwork.service.INotificationService;
import socialNetwork.service.IPostService;
import socialNetwork.service.user.IUserService;


import java.sql.Timestamp;
import java.util.Date;

@Controller
public class LikesController {
    @Autowired
    ILikesService likesService;
    @Autowired
    IPostService postService;
    @Autowired
    IUserService userService;
    @Autowired
    INotificationService notificationService;
    @GetMapping("/likes-post")
    public ModelAndView likesPost(@RequestParam("id") Long id, @RequestParam("back") String back, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView;
        if (back.equals("detail")) {
            modelAndView = new ModelAndView("redirect:/detail-post?id=" + id);
        } else if (back.equals("profile")) modelAndView = new ModelAndView("redirect:/profile?iduser=" + postService.findById(id).get().getUser().getId());
        else modelAndView = new ModelAndView("redirect:/");
        Likes likes = likesService.findByPostAndUser(postService.findById(id).get(), userService.getPrincipal());
        Notification notification = new Notification();
        Date date = new Date();
        Timestamp notifyTime = new Timestamp(date.getTime());
        notification.setNotifyTime(notifyTime);
        notification.setPost(postService.findById(id).get());
        notification.setContent("like");
        notification.setSender(userService.getPrincipal());
        if (likes == null) {
            likes = new Likes();
            likes.setUser(userService.getPrincipal());
            likes.setPost(postService.findById(id).get());
            likes.setScore(1);
        } else {
            if (likes.getScore() == 1) {
                likes.setScore(0);
            }
            else {
                likes.setScore(1);
            }
        }
        if (likes.getScore() == 1) notificationService.save(notification);
        else {
            if (notificationService.findLikeNotifyByPostAndSender(id, userService.getPrincipal().getId()) != null)
            notificationService.deleteById(notificationService.findLikeNotifyByPostAndSender(id, userService.getPrincipal().getId()).getId());
            else ;
        }
        likesService.save(likes);
        return modelAndView;
    }


}
