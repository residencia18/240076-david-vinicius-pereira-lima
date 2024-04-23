package org.aula10.aula10_car.service;

import org.aula10.aula10_car.module.Concessionaria;
import org.aula10.aula10_car.repository.ConcessionariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ConcessionariaService {
    @Autowired
    private ConcessionariaRepository concessionariaRepository;

    public List<Concessionaria>findAll(){return concessionariaRepository.findAll();}

    public Concessionaria create(Concessionaria concessionaria){return concessionariaRepository.save(concessionaria);}

    public Optional<Concessionaria>findById(Long id){return concessionariaRepository.findById(id);}

    public Optional<Concessionaria>update(Long concId, Concessionaria concAtualizada){
        return concessionariaRepository.findById(concId)
                .map(concessionaria -> {
                    concessionaria.setNome(concAtualizada.getNome());
                    return concessionariaRepository.save(concessionaria);
                });
    }

    public void deleteConcessionaria(Long id){concessionariaRepository.deleteById(id);}

}
