package org.aula07.aula07_car.controller.form;


import org.aula07.aula07_car.module.Servico;

public class ServicoForm {
    private String descricao;
    private Integer duracao;
    private Long preco;
    public ServicoForm(){}
    public ServicoForm(String descricao, Integer duracao, Long preco){
        this.descricao = descricao;
        this.duracao = duracao;
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Long getPreco() {
        return preco;
    }

    public void setPreco(Long preco) {
        this.preco = preco;
    }

    public Servico criarServico(){return new Servico(descricao, duracao, preco);}
}
