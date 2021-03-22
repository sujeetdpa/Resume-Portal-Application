package com.project.learn.resumeportalapplication.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class User {
    @Id
    private int id;
    private String firstName;

    private String lastName;
    @Column(unique = true)

    private String username;
    @OneToMany(cascade = CascadeType.ALL)

    private List<Achievement> achievements =new ArrayList<Achievement>();
    @OneToMany(cascade = CascadeType.ALL)

    private List<Education> educations =new ArrayList<Education>();
    @OneToMany(cascade = CascadeType.ALL)

    private List<Experience> experiences =new ArrayList<Experience>();
    @OneToMany(cascade = CascadeType.ALL)

    private List<Project> projects =new ArrayList<Project>();
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Achievement> getAchievements() {
        return  achievements;
    }

    public void setAchievements(ArrayList<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(ArrayList<Education> educations) {
        this.educations = educations;
    }

    public List<Experience> getExperiences() {
        return  experiences;
    }

    public void setExperiences(ArrayList<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Project> getProjects() {
        return  projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
