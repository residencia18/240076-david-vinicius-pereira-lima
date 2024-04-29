package org.aula14.aula14_car.module;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "SERVICO")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "descricao")
    @NotNull(message = "Descriçao não pode ser null")
    @NotEmpty(message = "Descrição não pode estar em branco")
    @NonNull private String descricao;

    @Column(name = "duracao")
    @PositiveOrZero(message = "A duração tem que ser maior que 0 segundos")
    @NonNull private Integer duracao;

    @Column(name = "preco")
    @PositiveOrZero(message = "Preço tem que ser maior que 0")
    @NonNull private Double preco;

    @Column(name = "concessionarias")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "CONCESSIONARIA_SERVICE_MAPPING", joinColumns = @JoinColumn(name = "servico_id"), inverseJoinColumns = @JoinColumn(name = "concessionaria_id"))
    private Set<Concessionaria>concessionarias;

    public void mostrarServico(){
        System.out.println(this.toString());
    }
}
