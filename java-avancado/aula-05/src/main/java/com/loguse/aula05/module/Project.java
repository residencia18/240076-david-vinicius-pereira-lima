package com.loguse.aula05.module;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PROJECT")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "projectName")
    private String projectName;

    @Column(name = "technologyUsed")
    private String technologyUsed;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "EMPLOYEE_PROJECT_MAPPING", joinColumns = @JoinColumn(name = "project_id"),inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employees;

    public Project() {
    }

    public Project(String projectName, String technologyUsed) {
        this.projectName = projectName;
        this.technologyUsed = technologyUsed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTechnologyUsed() {
        return technologyUsed;
    }

    public void setTechnologyUsed(String technologyUsed) {
        this.technologyUsed = technologyUsed;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    @Override
    public String toString() {
        return "Project [id=" + id + ", projectName=" + projectName + ", technologyUsed=" + technologyUsed + "]";
    }
}
