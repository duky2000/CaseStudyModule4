package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import socialNetwork.model.group.GroupTest;
import socialNetwork.model.group.Member;
import socialNetwork.service.friend.IUserServices;
import socialNetwork.service.group.IGroupService;
import socialNetwork.service.group.IMemberService;
import socialNetwork.service.group.IRoleGroupService;

import java.util.ArrayList;

@Controller
@RequestMapping("/gr")
public class GroupController {

    @Autowired
    IGroupService iGroupService;


    @Autowired
    IUserServices iUserServices;

    @Autowired
    IRoleGroupService iRoleGroupService;

    @Autowired
    IMemberService iMemberService;


    //---------

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

    //---------


    @GetMapping("/createGroup")
    public ModelAndView showCreateGroup(){
        ModelAndView modelAndView = new ModelAndView("group/createGroup");
        modelAndView.addObject("create", new GroupTest());
        return modelAndView;
    }

    @PostMapping("/createGroup")
    public ModelAndView createGroup(@ModelAttribute("create") GroupTest groupTest){
        ModelAndView modelAndView = new ModelAndView("group/test");
        iGroupService.saveGroup(groupTest);
        Member member = new Member();
        member.setUser(iUserServices.getUser(iUserServices.findIdByUsername(getPrincipal())));
        member.setGroupTest(groupTest);
        member.setRoleGroup(iRoleGroupService.getRoleGroupById(1));
        iMemberService.save(member);
        return modelAndView;
    }
}
