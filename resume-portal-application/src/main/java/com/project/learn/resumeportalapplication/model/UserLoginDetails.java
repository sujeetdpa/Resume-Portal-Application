package com.project.learn.resumeportalapplication.model;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserLoginDetails implements UserDetails, Principal {

    private UserLogin userLogin;
    private List<SimpleGrantedAuthority> authority;

    public UserLoginDetails(UserLogin userLogin){
        this.userLogin=userLogin;
        this.authority=Arrays.asList(this.userLogin.getRoles().split(",")).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return this.authority;
    }

    @Override
    public String getPassword() {
        return this.userLogin.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.userLogin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public String getName() {
        return this.userLogin.getFirstName() + " " + this.userLogin.getLastName();
    }
    
}
