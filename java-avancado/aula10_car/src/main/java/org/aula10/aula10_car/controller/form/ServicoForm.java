package org.aula10.aula10_car.controller.form;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula10.aula10_car.module.Servico;

@Data
@NoArgsConstructor
public class ServicoForm {
    private String descricao;
    private Integer duracao;
    private Double preco;

    public Servico criarServico(){return new Servico(descricao, duracao, preco);}
}
