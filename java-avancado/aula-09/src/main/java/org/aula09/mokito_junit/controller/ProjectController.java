package org.aula09.mokito_junit.controller;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.aula09.mokito_junit.module.Employee;
import org.aula09.mokito_junit.module.Project;
import org.aula09.mokito_junit.repository.EmployeeRepository;
import org.aula09.mokito_junit.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@RestController
@RequestMapping("api/project")

public class ProjectController {
    private static Logger log = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/createFakeProject/")
    public String createFakeProject(){
        Faker usFaker = new Faker(new Locale("en-US"));
        log.info("Create a new Project using JavaFaker.");
        Project project = new Project(StringUtils.capitalize(usFaker.app().name()),StringUtils.capitalize(usFaker.company().industry()));
        project = projectRepository.save(project);
        log.info("Saved Project:\t"+project+"\n");
        return "Project saved!!!";
    }
    @PostMapping("/createProject/")
    public String createProject(@RequestBody Project entity){
        log.info("Create a new Project.");
        Project project = new Project(entity.getProjectName(), entity.getTechnologyUsed());
        project = projectRepository.save(project);
        log.info("Saved Project:\t"+project+"\n");
        return "Project saved!!!";
    }
    @PostMapping("/createProjectForEmployees/")
    public String createProjectForEmployees(@RequestBody Project entity){
        log.info("Create a new Project and add existing Employees into this Project.");
        int empId = 1;
        Employee employee1 = employeeRepository.getReferenceById(empId);
        log.info("Employee details:\t"+employee1.toString()+"\n");
        empId = 2;
        Employee employee2 = employeeRepository.getReferenceById(empId);
        log.info("Employee details:\t"+employee2.toString()+"\n");

        Project project = new Project(entity.getProjectName(), entity.getTechnologyUsed());
        Set<Employee> employees = new HashSet<>();
        employees.add(employee1);
        employees.add(employee2);

        project.setEmployees(employees);
        project = projectRepository.save(project);
        log.info("\nSaved Project :: " + project + "\n");

        return "Project saved!!!";
    }

    @PostMapping("/assignProjectToEmployees/{projId}/{empId}")
    public String assignProjectToEmployees(@PathVariable(name = "projId") Integer projId, @PathVariable(name = "empId") Integer empId){
        log.info("\nFetch existing Project and add existing Employee into this Project." + "\n");

        // get Employee
        Employee employee = this.employeeRepository.getById(empId);
        log.info("\nEmployee details :: " + employee.toString() + "\n");

        // new Project
        Project project = this.projectRepository.getById(projId);
        log.info("\nProject details :: " + project.toString() + "\n");

        // create Employee set
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);

        // assign Employee Set to Project
        project.setEmployees(employees);

        // save Project
        project = projectRepository.save(project);
        log.info("\nSaved Project :: " + project + "\n");

        return "Project saved!!!";
    }

    @GetMapping(value = "/getProject/{projId}")
    public String getProject(@PathVariable(name = "projId") Integer projId) {
        log.info("Fetch Project and its Employees." + "\n");

        // get Project details
        Project project = this.projectRepository.getById(projId);
        log.info("\nProject details :: " + project.toString() + "\n");
        log.info("\nEmployees details :: " + project.getEmployees() + "\n");

        log.info("Done!!!\n");

        return "Project fetched successfully!!!";
    }
}


