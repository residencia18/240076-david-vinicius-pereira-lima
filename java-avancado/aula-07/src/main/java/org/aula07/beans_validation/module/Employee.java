package org.aula07.beans_validation.module;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

import java.util.Set;
@Data
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "name")
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @NonNull private String name;

    @Column(name = "email")
    @Email(message = "Email should be valid")
    @NonNull private String email;

    @Column(name = "technicalSkill")
    @NotNull(message = "Technical Skill cannot be null")
    @Size(min = 5 , max = 300 , message = "Technical Skill must to be between 5 and 300 characters")
    @NonNull private String technicalSkill;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "EMPLOYEE_PROJECT_MAPPING", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set <Project> projects;

}
