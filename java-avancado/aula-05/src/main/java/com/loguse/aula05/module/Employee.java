package com.loguse.aula05.module;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "technicalSkill")
    private String technicalSkill;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "EMPLOYEE_PROJECT_MAPPING", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set <Project> projects;
    public Employee(){}

    public Employee(String name, String email, String technicalSkill) {
        this.name = name;
        this.email = email;
        this.technicalSkill = technicalSkill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTechnicalSkill() {
        return technicalSkill;
    }

    public void setTechnicalSkill(String technicalSkill) {
        this.technicalSkill = technicalSkill;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee [email=" + email + ", id=" + id + ", name=" + name + ", technicalSkill=" + technicalSkill + "]";
    }
}
