package org.aula01.crudpostgrees.controller;

import org.aula01.crudpostgrees.controller.dto.CarroDTO;
import org.aula01.crudpostgrees.controller.form.CarroForm;
import org.aula01.crudpostgrees.module.Carro;
import org.aula01.crudpostgrees.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {
    @Autowired
    private CarroRepository carroRepository;
    @GetMapping
    public List<CarroDTO> retornaCarros(){
        List<Carro>listaCarro = (ArrayList<Carro>)carroRepository.findAll();
        List<CarroDTO>listaCarroDTO = new ArrayList<CarroDTO>();
        for(Carro carro : listaCarro){
            CarroDTO carroDTO = new CarroDTO(carro);
            listaCarroDTO.add(carroDTO);
        }
        return listaCarroDTO;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> retornaCarroPlaca(@PathVariable Long id){
        if(id != null){
            try {
                Carro carro = carroRepository.findCarroByPlaca(placa);
                CarroDTO carroDTO = new CarroDTO(carro);
                return ResponseEntity.ok(carroDTO);
            }
            catch (Exception e){
                return ResponseEntity.notFound().build();
            }
        }
        else
            return ResponseEntity.badRequest().build();
    }
    @GetMapping("/{placa}")
    public ResponseEntity<?> retornaCarroPlaca(@PathVariable String placa){
        if(!placa.isEmpty()){
            try {
                Carro carro = carroRepository.findCarroByPlaca(placa);
                CarroDTO carroDTO = new CarroDTO(carro);
                return ResponseEntity.ok(carroDTO);
            }
            catch (Exception e){
                return ResponseEntity.notFound().build();
            }
        }
        else
            return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?>inserirCarro(@RequestBody CarroForm carroForm, UriComponentsBuilder uriBuilder){
        try {
            Carro carro = carroForm.criarCarro();
            carroRepository.save(carro);
            CarroDTO carroDTO = new CarroDTO(carro);
            uriBuilder.path("/carro/{id}");
            URI uri = uriBuilder.buildAndExpand(carro.getId()).toUri();
            return ResponseEntity.created(uri).body(carroDTO);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }











}
