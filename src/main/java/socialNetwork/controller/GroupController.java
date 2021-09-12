package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import socialNetwork.model.group.GroupTest;
import socialNetwork.model.group.Member;
import socialNetwork.service.friend.IUserServices;
import socialNetwork.service.group.IGroupService;
import socialNetwork.service.group.IMemberService;
import socialNetwork.service.group.IRoleGroupService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/gr")
public class GroupController {

    @Value("${static-path}")
    private String fileUpload;

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
    public ModelAndView showCreateGroup() {
        ModelAndView modelAndView = new ModelAndView("group/CreateGroupDemo");
        modelAndView.addObject("create", new GroupTest());
        return modelAndView;
    }

    @PostMapping("/createGroup")
    public ModelAndView createGroup(@ModelAttribute("create") GroupTest groupTest,@RequestParam("avatarUpFile") MultipartFile avatarUpFile, @RequestParam("backgroundUpFile") MultipartFile backgroundUpFile) {
        ModelAndView modelAndView = new ModelAndView("group/test");

        String nameAvatar = avatarUpFile.getOriginalFilename();
        String nameBackground = backgroundUpFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(avatarUpFile.getBytes(), new File(fileUpload + "WEB-INF\\file\\" + nameAvatar));
            groupTest.setAvatar("/resource/WEB-INF/file/" + nameAvatar);
            System.out.println(nameAvatar);
        } catch (IOException e) {
            System.err.println("err upload file");
        }


        try {
            FileCopyUtils.copy(backgroundUpFile.getBytes(), new File(fileUpload + "WEB-INF\\file\\" + nameBackground));
            groupTest.setAvatar("/resource/WEB-INF/file/" + nameBackground);
            System.out.println(nameBackground);
        } catch (IOException e) {
            System.err.println("err upload file");
        }

        iGroupService.saveGroup(groupTest);


        Member member = new Member();
        member.setUser(iUserServices.getUser(iUserServices.findIdByUsername(getPrincipal())));
        member.setGroupTest(groupTest);
        member.setRoleGroup(iRoleGroupService.getRoleGroupById(1));
        iMemberService.save(member);
        return modelAndView;
    }


}
