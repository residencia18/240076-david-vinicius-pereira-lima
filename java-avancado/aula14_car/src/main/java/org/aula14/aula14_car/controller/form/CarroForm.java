package org.aula14.aula14_car.controller.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula14.aula14_car.module.Carro;
import org.aula14.aula14_car.module.Concessionaria;

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
