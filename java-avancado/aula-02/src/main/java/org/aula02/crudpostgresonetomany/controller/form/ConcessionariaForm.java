package org.aula02.crudpostgresonetomany.controller.form;

import org.aula02.crudpostgresonetomany.module.Carro;
import org.aula02.crudpostgresonetomany.module.Concessionaria;

import java.util.List;

public class ConcessionariaForm {
    private String nome;
    private List<Carro> carros;

    public ConcessionariaForm() {
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

    public Concessionaria criarConcessionaria(){
        return new Concessionaria(null, nome, carros);
    }
}
