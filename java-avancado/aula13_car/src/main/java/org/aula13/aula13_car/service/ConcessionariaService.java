package org.aula13.aula13_car.service;

import org.aula13.aula13_car.module.Concessionaria;
import org.aula13.aula13_car.repository.ConcessionariaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ConcessionariaService {
    @Autowired
    private ConcessionariaRepository concessionariaRepository;

    public List<Concessionaria>findAll(){return concessionariaRepository.findAll();}
    public Page<Concessionaria> findAll(Pageable pageable){
        return concessionariaRepository.findAll(pageable);
    }
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
