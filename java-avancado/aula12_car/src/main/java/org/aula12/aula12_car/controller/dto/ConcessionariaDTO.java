package org.aula12.aula12_car.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula12.aula12_car.module.Concessionaria;

@Data
@NoArgsConstructor
public class ConcessionariaDTO {
    private String nome;

    public ConcessionariaDTO(Concessionaria concessionaria) {
        this.nome = concessionaria.getNome();
    }

}
