package org.aula07.aula07_car.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aula07.aula07_car.controller.dto.CarroDTO;
import org.aula07.aula07_car.controller.form.CarroForm;
import org.aula07.aula07_car.module.Carro;
import org.aula07.aula07_car.module.Concessionaria;
import org.aula07.aula07_car.repository.CarroRepository;
import org.aula07.aula07_car.repository.ConcessionariaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/carro/", produces = {"application/json"})
@Tag(name = "api-carro")
public class CarroController {
    private static Logger log = LoggerFactory.getLogger(CarroController.class);
    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private ConcessionariaRepository concessionariaRepository;
    @Operation(summary = "Retorna uma lista de todos os carros do banco de dados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping(consumes = MediaType.ALL_VALUE)
    public List<CarroDTO> retornaCarros(){
        log.info("Retornando lista de carros");
        List<Carro>listaCarro = (ArrayList<Carro>)carroRepository.findAll();
        List<CarroDTO>listaCarroDTO = new ArrayList<CarroDTO>();
        for(Carro carro : listaCarro){
            CarroDTO carroDTO = new CarroDTO(carro);
            listaCarroDTO.add(carroDTO);
        }
        return listaCarroDTO;
    }
    @Operation(summary = "Retorna um carro através do Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Id do carro é nulo"),
            @ApiResponse(responseCode = "404", description = "Id do carro não foi encontrado")
    })
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> retornaCarroID(@PathVariable Long id){
        log.info("Retornando carro por Id");
        if(id != null){
            try {
                Carro carro = carroRepository.getReferenceById(id);
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
    @Operation(summary = "Retorna um carro através da sua placa", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Placa do carro está vazia"),
            @ApiResponse(responseCode = "404", description = "Placa do carro não encontrada")
    })
    @GetMapping(value = "/placa/{placa}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> retornaCarroPlaca(@PathVariable String placa){
        log.info("Retornando carro pela placa");
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
    @Operation(summary = "Inserir novo carro no BD", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro inserido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Inserção do carro não teve sucesso")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>inserirCarro(@RequestBody CarroForm carroForm, UriComponentsBuilder uriBuilder){
        log.info("Inserindo carro");
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
    @Operation(summary = "Inserir lista de carros no BD", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carros inseridos com sucesso"),
            @ApiResponse(responseCode = "404", description = "Inserção dos carros não teve sucesso")
    })
    @PostMapping(value = "/inserirLista/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> inserirCarros(@RequestBody List<CarroForm> listaCarroForm, UriComponentsBuilder uriBuilder) {
        log.info("Inserindo lista de carros");
        try {
            List<CarroDTO> carrosDTO = new ArrayList<>();
            for (CarroForm carroForm : listaCarroForm) {
                Carro carro = carroForm.criarCarro();
                carroRepository.save(carro);
                carrosDTO.add(new CarroDTO(carro));
            }
            return ResponseEntity.ok().body(carrosDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Associar Carro á Concessionária", method = "POST")
    @PostMapping(value = "/associarCarroConcessionaria/{concId}/{carId}")
    public String associarConcessionariaServico(@PathVariable(name = "concId") Long concId, @PathVariable (name = "carId") Long carId){
        log.info("Associando um Carro existente a uma Concessionária existente");
        Carro carro = this.carroRepository.getReferenceById(carId);
        log.info("Detalhes do carro:\t"+carro.toString()+"\n");

        Concessionaria concessionaria = this.concessionariaRepository.getReferenceById(concId);
        log.info("Detalhes da concessionária:\t"+concessionaria.toString()+"\n");

        Set<Carro> carros = new HashSet<>();
        carros.add(carro);
        concessionaria.setCarros(carros);
        concessionaria = concessionariaRepository.save(concessionaria);
        log.info("Carro adicionado á concessionária");
        return "Carro salvo";
    }
    @Operation(summary = "Atualizar dados do carro", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Id do carro tem valor null"),
            @ApiResponse(responseCode = "404", description = "Id do carro não foi encontrado")
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCarro(@RequestBody CarroForm carroForm, @PathVariable Long id){
        log.info("Atualizando carro pelo ID");
        if(id != null){
            try {
                Carro carro = carroRepository.getReferenceById(id);
                if(carroForm.getPlaca()!=null)
                    carro.setPlaca(carroForm.getPlaca());
                if(carroForm.getMarca()!=null)
                    carro.setMarca(carroForm.getMarca());
                if(carroForm.getModelo()!=null)
                    carro.setModelo(carroForm.getModelo());
                if(carroForm.getAnoFabricacao()!=null)
                    carro.setAnoFabricacao(carroForm.getAnoFabricacao());
                carroRepository.save(carro);
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
    @Operation(summary = "Deletar carro do BD", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Id do carro tem valor null"),
            @ApiResponse(responseCode = "404", description = "Id do carro não foi encontrado")
    })
    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> deleteCarro(@PathVariable Long id){
        log.info("Deletando carro pelo ID");
        if(id != null){
            try {
                Carro carro = carroRepository.getReferenceById(id);
                CarroDTO carroDTO = new CarroDTO(carro);
                carroRepository.delete(carro);
                System.out.println(carroDTO + " DELETADO com sucesso!!!");
                return ResponseEntity.ok(carroDTO);
            }
            catch (Exception e){
                ResponseEntity.notFound().build();
            }
        }
        else {
            return ResponseEntity.badRequest().build();
        }
        return null;
    }

}
