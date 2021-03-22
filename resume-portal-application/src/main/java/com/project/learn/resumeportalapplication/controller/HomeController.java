package com.project.learn.resumeportalapplication.controller;


import javax.servlet.http.HttpServletRequest;

import com.project.learn.resumeportalapplication.model.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
    
    @GetMapping({"/home","/"})
    public ModelAndView getHomePage(){
        return new ModelAndView("index.html");
    }
    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView mv=new ModelAndView("register.html");
        mv.addObject("userlogin", new UserLogin());
        return mv;
    }
    @GetMapping("/app/search")
    public ModelAndView search(){
        ModelAndView mv=new ModelAndView("app/searchResult.html");
        return mv;
    }
    @PostMapping(value = "/user/add-details/{name}",params = "addAchievement")
    public ModelAndView addAchievement(@ModelAttribute User user){
        user.getAchievements().add(new Achievement());
        ModelAndView mv;
        System.out.println(user.getId());
        mv=new ModelAndView("user/registrationForm.html");
        mv.addObject("user",user);
        return mv;
    }
    @PostMapping(value = "/user/add-details/{name}",params = "removeAchievement")
    public ModelAndView removeAchievement(@ModelAttribute User user,HttpServletRequest req){
        user.getAchievements().remove(Integer.parseInt(req.getParameter("removeAchievement")));
        ModelAndView mv;
        System.out.println(user.getId());
        mv=new ModelAndView("user/registrationForm.html");
        mv.addObject("user",user);
        return mv;
    }
    @PostMapping(value = "/user/add-details/{name}",params = "addEducation")
    public ModelAndView addEducation(@ModelAttribute User user){
        user.getEducations().add(new Education());
        ModelAndView mv;
        System.out.println(user.getId());
        mv=new ModelAndView("user/registrationForm.html");
        mv.addObject("user",user);
        return mv;
    }
    @PostMapping(value = "/user/add-details/{name}",params = "removeEducation")
    public ModelAndView removeEducation(@ModelAttribute User user,HttpServletRequest req){
        user.getEducations().remove(Integer.parseInt(req.getParameter("removeEducation")));
        ModelAndView mv;
        mv=new ModelAndView("user/registrationForm.html");
        mv.addObject("user",user);
        return mv;
    }
    @PostMapping(value = "/user/add-details/{name}",params = "addExperience")
    public ModelAndView addExperience(@ModelAttribute User user){
        user.getExperiences().add(new Experience());
        ModelAndView mv;
        mv=new ModelAndView("user/registrationForm.html");
        mv.addObject("user",user);
        return mv;
    }
    @PostMapping(value = "/user/add-details/{name}",params = "removeExperience")
    public ModelAndView removeExperience(@ModelAttribute User user,HttpServletRequest req){
        user.getExperiences().remove(Integer.parseInt(req.getParameter("removeExperience")));
        ModelAndView mv;
        mv=new ModelAndView("user/registrationForm.html");
        mv.addObject("user",user);
        return mv;
    }
    @PostMapping(value = "/user/add-details/{name}",params = "addProject")
    public ModelAndView addProject(@ModelAttribute User user){
        user.getProjects().add(new Project());
        ModelAndView mv;
        
        mv=new ModelAndView("user/registrationForm.html");
        mv.addObject("user",user);
        return mv;
    }
    @PostMapping(value = "/user/add-details/{name}",params = "removeProject")
    public ModelAndView removeProject(@ModelAttribute User user,HttpServletRequest req){
        user.getProjects().remove(Integer.parseInt(req.getParameter("removeProject")));
        ModelAndView mv;
        mv=new ModelAndView("user/registrationForm.html");
        mv.addObject("user",user);
        return mv;
    }



}
