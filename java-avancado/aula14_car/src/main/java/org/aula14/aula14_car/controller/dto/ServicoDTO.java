package org.aula14.aula14_car.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aula14.aula14_car.module.Servico;

@Data
@NoArgsConstructor
public class ServicoDTO {
    private String descricao;
    private Integer duracao;
    private Double preco;

    public ServicoDTO(Servico servico){
        this.descricao = servico.getDescricao();
        this.duracao = servico.getDuracao();
        this.preco = servico.getPreco();
    }

}
