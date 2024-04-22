package org.aula10.aula10_car.controller.form;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula10.aula10_car.module.Concessionaria;

@Data
@NoArgsConstructor
public class ConcessionariaForm {
    private String nome;
    public Concessionaria criarConcessionaria(){
        return new Concessionaria(nome);
    }
}
