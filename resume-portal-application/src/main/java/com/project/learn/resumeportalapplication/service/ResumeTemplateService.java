package com.project.learn.resumeportalapplication.service;

import com.project.learn.resumeportalapplication.repository.ResumeTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeTemplateService {
    @Autowired
    private ResumeTemplateRepository resumeTemplateRepository;
    public List<String> getTemplates(){
        return resumeTemplateRepository.getTemplates();
    }
    public void saveTemplate(String name){
        resumeTemplateRepository.setTemplates(name);
    }


}
