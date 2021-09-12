package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import socialNetwork.model.Message;
import socialNetwork.service.IMessageService;
import socialNetwork.service.user.IUserService;


import java.sql.Timestamp;
import java.util.Date;

@Controller
public class MessageController {
    @Autowired
    IUserService userService;
    @Autowired
    IMessageService messageService;
//    @ModelAttribute("currentUser")
//    public User getCurrentUser() {
//        return userService.getPrincipal();
//    }
//    @ModelAttribute("lastestSender")
//    public Long getLastestSender() {
//        return messageService.findAllSenderByTimeDesc(userService.getPrincipal().getId())
//                .get(0).getSender().getId();
//    }

    @GetMapping("/message")
    public ModelAndView showMessageDetail(@RequestParam("idsend") Long idsend) {
        ModelAndView modelAndView = new ModelAndView("message/message");
//        List<Message> testmessages = messageService.findAllNewestSender(userService.getPrincipal().getId());
//        List<Message> results = new ArrayList<>();
//        if (testmessages.size() > 1) {
//            for (int i = 0; i < testmessages.size() - 1; i++) {
//                for (int j = i + 1; j < testmessages.size(); j++) {
//                    results.add(testmessages.get(i));
//                    if (extracted(testmessages, i) != extracted(testmessages, j) && !results.contains(testmessages.get(j)))
//                        results.add(testmessages.get(j));
//                }
//            }
//            modelAndView.addObject("senders", results);
//        } else if (testmessages.size() == 1) modelAndView.addObject("senders", testmessages);
//        else ;
        if (idsend != 0) {
            modelAndView.addObject("message", new Message());
            modelAndView.addObject("senders", messageService.findAllSenderByTimeDesc(userService.getPrincipal().getId()));

            modelAndView.addObject("messages",
                    messageService.findAllByUserId(userService.getPrincipal().getId(), idsend));
            modelAndView.addObject("currentid", userService.getPrincipal().getId());
            modelAndView.addObject("notfound", false);
        } else {
            modelAndView.addObject("notfound", true);
        }
        return modelAndView;
    }

//    private Long extracted(List<Message> testmessages, int i) {
//        Long x = testmessages.get(i).getSender().getId();
//        Long y = testmessages.get(i).getReceiver().getId();
//        return x + y + x * y;
//    }

    @PostMapping("/message")
    public ModelAndView createMessage(@RequestParam("idsend") Long idsend, @ModelAttribute("message") Message message) {
        ModelAndView modelAndView = new ModelAndView("redirect:/message?idsend=" + idsend);
        Date date = new Date();
        Timestamp messageTime = new Timestamp(date.getTime());
        message.setMessageTime(messageTime);
        message.setSender(userService.getPrincipal());
        message.setReceiver(userService.findById(idsend).get());
        messageService.save(message);
        return modelAndView;
    }
}
