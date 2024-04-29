package org.aula13.aula13_car.controller.form;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula13.aula13_car.module.Concessionaria;

@Data
@NoArgsConstructor
public class ConcessionariaForm {
    private String nome;
    public Concessionaria criarConcessionaria(){
        return new Concessionaria(nome);
    }
}
