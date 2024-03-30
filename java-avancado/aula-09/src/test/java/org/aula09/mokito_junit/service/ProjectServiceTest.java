package org.aula09.mokito_junit.service;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.aula09.mokito_junit.module.Project;
import org.aula09.mokito_junit.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProjectServiceTest {
    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectService projectService;
    private Project project;
    private Faker faker;

    @BeforeEach
    public void setUp(){
        faker = new Faker(new Locale("en-US"));
        project = new Project();
        project.setId(1);
        project.setProjectName(StringUtils.capitalize(faker.app().name()));
        project.setTechnologyUsed(StringUtils.capitalize(faker.company().industry()));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFakeProject(){
        given(projectRepository.save(any(Project.class))).willReturn(project);
        Project savedProject = projectService.create(project);
        verify(projectRepository).save(any(Project.class));
        assertNotNull(savedProject, "O projeto salvo não pode ser nulo");
        assertEquals(project.getProjectName(), savedProject.getProjectName(), "O nome do projeto não corresponde ao esperado");
        assertEquals(project.getTechnologyUsed(), savedProject.getTechnologyUsed(), "A tecnologia usada no projeto não corresponde ao esperado");
    }

    @Test
    public void shouldUpdateProjectSucessfully(){
        Project updatedProject = new Project();
        updatedProject.setProjectName("Projeto A");
        updatedProject.setTechnologyUsed("Java Spring Boot");

        when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(updatedProject);

        Optional<Project> result = projectService.update(project.getId(), updatedProject);

        assertTrue(result.isPresent(), "O projeto atualizado deve existir");
        assertEquals(updatedProject.getProjectName(), result.get().getProjectName(), "O nome do projeto atualizado não corresponde ao esperado");
        assertEquals(updatedProject.getTechnologyUsed(), result.get().getTechnologyUsed(), "A tecnologia usada no projeto atualizado não corresponde ao esperado");

        verify(projectRepository).findById(project.getId());
        verify(projectRepository).save(any(Project.class));
    }
}
