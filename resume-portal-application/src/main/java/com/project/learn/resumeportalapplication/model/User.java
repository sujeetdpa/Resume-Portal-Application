package com.project.learn.resumeportalapplication.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
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
    @Column(unique = true)
    private String email;
    private String template="defaultResume.html";

    @OneToMany(cascade = CascadeType.ALL)
    private List<Achievement> achievements =new ArrayList<Achievement>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Education> educations =new ArrayList<Education>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Experience> experiences =new ArrayList<Experience>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Project> projects =new ArrayList<Project>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Link> links=new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
