package org.aula06.aula06_car.controller.dto;


import org.aula06.aula06_car.module.Servico;

public class ServicoDTO {
    private String descricao;
    private Integer duracao;
    private Long preco;

    public ServicoDTO() {}

    public ServicoDTO(Servico servico){
        this.descricao = servico.getDescricao();
        this.duracao = servico.getDuracao();
        this.preco = servico.getPreco();
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public Long getPreco() {
        return preco;
    }

}
