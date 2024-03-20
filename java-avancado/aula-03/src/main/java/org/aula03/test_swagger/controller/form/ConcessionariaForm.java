package org.aula03.test_swagger.controller.form;

import org.aula03.test_swagger.module.Carro;
import org.aula03.test_swagger.module.Concessionaria;

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
        return new Concessionaria(null, nome);
    }
}
