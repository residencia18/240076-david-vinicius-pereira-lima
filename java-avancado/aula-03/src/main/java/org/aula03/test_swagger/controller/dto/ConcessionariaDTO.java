package org.aula03.test_swagger.controller.dto;

import org.aula03.test_swagger.module.Carro;
import org.aula03.test_swagger.module.Concessionaria;

import java.util.List;

public class ConcessionariaDTO {
    private String nome;
    private List<Carro> carros;

    public ConcessionariaDTO() {
    }

    public ConcessionariaDTO(Concessionaria concessionaria) {
        this.nome = concessionaria.getNome();
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
