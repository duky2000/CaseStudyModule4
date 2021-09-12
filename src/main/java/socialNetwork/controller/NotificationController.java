package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import socialNetwork.service.ILikesService;
import socialNetwork.service.INotificationService;
import socialNetwork.service.IPostService;
import socialNetwork.service.user.IUserService;


@Controller
public class NotificationController {
    @Autowired
    IPostService postService;
    @Autowired
    IUserService userService;
    @Autowired
    ILikesService likesService;
    @Autowired
    INotificationService notificationService;
//    @GetMapping("/notify")
//    public ModelAndView getNotify() {
//        ModelAndView modelAndView = new ModelAndView("notification");
//        modelAndView.addObject("notifications", notificationService.findAllByReceiver(userService.getPrincipal().getId()));
//        List<String> displayTimes = new ArrayList<>();
//        Date date = new Date();
//        long milliseconds2 = date.getTime();
//        for (Notification n : notificationService.findAllByReceiver(userService.getPrincipal().getId())) {
//            long milliseconds1 = n.getNotifyTime().getTime();
//            int result1 = (int) ((milliseconds2 - milliseconds1)/1000);
//            int result2 = (int) (result1/60);
//            int result3 = (int) (result2/60);
//            int result4 = (int) (result3/24);
//            if (result1 < 60) displayTimes.add(result1 + " seconds ago");
//            else if (result2 < 60) displayTimes.add(result2 + " minutes ago");
//            else if (result3 < 24) displayTimes.add(result3 + " hours ago");
//            else displayTimes.add(result4 + " days ago");
//
//        };
//
//        modelAndView.addObject("displayTimes", displayTimes);
//        return modelAndView;
//    }
    @GetMapping("/delete-notify")
    public ModelAndView deleteNotify(@RequestParam("idnotify") Long idnotify) {
        notificationService.deleteById(idnotify);
        return new ModelAndView("redirect:/");
    }
}
