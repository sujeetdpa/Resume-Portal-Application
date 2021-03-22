package com.project.learn.resumeportalapplication.configuration;

import com.project.learn.resumeportalapplication.service.UserLoginDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserLoginDetailsService userLoginDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginDetailsService)
        .passwordEncoder(getPasswordEncoder());
    }
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
        .antMatchers("/app/**").permitAll()
        .and()
        .formLogin()
        .and()
        .logout()
        .logoutSuccessUrl("/");
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }
    
}
