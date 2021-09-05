package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import socialNetwork.model.AppUser;
import socialNetwork.model.Role;
import socialNetwork.service.Implement.AppRoleService;
import socialNetwork.service.Implement.AppUserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AppUserController {
    @Autowired
    AppUserService appUserService;

    @Autowired
    AppRoleService appRoleService;

    @RequestMapping("/users")
    public ModelAndView showFormUser(){
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("list",appUserService.findAll());
        return modelAndView;
    }

    @ModelAttribute("listRole")
    public Iterable<Role> listRole(){
        return appRoleService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("user",new AppUser());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("user",appUserService.findById(id));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("user",appUserService.findById(id));
        return modelAndView;
    }

    @GetMapping("/findByName")
    public ModelAndView findByName(@RequestParam("findName") String name) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("list", appUserService.findByName(name));
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showFormView(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("user", appUserService.findById(id));
        return modelAndView;

    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute AppUser appUser, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            modelAndView.addObject("user",appUser);
            return modelAndView;
        }
        appUserService.save(appUser);
        ModelAndView modelAndView = new ModelAndView("redirect:/users");
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute AppUser appUser) {
        appUserService.save(appUser);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        appUserService.delete(appUserService.findById(id).get());
        return "redirect:/users";
    }
}
