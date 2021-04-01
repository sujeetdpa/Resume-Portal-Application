package com.project.learn.resumeportalapplication.repository;

import java.util.Optional;

import javax.persistence.NamedQuery;
import javax.transaction.Transactional;

import com.project.learn.resumeportalapplication.model.UserLogin;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserLoginRepository extends CrudRepository<UserLogin,Integer> {

    public Optional<UserLogin> findByUsername(String username);
    @Modifying
    @Query(value = "update user_login set details_updated= :detailsUpdated where id= :id",nativeQuery = true)
    public int setDetailsUpdated(@Param("id") int id,@Param("detailsUpdated") boolean detailsUpdated);

    
}
