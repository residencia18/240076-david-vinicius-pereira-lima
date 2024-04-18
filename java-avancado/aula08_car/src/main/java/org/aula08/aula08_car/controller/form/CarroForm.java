package org.aula08.aula08_car.controller.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.aula08.aula08_car.module.Concessionaria;
import org.aula08.aula08_car.module.Carro;

@Data
@NoArgsConstructor
public class CarroForm {
    private String placa;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;
    private Concessionaria concessionaria;

    public Carro criarCarro() throws Exception {
        return new Carro(placa, marca, modelo, anoFabricacao);
    }
}
