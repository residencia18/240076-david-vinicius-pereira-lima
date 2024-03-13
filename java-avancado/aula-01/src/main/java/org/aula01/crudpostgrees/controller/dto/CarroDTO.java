package org.aula01.crudpostgrees.controller.dto;

public class CarroDTO {
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;

    public CarroDTO() {
    }

    public CarroDTO(String placa, String marca, String modelo, int anoFabricacao) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
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
