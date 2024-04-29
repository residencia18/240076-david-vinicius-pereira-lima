package org.aula13.aula13_car.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula13.aula13_car.module.Carro;

import java.util.Optional;

@Data
@NoArgsConstructor

public class CarroDTO {
    private String placa;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;
    private ConcessionariaDTO concessionariaDTO;


    public CarroDTO(Optional <Carro> carro) {
        this.placa = carro.get().getPlaca();
        this.marca = carro.get().getMarca();
        this.modelo = carro.get().getModelo();
        this.anoFabricacao = carro.get().getAnoFabricacao();
        this.concessionariaDTO.setNome(carro.get().getConcessionaria().getNome());
    }

    public void mostrarCarro(){
        System.out.println(toString());
    }
}