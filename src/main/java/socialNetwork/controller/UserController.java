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

import socialNetwork.service.user.IRoleService;
import socialNetwork.service.user.UserService;


import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    IRoleService roleService;


    @Value("${img-path}")
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

    @PostMapping("/forgotPassword")
    public ModelAndView findPassWord(@RequestParam String username) {

        ModelAndView modelAndView = new ModelAndView("user/forgotPassword");
        if (userService.findByName(username) != null) {
            modelAndView.addObject("result", userService.findByName(username).getPassword());
        } else {
            modelAndView.addObject("result", "This account does not exist");
        }

        return modelAndView;
    }


//    @GetMapping("/")
//    public ModelAndView showFormUser() {
//        ModelAndView modelAndView = new ModelAndView("user/home");
//        Long idUser = userService.findByName(getPrincipal()).getId();
//        modelAndView.addObject("idUserName", idUser);
//        return modelAndView;
//    }

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

        if (userService.existsByUsername(user.getUsername())) {

            modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("message", "sam user name");

        } else if (userService.existsByPhone(user.getPhone())) {

            modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("message", "sam phone number ");

        } else if (userService.existsByEmail(user.getEmail())) {

            modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("message", "sam email");

        } else if (!user.getPassword().equals(user.getRepass())) {

            modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("message", "confirm password does not match");

        } else if (bindingResult.hasFieldErrors()) {
            modelAndView = new ModelAndView("user/signup");

        } else {
            long id = 2;
            user.setRole(roleService.findById(id));

            userService.save(user);
            modelAndView = new ModelAndView("redirect:/login");
        }
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@RequestParam("upImg") MultipartFile upImg, @ModelAttribute User user,@PathVariable long id) {
        String nameImg = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File(fileUpload + "file\\" + nameImg));
            user.setAvatar("/resource/file/" + nameImg);
        } catch (IOException e) {
            System.err.println("err upload file");
        }
        Optional<User> user1 = userService.findById(id);
        if (user1.isPresent()){
            if (user.getRole() == null){
                user.setRole(user1.get().getRole());
            }
        }
            userService.save(user);
        ModelAndView modelAndView = new ModelAndView("user/edit");
        modelAndView.addObject("appUser", user);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        userService.delete(userService.findById(id).get());
        return "redirect:/login";
    }

//    private String getPrincipal() {
//        String userName = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            userName = ((UserDetails) principal).getUsername();
//        } else {
//            userName = principal.toString();
//        }
//        return userName;
//    }

}
