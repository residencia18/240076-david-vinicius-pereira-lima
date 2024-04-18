package org.aula09.aula09_car.controller.form;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula09.aula09_car.module.Concessionaria;

@Data
@NoArgsConstructor
public class ConcessionariaForm {
    private String nome;
    public Concessionaria criarConcessionaria(){
        return new Concessionaria(nome);
    }
}
