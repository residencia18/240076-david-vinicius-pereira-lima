package org.aula11.aula11_car.controller.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula11.aula11_car.module.Carro;
import org.aula11.aula11_car.module.Concessionaria;

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
