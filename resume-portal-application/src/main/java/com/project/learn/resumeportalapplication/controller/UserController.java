package com.project.learn.resumeportalapplication.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.learn.resumeportalapplication.model.User;
import com.project.learn.resumeportalapplication.model.UserLogin;
import com.project.learn.resumeportalapplication.service.ResumeTemplateService;
import com.project.learn.resumeportalapplication.service.UserLoginDetailsService;
import com.project.learn.resumeportalapplication.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginDetailsService userLoginDetailsService;
    @Autowired
    private BCryptPasswordEncoder enc;
    @Autowired
    private ResumeTemplateService resumeTemplateService;

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute UserLogin userlogin) {
        ModelAndView mv=new ModelAndView("register.html");
        try{
        UserLogin ul=userLoginDetailsService.save(userlogin);
        mv.addObject("success",Boolean.TRUE);
        mv.addObject("userlogin",new UserLogin());
        }
        catch (Exception e){
            mv.addObject("userlogin",userlogin);
            mv.addObject("error",Boolean.TRUE);
        }
        return  mv;
        
    }

    @GetMapping("/user/add-details/{username}")
    public ModelAndView addDetails(@PathVariable String username,HttpServletRequest request) throws IllegalAccessException {
        ModelAndView mv=new ModelAndView("user/registrationForm.html");
        User user;
        if(request.getRemoteUser().equals(username)){
            UserLogin userlogin=userLoginDetailsService.findByUsername(username);
            try {
                user = userService.findByUsername(username);
            }catch (Exception e){
                user=new User();
            }
            user.setId(userlogin.getId());
            user.setFirstName(userlogin.getFirstName());
            user.setLastName(userlogin.getLastName());
            user.setUsername(userlogin.getUsername());
            user.setEmail(userlogin.getEmail());
            mv.addObject("user",user);
            mv.addObject("templates",resumeTemplateService.getTemplates());
        }
        else {
            mv.addObject("user",new User());
            mv.addObject("unauthorised", true);
            mv.addObject("templates",resumeTemplateService.getTemplates());
        }
        return mv;
    }
    @PostMapping("/user/add-details/{username}")
    public ModelAndView addDetails(@ModelAttribute User user, HttpServletRequest request, @PathVariable("username") String username) {
       ModelAndView mv=new ModelAndView("user/registrationForm.html");
        User u=null;
        if(request.getRemoteUser().equals(username)) {
            try{
            u = userService.save(user);

            userLoginDetailsService.setDetailsUpdated(user.getId(),true);
            mv.addObject("user",u);
            mv.addObject("success",true);
            mv.addObject("templates",resumeTemplateService.getTemplates());
            }catch (Exception e){
                userService.delete(u);
                userLoginDetailsService.setDetailsUpdated(u.getId(),false);
                mv.addObject("user",user);
                mv.addObject("error",true);
                mv.addObject("templates",resumeTemplateService.getTemplates());
            }
        }
        else{
            mv.addObject("user",user);
            mv.addObject("unauthorised",true);
            mv.addObject("templates",resumeTemplateService.getTemplates());
        }
        return  mv;

    }

    @GetMapping("/app/view/{username}")
    public ModelAndView viewUser(@PathVariable String username,HttpServletResponse response){

        ModelAndView mv=new ModelAndView();
        mv.setViewName("app/defaultResume.html");
        mv.addObject("templates",resumeTemplateService.getTemplates());
        try{
            UserLogin userLogin=userLoginDetailsService.findByUsername(username);
            if(userLogin.isDetailsUpdated()) {
                User user=userService.findByUsername(username);
                mv.setViewName("app/"+user.getTemplate());
                mv.addObject("user", user);
            }
            else{
                User user=new User();
                user.setEmail(userLogin.getEmail());
                user.setFirstName(userLogin.getFirstName());
                user.setLastName(userLogin.getLastName());
                user.setAddress(null);
                mv.addObject("user",user);
                mv.addObject("dnp",true);
            }
        }
        catch (Exception e){
            mv.addObject("user",new User());
            mv.addObject("unf",true);

        }
        return mv;
    }
    @GetMapping("/app/view/{username}/{templateName}")
    public ModelAndView viewUser(@PathVariable String username,@PathVariable String templateName){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("app/"+templateName);
        mv.addObject("templates",resumeTemplateService.getTemplates());
        try{
            UserLogin userLogin=userLoginDetailsService.findByUsername(username);
            if(userLogin.isDetailsUpdated()) {
                User user=userService.findByUsername(username);
                mv.addObject("user", user);
            }
            else{
                User user=new User();
                user.setEmail(userLogin.getEmail());
                user.setFirstName(userLogin.getFirstName());
                user.setLastName(userLogin.getLastName());
                mv.addObject("user",user);
                mv.addObject("dnp",true);
            }
        }
        catch (Exception e){
            mv.addObject("user",new User());
            mv.addObject("unf",true);

        }
        return mv;
    }
    @GetMapping(value = "/app/search", params = "search")
    public ModelAndView searchUser(HttpServletRequest req) {
        String token=req.getParameter("search");
        List<String> usernames=userService.searchUser(token);
        ModelAndView mv=new ModelAndView("app/searchResult.html");
        mv.addObject("usernames", usernames);
        return mv;

    }
    @GetMapping("/user/dashboard")
    public ModelAndView getDashboard(HttpSession session){
        return new ModelAndView("user/dashboard.html");
    }
    
}
