package com.project.learn.resumeportalapplication.service;

import com.project.learn.resumeportalapplication.model.UserLogin;
import com.project.learn.resumeportalapplication.model.UserLoginDetails;
import com.project.learn.resumeportalapplication.repository.UserLoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginDetailsService implements UserDetailsService {

    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private BCryptPasswordEncoder enc;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin userLogin=userLoginRepository.findByUsername(username)
                .orElseThrow(()->new IllegalStateException("User does not exixst with username:"+username));;
        return new UserLoginDetails(userLogin);
    }

    public UserLogin save(UserLogin userlogin) {
        userlogin.setPassword(enc.encode(userlogin.getPassword()));
        UserLogin ul=userLoginRepository.save(userlogin);
        return ul;
    }
    public UserLogin findByUsername(String username){
        return userLoginRepository.findByUsername(username)
                .orElseThrow(()->new IllegalStateException("User does not exixst with username:"+username));
    }
    public Iterable<UserLogin> findAll(){
       Optional<Iterable<UserLogin>> allUser= Optional.of(userLoginRepository.findAll());
       return allUser.orElseThrow(()->new IllegalStateException("No User Found...."));
    }
    public void deleteById(int id){
        userLoginRepository.findById(id).ifPresent(userLogin -> {
            userLoginRepository.delete(userLogin);
        }
        );
    }
    public boolean setDetailsUpdated(int id,boolean detailsUpdated){
        int x=userLoginRepository.setDetailsUpdated(id,detailsUpdated);
        if(x!=0){
            return false;
        }
        return true;
    }
}
