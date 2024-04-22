package org.aula09.aula09_car.service;

import org.aula09.aula09_car.module.Carro;
import org.aula09.aula09_car.repository.CarroRepository;

import java.util.Optional;

public class CarroService {
    private CarroRepository carroRepository;
    public CarroService(CarroRepository carroRepository){this.carroRepository = carroRepository;}
    public Carro create(Carro carro){return carroRepository.save(carro);}
    public Optional<Carro>update(Long carroId, Carro carroAtualizado){
        Optional<Carro>carroExistente = carroRepository.findById(carroId);
        if(carroExistente.isPresent()){
            carroAtualizado.setId(carroId);
            return Optional.of(carroRepository.save(carroAtualizado));
        }
        return Optional.empty();
    }
}
