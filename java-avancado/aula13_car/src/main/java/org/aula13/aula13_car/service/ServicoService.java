package org.aula13.aula13_car.service;

import org.aula13.aula13_car.module.Servico;
import org.aula13.aula13_car.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    public Servico create(Servico servico){return servicoRepository.save(servico);}

    public List<Servico>findAll(){return servicoRepository.findAll();}
    public Page<Servico>findAll(Pageable pageable){return servicoRepository.findAll(pageable);}

    public Optional<Servico>findById(Long id){return servicoRepository.findById(id);}

    public Optional<Servico>update(Long servicoId, Servico servicoAtualizado){
        return servicoRepository.findById(servicoId)
                .map(servico -> {
                    servico.setDescricao(servicoAtualizado.getDescricao());
                    servico.setDuracao(servicoAtualizado.getDuracao());
                    servico.setPreco(servicoAtualizado.getPreco());
                    return servicoRepository.save(servico);
                });
    }

    public void delete(Long id){servicoRepository.deleteById(id);}
}
