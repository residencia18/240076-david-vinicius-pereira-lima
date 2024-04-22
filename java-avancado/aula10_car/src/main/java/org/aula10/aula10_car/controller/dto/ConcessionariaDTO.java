package org.aula10.aula10_car.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula10.aula10_car.module.Concessionaria;

@Data
@NoArgsConstructor
public class ConcessionariaDTO {
    private String nome;

    public ConcessionariaDTO(Concessionaria concessionaria) {
        this.nome = concessionaria.getNome();
    }

}
