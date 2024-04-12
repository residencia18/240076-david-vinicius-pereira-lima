package org.aula04.aula04_car.module;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CONCESSIONARIA")
public class Concessionaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "concessionaria")
    private List<Carro> carros;

    @Column(name = "servicos")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "CONCESSIONARIA_SERVICE_MAPPING", joinColumns = @JoinColumn(name = "concessionaria_id"), inverseJoinColumns = @JoinColumn(name = "servico_id"))
    private Set<Servico> servicos;

    public Concessionaria() {
    }

    public Concessionaria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }
    public String toString(){
        String str = "";
        for(Carro carro : carros)
            str += carro.toString();
        return "Concessionária: "+nome+
                "\nCarros:\n"+str;
    }

    public void mostrarConcessionaria(){
        System.out.println(toString());
    }

}
