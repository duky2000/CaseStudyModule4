package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import socialNetwork.model.Comment;
import socialNetwork.model.Notification;
import socialNetwork.service.ICommentService;
import socialNetwork.service.INotificationService;
import socialNetwork.service.IPostService;
import socialNetwork.service.user.IUserService;


import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    ICommentService commentService;
    @Autowired
    IPostService postService;
    @Autowired
    IUserService userService;
    @Autowired
    INotificationService notificationService;
    @PostMapping("/create-comment")
    public ModelAndView createComment(@RequestParam("postId") Long postId, @RequestParam("back") String back, @Valid @ModelAttribute("comment") Comment comment, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView;
        if (bindingResult.hasFieldErrors()) {
            redirectAttributes.addAttribute("id", postId);
            redirectAttributes.addFlashAttribute("errorCode", "Ban phai nhap gi do truoc khi dang");
            if (back.equals("detail")) {
                modelAndView = new ModelAndView("redirect:/detail-post?id=" + postId);
            } else if (back.equals("profile")) modelAndView = new ModelAndView("redirect:/profile?iduser=" + postService.findById(postId).get().getUser().getId());
            else modelAndView = new ModelAndView("redirect:/");
            return modelAndView;
        }
        if (back.equals("detail")) {
            modelAndView = new ModelAndView("redirect:/detail-post?id=" + postId);
        } else if (back.equals("profile")) modelAndView = new ModelAndView("redirect:/profile?iduser=" + postService.findById(postId).get().getUser().getId());
        else modelAndView = new ModelAndView("redirect:/");
        Date date = new Date();
        Timestamp postTime = new Timestamp(date.getTime());
        comment.setCommentTime(postTime);
        comment.setUser(userService.getPrincipal());
        comment.setPost(postService.findById(postId).get());
        commentService.save(comment);
        Notification notification = new Notification();
        notification.setNotifyTime(postTime);
        notification.setSender(userService.getPrincipal());
        notification.setContent("comment");
        notification.setPost(postService.findById(postId).get());
        notificationService.save(notification);
        return modelAndView;
    }
}
