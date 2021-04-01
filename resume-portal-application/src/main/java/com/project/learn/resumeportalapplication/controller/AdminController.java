package com.project.learn.resumeportalapplication.controller;



import com.project.learn.resumeportalapplication.model.User;
import com.project.learn.resumeportalapplication.model.UserLogin;

import com.project.learn.resumeportalapplication.service.ResumeTemplateService;


import com.project.learn.resumeportalapplication.service.UserLoginDetailsService;
import com.project.learn.resumeportalapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginDetailsService userLoginDetailsService;
    @Autowired
    private BCryptPasswordEncoder enc;
    @Autowired
    private ResumeTemplateService resumeTemplateService;

    @GetMapping("/admin")
    public ModelAndView adminPage(HttpServletRequest request){
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
    public ModelAndView addAdmin(@ModelAttribute("userlogin") UserLogin userlogin) {
        ModelAndView mv =new ModelAndView("adm/addAdmin.html");
        mv.addObject("userlogin",new UserLogin());
        UserLogin ul;
        try {
            ul = userLoginDetailsService.save(userlogin);
            mv.addObject("userlogin",new UserLogin());
            mv.addObject("success",Boolean.TRUE);
        }
        catch (Exception e){
            mv.addObject("error",Boolean.TRUE);
            mv.addObject("userlogin",userlogin);
        }
        return mv;
    }

    @GetMapping("/admin/view-user/{id}")
    public ModelAndView getUser(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("adm/viewUser.html");
        User u = userService.findById(id);
        mv.addObject("user",u);
        return mv;

    }



}
