package org.aula13.aula13_car.controller;


import com.github.javafaker.Faker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aula13.aula13_car.module.Concessionaria;
import org.aula13.aula13_car.module.Servico;
import org.aula13.aula13_car.service.ConcessionariaService;
import org.aula13.aula13_car.service.ServicoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@Validated
@RequestMapping(value = "api/concessionarias/", produces = {"application/json"})
@Tag(name = "api-concessionária")
@RequiredArgsConstructor

public class ConcessionariaController {
    private static Logger log = LoggerFactory.getLogger(ConcessionariaController.class);
    private final ConcessionariaService concessionariaService;
    private final ServicoService servicoService;
    @Operation(summary = "Retorna lista de concessionárias", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de concessionária retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity <List<Concessionaria>> retornaConcessionarias(){
        log.info("Retornando lista de concessionárias");
        List<Concessionaria>concessionarias = concessionariaService.findAll();
        return ResponseEntity.ok(concessionarias);
    }
    @Operation(summary = "Retorna Concessionária por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concessionária encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Id da Concessionária é null"),
            @ApiResponse(responseCode = "404", description = "Id da Concessionária não foi encontrado")
    })
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Concessionaria>concessionarioByID(@PathVariable Long id){
        log.info("Retornando concessionária por ID");
        return concessionariaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());

    }

    @GetMapping
    public ResponseEntity<Page<Concessionaria>> getAllConcessionarias(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size){
        Page<Concessionaria> concessionariaPage = concessionariaService.findAll(PageRequest.of(page, size));
        if (concessionariaPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Inserir nova concessionária", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concessionária cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Concessionária não pôde ser cadastrada")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>inserirConcessionaria(@RequestBody @Valid Concessionaria concessionaria){
        log.info("Inserindo concessionaria");
        Concessionaria concessionariaSalva = concessionariaService.create(concessionaria);
        return new ResponseEntity<>(concessionariaSalva, HttpStatus.CREATED);
    }
    @Operation(summary = "Inserir um Concessionária com JavaFaker")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concessionária criado e inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao inserir concessionária")
    })
    @PostMapping(value = "/inserirFakeConcessionaria/")
    public ResponseEntity<Concessionaria> gerarConcessionariaFaker(){
        log.info("Criar um novo concessionária usando JavaFaker");
        Faker usFaker = new Faker(new Locale("en-US"));
        Concessionaria concessionaria = new Concessionaria(usFaker.company().name());
        concessionariaService.create(concessionaria);
        log.info("Concessionária salva:\t"+concessionaria+"\n");
        return new ResponseEntity<>(concessionaria, HttpStatus.CREATED);
    }

    @Operation(summary = "Associar Concessionária á Serviço", method = "POST")
    @PostMapping(value = "{concId}/assignServico/{servId}")
    public ResponseEntity<?> associarConcessionariaServico(@PathVariable(name = "concId") Long concId, @PathVariable (name = "servId") Long servId){
        Servico servico = servicoService.findById(servId).orElseThrow(()-> new RuntimeException("Serviço não encontrado"));
        log.info("Detalhes do serviço:\t"+servico.toString()+"\n");

        Concessionaria concessionaria = concessionariaService.findById(concId).orElseThrow(()->new RuntimeException("Concessionária não encontrada"));
        System.out.println("Detalhes da concessionária:\t"+concessionaria.toString()+"\n");

        servico.getConcessionarias().add(concessionaria);
        concessionaria.getServicos().add(servico);

        servicoService.create(servico);
        concessionariaService.create(concessionaria);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualizar dados da concessionária", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concessionária atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Id da concessionária é null"),
            @ApiResponse(responseCode = "404", description = "Id da concessionária não foi encontrado")
    })
    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Concessionaria>updateConcessionaria(@RequestBody @Valid Concessionaria concessionaria, @PathVariable Long id){
        log.info("Atualizando concessionária por ID");
        return concessionariaService.update(id,concessionaria)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(summary = "Deletar uma concessionária do BD", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concessionária deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Id da concessionária tem valor null"),
            @ApiResponse(responseCode = "404", description = "Id da concessionária não foi encontrado")
    })
    @DeleteMapping(value = ("/{id}"), consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Concessionaria>deleteConcessionaria(@PathVariable Long id){
        log.info("Deletando concessionária por ID");
        concessionariaService.deleteConcessionaria(id);
        return ResponseEntity.noContent().build();
    }
}
