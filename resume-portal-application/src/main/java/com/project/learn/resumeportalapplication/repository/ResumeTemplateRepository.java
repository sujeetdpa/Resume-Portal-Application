package com.project.learn.resumeportalapplication.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ResumeTemplateRepository  {
    private List<String> templates=List.of("defaultResume.html","template2.html");

    public List<String> getTemplates() {
        return templates;
    }

    public void setTemplates(String templates) {
        this.templates.add(templates);
    }
}
