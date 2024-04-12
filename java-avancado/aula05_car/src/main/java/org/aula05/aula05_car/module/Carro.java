package org.aula04.aula04_car.module;

import jakarta.persistence.*;
@Entity
@Table(name = "CARRO")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "placa")
    private String placa;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "anoFabricacao")
    private Integer anoFabricacao;

    @ManyToOne
    @JoinColumn(name = "concessionaria_id")
    private Concessionaria concessionaria;

    public Carro() {}

    public Carro(Long id, String placa, String marca, String modelo, Integer anoFabricacao, Concessionaria concessionaria) throws Exception {
        this.id = id;
        this.setPlaca(placa);
        this.setMarca(marca);
        this.modelo = modelo;
        this.setAnoFabricacao(anoFabricacao);
        this.concessionaria = concessionaria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) throws Exception{
        if(placa == null || placa.isEmpty())
            throw new Exception("Placa não pode estar vazia ou nula");
        else if (placa.length() != 7)
            throw new Exception("Tamanho da placa é diferente de 7");
        else if (!verificaPlaca(placa))
            throw new Exception("Placa não segue padrão correto");
        else
            this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) throws Exception {
        if(marca == null || marca.isEmpty())
            throw new Exception("Marca não pode ser nula ou vazia");
        else if(marca.chars().anyMatch(Character::isDigit))
            throw new Exception("Marca não pode conter dígitos");
        else if (marca.length() < 3)
            throw new Exception("Tamanho da marca menor que 3");
        else if (!verificaMarca(marca))
            throw new Exception("Marca não segue o padrão correto");
        else
            this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) throws Exception{
        if (anoFabricacao == null)
            throw new Exception("Ano de fabricação não pode ser nulo");
        else if (anoFabricacao < 0)
            throw new Exception("Ano de fabricação não pode ser negativo");
        else if (anoFabricacao < 1960)
            throw new Exception("Ano de fabricação abaixo de 1960");
        else if (anoFabricacao > 2999)
            throw new Exception("Ano de fabricação acima de 2999");
        else
            this.anoFabricacao = anoFabricacao;
    }

    public Concessionaria getConcessionaria() {
        return concessionaria;
    }

    public void setConcessionaria(Concessionaria concessionaria) {
        this.concessionaria = concessionaria;
    }

    public String toString(){
        return "Carro:\n"+
                "Placa:\t"+placa+
                "\nMarca:\t"+marca+
                "\nModelo:\t"+modelo+
                "\nAno de Lançamento:\t"+anoFabricacao+"\n\n";
    }

    public void mostrarCarro(){
        System.out.println(toString());
    }

    public boolean verificaPlaca(String placa){
        String regex = "[A-Z]{3}\\d[A-Z]\\d{2}$";
        return placa.matches(regex);
    }

    public boolean verificaMarca(String nome){
        return nome.matches("[A-Z][a-zA-Z]+$");
    }

}