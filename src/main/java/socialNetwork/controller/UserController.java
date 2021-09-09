package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${static-path}")
    private String fileUpload;


    @GetMapping("/login")
    public ModelAndView showFormLogin() {
        ModelAndView modelAndView = new ModelAndView("user/login");
        return modelAndView;
    }

    @GetMapping("/signup")
    public ModelAndView showFormSignup() {
        ModelAndView modelAndView = new ModelAndView("user/signup");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping("/forgotPassword")
    public ModelAndView showFormPassWord() {
        ModelAndView modelAndView = new ModelAndView("user/forgotPassword");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }


    @GetMapping("/")
    public ModelAndView showFormUser() {
        ModelAndView modelAndView = new ModelAndView("user/home");
        Long idUser = userService.findByName(getPrincipal()).getId();
        modelAndView.addObject("idUserName", idUser);
        return modelAndView;
    }

    @ModelAttribute("listRole")
    public Iterable<Role> listRole() {
        return roleService.findAll();
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("user/edit");
        User appUser = userService.findById(id).get();
        modelAndView.addObject("appUser", appUser);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("user/delete");
        modelAndView.addObject("user", userService.findById(id).get());
        return modelAndView;
    }

    @GetMapping("/findByName")
    public ModelAndView findByName(@RequestParam("findName") String name) {
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("list", userService.findByName(name));
        return modelAndView;
    }


    @PostMapping("/signup")
    public ModelAndView signup(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {


        ModelAndView modelAndView;
        validateUserName.validate(user,bindingResult);
        if (!user.getPassword().equals(user.getRepass())) {

            modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("message", "confirm password does not match");

        } else if (bindingResult.hasFieldErrors()){
            modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("username", "Duplicate name");

        }
        else {
            long id = 2;
            user.setRole(roleService.findById(id));

            userService.save(user);
            modelAndView = new ModelAndView("redirect:/");
        }
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String edit(@RequestParam("upImg") MultipartFile upImg, @ModelAttribute User user) {
        String nameImg = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File(fileUpload +"WEB-INF\\file\\"+ nameImg));
            user.setAvatar("/resource/WEB-INF/file/"+nameImg);
        } catch (IOException e) {
            System.err.println("err upload file");
        }

        userService.save(user);
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
