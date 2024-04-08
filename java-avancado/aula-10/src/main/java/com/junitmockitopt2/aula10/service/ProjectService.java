package com.junitmockitopt2.aula10.service;



import com.junitmockitopt2.aula10.module.Project;
import com.junitmockitopt2.aula10.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public Project create(Project project){return projectRepository.save(project);}
    public List<Project> findAll(){
        return projectRepository.findAll();
    }
    public Optional<Project> findById(Integer id){
        return projectRepository.findById(id);
    }

    public Optional<Project> findByProjectName(String name){
        return projectRepository.findByProjectName(name);
    }

    public Optional<Project> update(Integer projectId, Project updatedProject){
        return projectRepository.findById(projectId).
                map(project -> {
                    project.setProjectName(updatedProject.getProjectName());
                    project.setTechnologyUsed(updatedProject.getTechnologyUsed());
                    return projectRepository.save(project);
                });
    }

    public void deleteById(Integer id){
        projectRepository.deleteById(id);
    }
}
