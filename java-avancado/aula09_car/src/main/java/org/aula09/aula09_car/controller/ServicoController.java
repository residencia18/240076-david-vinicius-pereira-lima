package org.aula09.aula09_car.controller;

import com.github.javafaker.Faker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aula09.aula09_car.controller.dto.ServicoDTO;
import org.aula09.aula09_car.controller.form.ServicoForm;
import org.aula09.aula09_car.module.Concessionaria;
import org.aula09.aula09_car.module.Servico;
import org.aula09.aula09_car.repository.ConcessionariaRepository;
import org.aula09.aula09_car.repository.ServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@Validated
@RequestMapping(value = "/api/servico/", produces = {"application/json"})
@Tag(name = "api-servico")
public class ServicoController {
    private static Logger log = LoggerFactory.getLogger(ServicoController.class);
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ConcessionariaRepository concessionariaRepository;

    @Operation(summary = "Retorna lista de todas os Serviços", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de serviços carregada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request")

    })
    @GetMapping(consumes = MediaType.ALL_VALUE)
    public List<ServicoDTO> retornaServicos(){
        log.info("Retornando todos os serviços");
        List<Servico> listaServicos = servicoRepository.findAll();
        List<ServicoDTO> listaServicosDTO = new ArrayList<ServicoDTO>();
        for (Servico servico : listaServicos ){
            ServicoDTO servicoDTO = new ServicoDTO(servico);
            listaServicosDTO.add(servicoDTO);
        }
        return listaServicosDTO;
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
        if (id != null){
            try{
                Servico servico = servicoRepository.getReferenceById(id);
                ServicoDTO servicoDTO = new ServicoDTO(servico);
                return ResponseEntity.ok(servicoDTO);
            }catch (Exception e){
                return ResponseEntity.notFound().build();
            }
        }
        else
            return ResponseEntity.badRequest().build();
    }
    @Operation(summary = "Inserir um novo serviço", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inserção concluída com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha na inserção")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> inserirServico(@RequestBody ServicoForm servicoForm, UriComponentsBuilder uriBuilder){
        log.info("Inserindo serviço");
        try {
            Servico servico = servicoForm.criarServico();
            servicoRepository.save(servico);
            ServicoDTO servicoDTO = new ServicoDTO(servico);
            uriBuilder.path("/servico/{id}");
            URI uri =  uriBuilder.buildAndExpand(servico.getId()).toUri();
            ResponseEntity.created(uri).body(servicoDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return null;
    }

    @Operation(summary = "Inserir um Serviço com JavaFaker", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço criado e inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao inserir serviço")
    })
    @PostMapping(value = "/inserirFakeServico/")
    public String gerarServicoFaker(){
        log.info("Criar um novo serviço usando JavaFaker");
        Faker usFaker = new Faker(new Locale("en-US"));
        Date dataInicial = usFaker.date().future(365, TimeUnit.DAYS);
        Date dataFinal = usFaker.date().future(30, TimeUnit.DAYS, dataInicial);
        long duracaoMili = dataFinal.getTime() - dataInicial.getTime();
        long duracaoL = TimeUnit.MILLISECONDS.toSeconds(duracaoMili);
        Integer duracao = (int) duracaoL;
        Double preco =  Double.parseDouble(usFaker.commerce().price().replace(",", "."));
        Servico servico = new Servico(usFaker.company().buzzword(), (int) duracao, preco);
        servicoRepository.save(servico);
        log.info("Serviço salvo:\t"+servico+"\n");
        return "Serviço salvo";
    }

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
    }
    @Operation(summary = "Associar serviço com concessionária", method = "POST")
    @PostMapping(value = "/associarServicoConcessionaria/{concId}/{servId}")
    public String associarServicoConcessionaria(@PathVariable(name = "concId") Long concId, @PathVariable (name = "servId") Long servId){
        log.info("Associando um Serviço existente a uma Concessinária existente");
        Servico servico = this.servicoRepository.getReferenceById(servId);
        log.info("Detalhes do serviço:\t"+servico.toString()+"\n");

        Concessionaria concessionaria = this.concessionariaRepository.getReferenceById(concId);
        log.info("Detalhes da concessionária:\t"+concessionaria.toString()+"\n");

        Set<Servico>servicos = new HashSet<>();
        servicos.add(servico);
        concessionaria.setServicos(servicos);
        concessionaria = concessionariaRepository.save(concessionaria);
        log.info("Serviço adicionado á concessionária");
        return "Serviço salvo";
    }

    @Operation(summary = "Atualizar serviço", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do serviço feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha na atualizção do serviço"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateServico(@PathVariable Long id, @RequestBody ServicoForm servicoForm){
        log.info("Atualizando serviço por ID");
        if(id != null){
            try {
                Servico servico = servicoRepository.getReferenceById(id);
                if (servico.getDescricao()!=null)
                    servico.setDescricao(servicoForm.getDescricao());
                if (servico.getDuracao()!=null)
                    servico.setDuracao(servicoForm.getDuracao());
                if (servico.getPreco()!=null)
                    servico.setPreco(servicoForm.getPreco());
                servicoRepository.save(servico);
                ServicoDTO servicoDTO = new ServicoDTO(servico);
                return ResponseEntity.ok(servicoDTO);
            }catch (Exception e){
                return ResponseEntity.notFound().build();
            }
        }
        else
            return ResponseEntity.badRequest().build();
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
        if(id!=null){
            try {
                Servico servico = servicoRepository.getReferenceById(id);
                ServicoDTO servicoDTO = new ServicoDTO(servico);
                servicoRepository.delete(servico);
                System.out.println(servicoDTO + " DELETADO com sucesso!!!");
                return ResponseEntity.ok(servicoDTO);
            }catch (Exception e){
                return ResponseEntity.notFound().build();
            }
        }
        else
            return ResponseEntity.badRequest().build();
    }

}
