package org.aula09.aula09_car.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula09.aula09_car.module.Concessionaria;

@Data
@NoArgsConstructor
public class ConcessionariaDTO {
    private String nome;

    public ConcessionariaDTO(Concessionaria concessionaria) {
        this.nome = concessionaria.getNome();
    }

}
