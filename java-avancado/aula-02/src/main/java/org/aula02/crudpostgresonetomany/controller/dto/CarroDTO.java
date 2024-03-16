package org.aula02.crudpostgresonetomany.controller.dto;

import org.aula02.crudpostgresonetomany.module.Carro;

public class CarroDTO {
    private String placa;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;

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