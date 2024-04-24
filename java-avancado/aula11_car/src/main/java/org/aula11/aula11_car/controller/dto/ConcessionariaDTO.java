package org.aula11.aula11_car.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula11.aula11_car.module.Concessionaria;

@Data
@NoArgsConstructor
public class ConcessionariaDTO {
    private String nome;

    public ConcessionariaDTO(Concessionaria concessionaria) {
        this.nome = concessionaria.getNome();
    }

}
