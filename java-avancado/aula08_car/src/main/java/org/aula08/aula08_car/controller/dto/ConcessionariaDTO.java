package org.aula08.aula08_car.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula08.aula08_car.module.Concessionaria;
@Data
@NoArgsConstructor
public class ConcessionariaDTO {
    private String nome;

    public ConcessionariaDTO(Concessionaria concessionaria) {
        this.nome = concessionaria.getNome();
    }

}
