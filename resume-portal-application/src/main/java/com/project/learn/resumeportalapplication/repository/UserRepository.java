package com.project.learn.resumeportalapplication.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.project.learn.resumeportalapplication.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {

	public Optional<User> findByUsername(String username);

	@Query(value = "select username from user where username like :token", nativeQuery = true)
    public List<String> searchUser(@Param("token") String token);
}
