package org.aula13.aula13_car.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula13.aula13_car.module.Concessionaria;

@Data
@NoArgsConstructor
public class ConcessionariaDTO {
    private String nome;

    public ConcessionariaDTO(Concessionaria concessionaria) {
        this.nome = concessionaria.getNome();
    }

}
