package org.aula06.aula06_car.controller.form;


import org.aula06.aula06_car.module.Concessionaria;

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
        return new Concessionaria(nome);
    }
}
