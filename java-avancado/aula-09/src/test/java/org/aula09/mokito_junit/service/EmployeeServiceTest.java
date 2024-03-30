package org.aula09.mokito_junit.service;

import com.github.javafaker.Faker;
import org.aula09.mokito_junit.module.Employee;
import org.aula09.mokito_junit.repository.EmployeeRepository;
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

public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private Faker faker;

    @BeforeEach
    public void setUp(){
        faker = new Faker(new Locale("en-US"));
        employee = new Employee();
        employee.setId(1);
        employee.setName(faker.name().fullName());
        employee.setEmail(faker.internet().emailAddress());
        employee.setTechnicalSkill(faker.job().position());
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testCreateFakeEmployee(){
        given(employeeRepository.save(any(Employee.class))).willReturn(employee);
        Employee savedEmployee = employeeService.create(employee);
        verify(employeeRepository).save(any(Employee.class));
        assertNotNull(savedEmployee, "O funcionário salvo não pode ser nulo");
        assertEquals(employee.getName(), savedEmployee.getName(), "O nome do funcionário não corresponde ao esperado");
        assertEquals(employee.getEmail(), savedEmployee.getEmail(), "O email do funcionário não corresponde ao esperado");
        assertEquals(employee.getTechnicalSkill(), savedEmployee.getTechnicalSkill(), "A habilidade técnica do funcionário não corresponde ao esperado");
    }

    @Test
    public void shouldUpdateEmployeeSuccessfully(){
        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Jane Doe");
        updatedEmployee.setEmail("jane.doe@example.com");
        updatedEmployee.setTechnicalSkill("Spring Boot");

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        Optional<Employee> result = employeeService.update(employee.getId(), updatedEmployee);

        assertTrue(result.isPresent(), "O funcionário atualizado deve estar presente");
        assertEquals(updatedEmployee.getName(), result.get().getName(), "O nome do funcionário atualizado não corresponde ao esperado");
        assertEquals(updatedEmployee.getEmail(), result.get().getEmail(), "O email do funcionário atualizado não corresponde ao eperado");
        assertEquals(updatedEmployee.getTechnicalSkill(), result.get().getTechnicalSkill(), "A habilidade técnica do funcionário não corresponde ao esperado");

        verify(employeeRepository).findById(employee.getId());
        verify(employeeRepository).save(any(Employee.class));
    }
}
