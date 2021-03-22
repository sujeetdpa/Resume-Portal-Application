package com.project.learn.resumeportalapplication.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginDetailsService userLoginDetailsService;
    @Autowired
    private BCryptPasswordEncoder enc;

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute UserLogin userlogin) {
        ModelAndView mv=new ModelAndView("register.html");
        UserLogin ul=userLoginDetailsService.save(userlogin);
        mv.addObject("success",ul);
        mv.addObject("userlogin",new UserLogin());
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
            mv.addObject("user",user);
        }
        else
            throw new IllegalAccessException("You are not authorized..");
        return mv;
    }
    @PostMapping("/user/add-details/{username}")
    public ModelAndView addDetails(@ModelAttribute User user, HttpServletRequest request, @PathVariable("username") String username) throws IllegalAccessException {
       ModelAndView mv=new ModelAndView("user/registrationForm.html");
        if(request.getRemoteUser().equals(username)) {
            User u = userService.save(user);
            mv.addObject("user",u);
            mv.addObject("success",u);
            return mv;
        }
        else
            throw new IllegalAccessException("You are not authorized..");

    }
    @GetMapping("/user/edit/{username}")
     public ModelAndView editUser(@PathVariable String username,HttpServletRequest request) throws IllegalAccessException {
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
            mv.addObject("user",user);
        }
        else
            throw new IllegalAccessException("You are not authorized..");
        return mv;
           
    }
    @GetMapping("/app/view/{username}")
    public ModelAndView viewUser(@PathVariable String username){
        User user=userService.findByUsername(username);
        String model="app/tempelate1.html";
        ModelAndView mv=new ModelAndView(model);
        mv.addObject("user",user);
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
    public ModelAndView getDashboard(HttpServletRequest request){
        return new ModelAndView("/user/dashboard");
    }
    
}
