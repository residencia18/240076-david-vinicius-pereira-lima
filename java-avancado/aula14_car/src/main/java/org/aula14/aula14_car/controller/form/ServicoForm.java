package org.aula14.aula14_car.controller.form;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula14.aula14_car.module.Servico;

@Data
@NoArgsConstructor
public class ServicoForm {
    private String descricao;
    private Integer duracao;
    private Double preco;

    public Servico criarServico(){return new Servico(descricao, duracao, preco);}
}
