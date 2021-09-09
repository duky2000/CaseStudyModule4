package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import socialNetwork.model.User;
import socialNetwork.model.Role;
import socialNetwork.service.Implement.RoleService;
import socialNetwork.service.Implement.UserService;
import socialNetwork.validate.ValidateUserName;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    ValidateUserName validateUserName;


    private String upLoadFile = "D:\\Module4\\TestCaseMD4\\src\\main\\webapp\\WEB-INF\\file\\";

    @GetMapping("/login")
    public ModelAndView showFormLogin() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/signup")
    public ModelAndView showFormSignup() {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView showFormUser() {
        ModelAndView modelAndView = new ModelAndView("home");
        Long idUser = userService.findByName(getPrincipal()).getId();
        modelAndView.addObject("idUserName",idUser);
        return modelAndView;
    }

    @ModelAttribute("listRole")
    public Iterable<Role> listRole() {
        return roleService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        User appUser = userService.findByIdTest(id);
        modelAndView.addObject("appUser",appUser);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("user", userService.findById(id).get());
        return modelAndView;
    }

    @GetMapping("/findByName")
    public ModelAndView findByName(@RequestParam("findName") String name) {
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("list", userService.findByName(name));
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showFormView(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("user", userService.findById(id).get());
        return modelAndView;

    }

    @PostMapping("/create")
    public ModelAndView create( @ModelAttribute("user") User appUser) {
//        String fileName = file.getOriginalFilename();
//        try {
//            FileCopyUtils.copy(file.getBytes(), new File(upLoadFile + fileName));
//        } catch (Exception ex) {
//            System.err.println("err upload file");
//        }
//        appUser.setAvatar(fileName);
//        validateUserName.validate(appUser,bindingResult);

        long id = 2;
        appUser.setRole(roleService.findById(id));
        userService.save(appUser);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;


//        if (bindingResult.hasFieldErrors()) {
//            ModelAndView modelAndView = new ModelAndView("create");
//            modelAndView.addObject("user", appUser);
//            return modelAndView;
//        }

//        if (appUser.getPassword().equals( appUser.getRepass())){
//            long id = 2;
//            appUser.setRole(appRoleService.findById(id));
//            appUserService.save(appUser);
//           ModelAndView modelAndView = new ModelAndView("redirect:/");
//            return modelAndView;
//
//        }
//      ModelAndView  modelAndView = new ModelAndView("redirect:/signup");
//        modelAndView.addObject("message","confirm password does not match");
//        return modelAndView;

    }

    @PostMapping("/edit/{id}")
    public String edit(@RequestParam("upImg") MultipartFile upImg, @ModelAttribute User appUser, @PathVariable long id) {
        String nameImg = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File(upLoadFile + nameImg));
            appUser.setAvatar(nameImg);
        } catch (IOException e) {
            System.err.println("err upload file");
        }

        userService.save(appUser);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        userService.delete(userService.findById(id).get());
        return "redirect:/";
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

}
