package org.aula09.aula09_car.service;

import org.aula09.aula09_car.module.Concessionaria;
import org.aula09.aula09_car.repository.ConcessionariaRepository;

import java.util.Optional;

public class ConcessionariaService {
    private ConcessionariaRepository concessionariaRepository;
    public ConcessionariaService(ConcessionariaRepository concessionariaRepository){this.concessionariaRepository = concessionariaRepository;}
    public Concessionaria create(Concessionaria concessionaria){return concessionariaRepository.save(concessionaria);}
    public Optional<Concessionaria>update(Long concId, Concessionaria concAtualizada){
        Optional<Concessionaria>concExistente = concessionariaRepository.findById(concId);
        if (concExistente.isPresent()){
            concAtualizada.setId(concId);
            return Optional.of(concessionariaRepository.save(concAtualizada));
        }
        return Optional.empty();
    }
}
