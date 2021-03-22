package com.project.learn.resumeportalapplication.service;

import java.util.List;
import java.util.Optional;

import com.project.learn.resumeportalapplication.model.User;
import com.project.learn.resumeportalapplication.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User save(User user) {
        return userRepository.save(user);
    }
    public User findByUsername( String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new IllegalStateException("User does not exixst with username:"+username));
    }
    public User findById(int id){
        return userRepository.findById(id).orElseThrow(()->new IllegalStateException("User not found with id:"+id));
    }

    public List<String> searchUser(String token){
        token="%"+token+"%";
        List<String> usernames=userRepository.searchUser(token);
        return usernames;
    }
    public void deleteById(int id){
        userRepository.findById(id).ifPresent(user -> userRepository.delete(user));
    }

}
