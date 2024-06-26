package org.aula09.aula09_car.service;

import org.aula09.aula09_car.module.Servico;
import org.aula09.aula09_car.repository.ServicoRepository;

import java.util.Optional;

public class ServicoService {
    private ServicoRepository servicoRepository;
    public ServicoService(ServicoRepository servicoRepository){this.servicoRepository = servicoRepository;}
    public Servico create(Servico servico){return servicoRepository.save(servico);}
    public Optional<Servico>update(Long servicoId, Servico servicoAtualizado){
        Optional<Servico>servicoExistente = servicoRepository.findById(servicoId);
        if (servicoExistente.isPresent()){
            servicoAtualizado.setId(servicoId);
            return Optional.of(servicoRepository.save(servicoAtualizado));
        }
        return Optional.empty();
    }
}
