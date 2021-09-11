package socialNetwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import socialNetwork.model.group.GroupTest;

@Controller
@RequestMapping("/gr")
public class GroupController {

    @GetMapping("/createGroup")
    public ModelAndView createGround(){
        ModelAndView modelAndView = new ModelAndView("group/createGroup");
        modelAndView.addObject("create", new GroupTest());
        return modelAndView;
    }
}
