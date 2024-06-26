package org.aula05.aula05_car.controller.dto;


import org.aula05.aula05_car.module.Concessionaria;

public class ConcessionariaDTO {
    private String nome;

    public ConcessionariaDTO() {
    }

    public ConcessionariaDTO(Concessionaria concessionaria) {
        this.nome = concessionaria.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String toString(){
        return "Concessionária: "+nome;
    }
}
