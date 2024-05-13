package org.aula05.aula05_car.controller.form;

import org.aula05.aula05_car.module.Concessionaria;

import java.util.List;

public class ConcessionariaForm {
    private String nome;

    public ConcessionariaForm() {}

    public ConcessionariaForm(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Concessionaria criarConcessionaria(){
        return new Concessionaria(null, nome);
    }
}
