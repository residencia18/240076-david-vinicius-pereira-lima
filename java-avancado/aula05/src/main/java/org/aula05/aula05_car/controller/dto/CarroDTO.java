package org.aula04.aula04_car.controller.dto;


import org.aula04.aula04_car.module.Carro;
import org.aula04.aula04_car.module.Concessionaria;

public class CarroDTO {
    private String placa;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;
    private ConcessionariaDTO concessionariaDTO;

    public CarroDTO() {}

    public CarroDTO(Carro carro) {
        this.placa = carro.getPlaca();
        this.marca = carro.getMarca();
        this.modelo = carro.getModelo();
        this.anoFabricacao = carro.getAnoFabricacao();
        this.concessionariaDTO.setNome(carro.getConcessionaria().getNome());
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public String toString(){
        return "Carro:\n"+
                "Placa:\t"+placa+
                "\nMarca:\t"+marca+
                "\nModelo:\t"+modelo+
                "\nAno de Lançamento:\t"+anoFabricacao+"\n";
    }

    public void mostrarCarro(){
        System.out.println(toString());
    }
}