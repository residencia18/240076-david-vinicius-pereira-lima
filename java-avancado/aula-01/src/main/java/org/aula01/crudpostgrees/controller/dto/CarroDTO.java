package org.aula01.crudpostgrees.controller.dto;

import org.aula01.crudpostgrees.module.Carro;

public class CarroDTO {
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;

    public CarroDTO() {
    }

    public CarroDTO(Carro carro) {
        this.placa = carro.getPlaca();
        this.marca = carro.getMarca();
        this.modelo = carro.getModelo();
        this.anoFabricacao = carro.getAnoFabricacao();
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

    public int getAnoFabricacao() {
        return anoFabricacao;
    }
}
