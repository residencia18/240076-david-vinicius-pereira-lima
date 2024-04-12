package org.aula04.aula04_car.controller.dto;

import jakarta.persistence.Column;
import org.aula04.aula04_car.module.Concessionaria;
import org.aula04.aula04_car.module.Servico;

import java.util.Set;

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
