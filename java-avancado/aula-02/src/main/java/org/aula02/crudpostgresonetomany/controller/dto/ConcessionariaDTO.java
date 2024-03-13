package org.aula02.crudpostgresonetomany.controller.dto;

import jakarta.persistence.OneToMany;
import org.aula02.crudpostgresonetomany.module.Carro;
import org.aula02.crudpostgresonetomany.module.Concessionaria;

import java.util.List;

public class ConcessionariaDTO {
    private String nome;
    private List<Carro> carros;

    public ConcessionariaDTO() {
    }

    public ConcessionariaDTO(Concessionaria concessionaria) {
        this.nome = concessionaria.getNome();
        this.carros = concessionaria.getCarros();
    }

    public String getNome() {
        return nome;
    }

    public List<Carro> getCarros() {
        return carros;
    }
    public String toString(){
        String str = "";
        for(Carro carro : carros)
            str += carro.toString();
        return "Concessionária: "+nome+
                "\nCarros:\n"+str;
    }
}
