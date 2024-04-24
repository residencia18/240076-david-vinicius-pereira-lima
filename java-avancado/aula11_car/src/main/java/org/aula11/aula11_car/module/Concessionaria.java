package org.aula11.aula11_car.module;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "CONCESSIONARIA")
public class Concessionaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "nome")
    @NotNull(message = "Nome não pode ser null")
    @NotEmpty(message = "Nome não pode estar em branco")
    @NonNull private String nome;

    @OneToMany(mappedBy = "concessionaria")
    private Set<Carro> carros;

    @Column(name = "servicos")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "CONCESSIONARIA_SERVICE_MAPPING", joinColumns = @JoinColumn(name = "concessionaria_id"), inverseJoinColumns = @JoinColumn(name = "servico_id"))
    private Set<Servico> servicos;

    public void mostrarConcessionaria(){
        System.out.println(toString());
    }

}
