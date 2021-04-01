package com.project.learn.resumeportalapplication.repository;

import javax.transaction.Transactional;

import com.project.learn.resumeportalapplication.model.Project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProjectRepository extends CrudRepository<Project,Integer>{
    
}
