package org.aula01.crudpostgrees.controller.form;

import org.aula01.crudpostgrees.module.Carro;

public class CarroForm {
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;

    public CarroForm() {
    }

    public CarroForm(String placa, String marca, String modelo, int anoFabricacao) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Carro criarCarro(){
        return new Carro(null, placa, marca, modelo, anoFabricacao);
    }
}
