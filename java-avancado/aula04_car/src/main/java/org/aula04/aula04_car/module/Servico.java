package org.aula04.aula04_car.module;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "SERVICO")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "duracao")
    private Integer duracao;

    @Column(name = "preco")
    private Long preco;

    @Column(name = "concessionarias")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "CONCESSIONARIA_SERVICE_MAPPING", joinColumns = @JoinColumn(name = "servico_id"), inverseJoinColumns = @JoinColumn(name = "concessionaria_id"))
    private Set<Concessionaria>concessionarias;

    public Servico() {}

    public Servico(String descricao, Integer duracao, Long preco) {
        this.descricao = descricao;
        this.duracao = duracao;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Concessionaria> getConcessionarias() {
        return concessionarias;
    }

    public void setConcessionarias(Set<Concessionaria> concessionarias) {
        this.concessionarias = concessionarias;
    }

    public Long getPreco() {
        return preco;
    }

    public void setPreco(Long preco) {
        this.preco = preco;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString(){
        return "Servico [id=" + id + ", descricao="+ descricao + ", duracao="+ duracao + ", preco="+ preco+ "]";
    }
}
