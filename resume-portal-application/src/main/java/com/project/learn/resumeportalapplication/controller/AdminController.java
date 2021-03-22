package com.project.learn.resumeportalapplication.controller;



import com.project.learn.resumeportalapplication.model.User;
import com.project.learn.resumeportalapplication.model.UserLogin;


import com.project.learn.resumeportalapplication.service.UserLoginDetailsService;
import com.project.learn.resumeportalapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginDetailsService userLoginDetailsService;
    @Autowired
    private BCryptPasswordEncoder enc;

    @GetMapping("/admin")
    public ModelAndView adminPage(){
        return new ModelAndView("adm/adminWelcome.html");
    }

    @GetMapping("/admin/all-user")
    public ModelAndView getUsers() {
        ModelAndView mv = new ModelAndView("adm/allUsers.html");
        Iterable<UserLogin> userLogins = userLoginDetailsService.findAll();
        mv.addObject("userlogins", userLogins);
        return mv;
    }

    @GetMapping("/admin/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        userLoginDetailsService.deleteById(id);
        userService.deleteById(id);
        return new ModelAndView("redirect:/admin/all-user");
    }

    @GetMapping("/admin/add-admin")
    public ModelAndView addAdmin() {
        ModelAndView mv = new ModelAndView("adm/addAdmin.html");
        mv.addObject("userlogin", new UserLogin());
        return mv;
    }

    @PostMapping("/admin/add-admin")
    public UserLogin addAdmin(@ModelAttribute("userlogin") UserLogin userlogin) {
        userlogin.setPassword(enc.encode(userlogin.getPassword()));
        UserLogin ul = userLoginDetailsService.save(userlogin);
        return ul;
    }

    @GetMapping("/admin/view-user/{id}")
    public ModelAndView getUser(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("adm/viewUser.html");
        User u = userService.findById(id);
        mv.addObject("user",u);
        return mv;

    }
}
