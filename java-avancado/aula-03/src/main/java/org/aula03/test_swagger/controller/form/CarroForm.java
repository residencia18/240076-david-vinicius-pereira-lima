package org.aula03.test_swagger.controller.form;


import org.aula03.test_swagger.module.Carro;

public class CarroForm {
    private String placa;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;

    public CarroForm() {
    }

    public CarroForm(String placa, String marca, String modelo, Integer anoFabricacao) {
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

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Carro criarCarro() throws Exception {
        return new Carro(null, placa, marca, modelo, anoFabricacao);
    }
}
