package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import socialNetwork.model.AppUser;
import socialNetwork.model.Role;
import socialNetwork.service.Implement.AppRoleService;
import socialNetwork.service.Implement.AppUserService;
import socialNetwork.validate.ValidateUserName;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
public class AppUserController {
    @Autowired
    AppUserService appUserService;

    @Autowired
    AppRoleService appRoleService;

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
        modelAndView.addObject("user", new AppUser());
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView showFormUser() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("list", appUserService.findAll());
        return modelAndView;
    }

    @ModelAttribute("listRole")
    public Iterable<Role> listRole() {
        return appRoleService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("user", new AppUser());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("user", appUserService.findById(id).get());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("user", appUserService.findById(id).get());
        return modelAndView;
    }

    @GetMapping("/findByName")
    public ModelAndView findByName(@RequestParam("findName") String name) {
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("list", appUserService.findByName(name));
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showFormView(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("user", appUserService.findById(id).get());
        return modelAndView;

    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("user") AppUser appUser) {
//        String fileName = file.getOriginalFilename();
//        try {
//            FileCopyUtils.copy(file.getBytes(), new File(upLoadFile + fileName));
//        } catch (Exception ex) {
//            System.err.println("err upload file");
//        }
//        appUser.setAvatar(fileName);

//       validateUserName.validate(appUser,bindingResult);

//        if (bindingResult.hasFieldErrors()) {
//            ModelAndView modelAndView = new ModelAndView("create");
//            modelAndView.addObject("user", appUser);
//            return modelAndView;
//        }

        long id = 2;
        appUser.setRole(appRoleService.findById(id));
        appUserService.save(appUser);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String edit( @RequestParam("upImg") MultipartFile upImg,@ModelAttribute AppUser appUser) {
        String nameImg = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File(upLoadFile + nameImg));
            appUser.setAvatar(nameImg);
        } catch (IOException e) {
            System.err.println("err upload file");
        }
        appUserService.save(appUser);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        appUserService.delete(appUserService.findById(id).get());
        return "redirect:/";
    }

}
