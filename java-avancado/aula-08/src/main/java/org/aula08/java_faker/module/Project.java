package org.aula07.beans_validation.module;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "PROJECT")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "projectName")
    @NotNull(message = "Project Name cannot be null")
    @Size(min = 5, max = 200, message = "Project Name must to be between 5 and 200 characters")
    @NonNull private String projectName;

    @Column(name = "technologyUsed")
    @NotNull(message = "Technology Used cannot be null")
    @Size(min = 5, max = 200, message = "Technology Used must to be between 5 and 200 characters")
    @NonNull private String technologyUsed;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "EMPLOYEE_PROJECT_MAPPING", joinColumns = @JoinColumn(name = "project_id"),inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employees;

}
