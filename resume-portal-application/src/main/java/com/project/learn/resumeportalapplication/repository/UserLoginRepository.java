package com.project.learn.resumeportalapplication.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import com.project.learn.resumeportalapplication.model.UserLogin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserLoginRepository extends CrudRepository<UserLogin,Integer> {

    public Optional<UserLogin> findByUsername(String username);

    
}
