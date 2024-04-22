package org.aula10.aula10_car.service;

import org.aula10.aula10_car.module.Concessionaria;
import org.aula10.aula10_car.repository.ConcessionariaRepository;

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

    public Optional<Concessionaria>findById(Long concId) {
        return concessionariaRepository.findById(concId);
    }
}
