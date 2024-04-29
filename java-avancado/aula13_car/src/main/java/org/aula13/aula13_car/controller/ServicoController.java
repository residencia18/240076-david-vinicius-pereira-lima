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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@RestController
@Validated
@RequestMapping(value = "/api/servico/", produces = {"application/json"})
@Tag(name = "api-servico")
@RequiredArgsConstructor
public class ServicoController {
    private static Logger log = LoggerFactory.getLogger(ServicoController.class);

    private final ServicoService servicoService;

    private final ConcessionariaService concessionariaService;

    @Operation(summary = "Retorna lista de todas os Serviços", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de serviços carregada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request")

    })
    @GetMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<Servico>> retornaServicos(){
        log.info("Retornando todos os serviços");
        List<Servico>servicos = servicoService.findAll();
        return ResponseEntity.ok(servicos);
    }
    @Operation(summary = "Retorna serviço por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço foi encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request do Serviço"),
            @ApiResponse(responseCode = "404", description = "Serviço não foi encontrado")
    })
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> retornaServicoById(@PathVariable Long id){
        log.info("Retornando serviço por ID");
        return servicoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Servico>>getAllServicos(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size){
        Page<Servico>servicoPage = servicoService.findAll(PageRequest.of(page,size));
        if (servicoPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Inserir um novo serviço", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inserção concluída com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha na inserção")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Servico> inserirServico(@RequestBody @Valid Servico servico){
        log.info("Inserindo serviço");
        Servico servicoSalvo = servicoService.create(servico);
        return new ResponseEntity<>(servicoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Inserir um Serviço com JavaFaker", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço criado e inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao inserir serviço")
    })
    @PostMapping(value = "/inserirFakeServico/")
    public ResponseEntity<Servico> gerarServicoFaker(){
        log.info("Criar um novo serviço usando JavaFaker");
        Faker usFaker = new Faker(new Locale("en-US"));
        Date dataInicial = usFaker.date().future(365, TimeUnit.DAYS);
        Date dataFinal = usFaker.date().future(30, TimeUnit.DAYS, dataInicial);
        long duracaoMili = dataFinal.getTime() - dataInicial.getTime();
        long duracaoL = TimeUnit.MILLISECONDS.toSeconds(duracaoMili);
        Integer duracao = (int) duracaoL;
        Double preco =  Double.parseDouble(usFaker.commerce().price().replace(",", "."));
        Servico servico = new Servico(usFaker.company().buzzword(), (int) duracao, preco);
        servicoService.create(servico);
        log.info("Serviço salvo:\t"+servico+"\n");
        return new ResponseEntity<>(servico, HttpStatus.CREATED);
    }
/*
    @Operation(summary = "Criar serviço para a concessionaria", method = "POST")
    @PostMapping(value="/criarServicoConcessionaria/{concId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String criarServicoEmConcessionaria(@RequestBody Servico entity, @PathVariable(name = "concId") Integer concId){
        log.info("Associando um Serviço existente a uma Concessionária existente");
        Servico servico =  new Servico(entity.getDescricao(), entity.getDuracao(), entity.getPreco());
        servicoRepository.save(servico);
        log.info("Serviço salvo:\t"+servico+"\n");
        Concessionaria concessionaria = this.concessionariaRepository.getReferenceById(Long.valueOf(concId));
        log.info("Detalhes concessionaria:\t"+concessionaria.toString()+"\n");
        Set<Servico> servicos = new HashSet<>();
        servicos.add(servico);
        concessionaria.setServicos(servicos);
        concessionaria = concessionariaRepository.save(concessionaria);
        log.info("Serviço adicionado á concessionária");
        return "Serviço salvo";
    }*/
    @Operation(summary = "Associar serviço com concessionária", method = "POST")
    @PostMapping(value = "{servId}/assignConcessionaria/{concId}")
    public ResponseEntity<?> associarServicoConcessionaria(@PathVariable(name = "concId") Long concId, @PathVariable (name = "servId") Long servId){
        Servico servico = servicoService.findById(servId).orElseThrow(()->new RuntimeException("Serviço não encontrado"));
        log.info("Detalhes do serviço:\t"+servico.toString()+"\n");

        Concessionaria concessionaria =concessionariaService.findById(concId).orElseThrow(()->new RuntimeException("Concessionária não encontrada"));
        log.info("Detalhes da concessionária:\t"+concessionaria.toString()+"\n");

        servico.getConcessionarias().add(concessionaria);
        concessionaria.getServicos().add(servico);

        servicoService.create(servico);
        concessionariaService.create(concessionaria);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualizar serviço", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do serviço feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha na atualizção do serviço"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Servico> updateServico(@PathVariable Long id, @RequestBody @Valid Servico servico){
        log.info("Atualizando serviço por ID");
        return servicoService.update(id,servico)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(summary = "Deletar um serviço", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha ao deletar Serviço"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> deletarServico(@PathVariable Long id){
        log.info("Deletando serviço por ID");
        servicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
