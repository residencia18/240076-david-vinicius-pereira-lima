package org.aula09.mokito_junit.service;

import org.aula09.mokito_junit.module.Project;
import org.aula09.mokito_junit.repository.ProjectRepository;

import java.util.Optional;

public class ProjectService {
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public Project create(Project project){return projectRepository.save(project);}

    public Optional<Project> update(Integer projectId, Project updatedProject){
        Optional<Project> existingProject = projectRepository.findById(projectId);
        if (existingProject.isPresent()){
            updatedProject.setId(projectId);
            return Optional.of(projectRepository.save(updatedProject));
        }
        return Optional.empty();
    }
}
