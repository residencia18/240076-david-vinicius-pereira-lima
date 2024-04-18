package org.aula08.aula08_car.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula08.aula08_car.module.Carro;

@Data
@NoArgsConstructor

public class CarroDTO {
    private String placa;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;
    private ConcessionariaDTO concessionariaDTO;


    public CarroDTO(Carro carro) {
        this.placa = carro.getPlaca();
        this.marca = carro.getMarca();
        this.modelo = carro.getModelo();
        this.anoFabricacao = carro.getAnoFabricacao();
        this.concessionariaDTO.setNome(carro.getConcessionaria().getNome());
    }

    public void mostrarCarro(){
        System.out.println(toString());
    }
}