package org.aula13.aula13_car.repository;
import org.aula13.aula13_car.module.Servico;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long > {
    Page<Servico>findAll(Pageable pageable);
    Page<Servico>findByDescricao(String descricao, Pageable pageable);
    Page<Servico>findByDuracao(Integer duracao, Pageable pageable);
    Page<Servico>findByPreco(Double preco, Pageable pageable);
    <S extends Servico> List<S> findAll(Example<S> example);

}
