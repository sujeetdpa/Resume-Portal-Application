package com.project.learn.resumeportalapplication.repository;

import javax.transaction.Transactional;

import com.project.learn.resumeportalapplication.model.Education;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EducationRepository extends CrudRepository<Education,Integer> {
    
}
