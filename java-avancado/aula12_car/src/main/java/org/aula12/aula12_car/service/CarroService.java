package org.aula12.aula12_car.service;

import org.aula12.aula12_car.module.Carro;
import org.aula12.aula12_car.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Primary
@Qualifier("v1")
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> findAll(){
        return carroRepository.findAll();
    }
    public Carro create(Carro carro){return carroRepository.save(carro);}
    public Optional<Carro>findById(Long id){
        return carroRepository.findById(id);
    }
    public Optional<Carro>findByPlaca(String placa){
        return carroRepository.findByPlaca(placa);
    }
    public Optional<Carro>update(Long carroId, Carro carroAtualizado){
        return carroRepository.findById(carroId)
                .map(carro -> {
                    carro.setPlaca(carroAtualizado.getPlaca());
                    carro.setMarca(carroAtualizado.getMarca());
                    carro.setModelo(carroAtualizado.getModelo());
                    carro.setAnoFabricacao(carroAtualizado.getAnoFabricacao());
                    carro.setExists(carroAtualizado.isExists());
                    return carroRepository.save(carro);
                });
    }
    public void deleteCarro(Long id){
        carroRepository.deleteById(id);
    }
}
