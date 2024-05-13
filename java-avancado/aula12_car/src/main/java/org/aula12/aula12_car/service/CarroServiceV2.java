package org.aula12.aula12_car.service;

import org.aula12.aula12_car.module.Carro;
import org.aula12.aula12_car.repository.CarroRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
@Qualifier("v2")
public class CarroServiceV2 extends CarroService{
    @Autowired
    private CarroRepository carroRepository;

    public List<Carro>findAllSorted(String[]sort){
        List<Order> orders = new ArrayList<>();
        for (String sortedOrder : sort){
            String[] sortedlist = sortedOrder.split(",");
            LoggerFactory.getLogger(CarroServiceV2.class).info("S1 = "+sortedlist[0]+"\nS2="+sortedlist[1]);
            orders.add(new Order(getSortDirection(sortedlist[1]), sortedlist[0]));
        }
        return carroRepository.findAll(Sort.by(orders));
    }

    private Sort.Direction getSortDirection(String direction){
        if ("asc".equals(direction)){
            return Sort.Direction.ASC;
        }
        else if ("desc".equals(direction)){
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    public Page<Carro>findAllPageables(Pageable pageable){
        return carroRepository.findAll(pageable);
    }

    public Map<String, Object> findCarro(int pag, int size){
        List<Carro>carros = new ArrayList<>();
        Pageable paging = PageRequest.of(pag, size);
        Page<Carro>carroPage = carroRepository.findByExists(true, paging);
        carros = carroPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("carros",carros);
        response.put("currentPage", carroPage.getNumber());
        response.put("totalItems", carroPage.getTotalElements());
        response.put("totalPages", carroPage.getTotalPages());

        return response;
    }
}
