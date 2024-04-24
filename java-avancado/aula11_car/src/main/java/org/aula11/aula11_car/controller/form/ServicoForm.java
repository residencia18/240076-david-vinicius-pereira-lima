package org.aula11.aula11_car.controller.form;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula11.aula11_car.module.Servico;

@Data
@NoArgsConstructor
public class ServicoForm {
    private String descricao;
    private Integer duracao;
    private Double preco;

    public Servico criarServico(){return new Servico(descricao, duracao, preco);}
}
