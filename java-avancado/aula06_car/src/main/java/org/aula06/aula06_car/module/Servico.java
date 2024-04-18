package org.aula06.aula06_car.module;

import jakarta.persistence.*;
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
    @NonNull private String descricao;

    @Column(name = "duracao")
    @NonNull private Integer duracao;

    @Column(name = "preco")
    @NonNull private Long preco;

    @Column(name = "concessionarias")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "CONCESSIONARIA_SERVICE_MAPPING", joinColumns = @JoinColumn(name = "servico_id"), inverseJoinColumns = @JoinColumn(name = "concessionaria_id"))
    private Set<Concessionaria>concessionarias;

    public void mostrarServico(){
        System.out.println(this.toString());
    }
}
