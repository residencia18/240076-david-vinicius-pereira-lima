package org.aula08.aula08_car.controller.form;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula08.aula08_car.module.Concessionaria;

@Data
@NoArgsConstructor
public class ConcessionariaForm {
    private String nome;
    public Concessionaria criarConcessionaria(){
        return new Concessionaria(nome);
    }
}
