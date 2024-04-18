package org.aula06.aula06_car.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aula06.aula06_car.controller.dto.ConcessionariaDTO;
import org.aula06.aula06_car.controller.form.ConcessionariaForm;
import org.aula06.aula06_car.module.Concessionaria;
import org.aula06.aula06_car.module.Servico;
import org.aula06.aula06_car.repository.ConcessionariaRepository;
import org.aula06.aula06_car.repository.ServicoRepository;
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
@RequestMapping(value = "api/concessionaria/", produces = {"application/json"})
@Tag(name = "api-concessionária")
public class ConcessionariaController {
    private static Logger log = LoggerFactory.getLogger(ConcessionariaController.class);
    @Autowired
    private ConcessionariaRepository concessionariaRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Operation(summary = "Retorna lista de concessionárias", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de concessionária retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping(consumes = MediaType.ALL_VALUE)
    public List<ConcessionariaDTO> retornaConcessionarias(){
        log.info("Retornando lista de concessionárias");
        List<Concessionaria> listaConcessionarias =  concessionariaRepository.findAll();
        List<ConcessionariaDTO> listaConcessionariasDTO = new ArrayList<ConcessionariaDTO>();
        for(Concessionaria c : listaConcessionarias){
            ConcessionariaDTO concessionariaDTO = new ConcessionariaDTO(c);
            listaConcessionariasDTO.add(concessionariaDTO);
        }
        return listaConcessionariasDTO;
    }
    @Operation(summary = "Retorna Concessionária por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concessionária encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Id da Concessionária é null"),
            @ApiResponse(responseCode = "404", description = "Id da Concessionária não foi encontrado")
    })
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?>concessionarioByID(@PathVariable Long id){
        log.info("Retornando concessionária por ID");
        if (id != null){
            try {
                Concessionaria concessionaria = concessionariaRepository.getReferenceById(id);
                ConcessionariaDTO concessionariaDTO = new ConcessionariaDTO(concessionaria);
                return ResponseEntity.ok(concessionariaDTO);
            }
            catch (Exception e){
                return ResponseEntity.notFound().build();
            }
        }
        else
            return ResponseEntity.badRequest().build();

    }
    @Operation(summary = "Inserir nova concessionária", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concessionária cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Concessionária não pôde ser cadastrada")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>inserirConcessionaria(@RequestBody ConcessionariaForm cf, UriComponentsBuilder uriBuilder){
        log.info("Inserindo concessionaria");
        try {
            Concessionaria concessionaria = cf.criarConcessionaria();
            concessionariaRepository.save(concessionaria);
            ConcessionariaDTO concessionariaDTO = new ConcessionariaDTO(concessionaria);
            uriBuilder.path("/concessionaria/{id}");
            URI uri = uriBuilder.buildAndExpand(concessionaria.getId()).toUri();
            ResponseEntity.created(uri).body(concessionariaDTO);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return null;
    }
    @Operation(summary = "Inserir nova lista de concessionárias", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concessionárias cadastradas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Concessionárias não foram cadastradas")
    })
    @PostMapping(value ="/inserirListas/" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>inserirConcessionarias(@RequestBody List<ConcessionariaForm> listaCf, UriComponentsBuilder uriBuilder){
        log.info("Inserindo lista de concessionarárias");
        try {
            List<ConcessionariaDTO>listaConcessionariaDTO = new ArrayList<>();
            for(ConcessionariaForm concessionariaForm : listaCf) {
                Concessionaria concessionaria = concessionariaForm.criarConcessionaria();
                concessionariaRepository.save(concessionaria);
                listaConcessionariaDTO.add(new ConcessionariaDTO(concessionaria));
            }
            return ResponseEntity.ok().body(listaConcessionariaDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary = "Associar Concessionária á Serviço", method = "POST")
    @PostMapping(value = "/associarConcessionariaServico/{concId}/{servId}")
    public String associarConcessionariaServico(@PathVariable(name = "concId") Long concId, @PathVariable (name = "servId") Long servId){
        log.info("Associando uma Concessionária existente a um Serviço existente");
        Servico servico = this.servicoRepository.getReferenceById(servId);
        log.info("Detalhes do serviço:\t"+servico.toString()+"\n");

        Concessionaria concessionaria = this.concessionariaRepository.getReferenceById(concId);
        System.out.println("Detalhes da concessionária:\t"+concessionaria.toString()+"\n");

        Set<Concessionaria>concessionarias = new HashSet<>();
        concessionarias.add(concessionaria);
        servico.setConcessionarias(concessionarias);
        servico = servicoRepository.save(servico);
        log.info("Serviço adicionado á concessionária");
        return "Concessionária salva";
    }


    @Operation(summary = "Atualizar dados da concessionária", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concessionária atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Id da concessionária é null"),
            @ApiResponse(responseCode = "404", description = "Id da concessionária não foi encontrado")
    })
    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>updateConcessionaria(@RequestBody ConcessionariaForm cf, @PathVariable Long id){
        log.info("Atualizando concessionária por ID");
        if(id != null){
            try{
                Concessionaria concessionaria = concessionariaRepository.getReferenceById(id);
                if(cf.getNome()!=null)
                    concessionaria.setNome(cf.getNome());
                concessionariaRepository.save(concessionaria);
                ConcessionariaDTO concessionariaDTO = new ConcessionariaDTO(concessionaria);
                return ResponseEntity.ok(concessionariaDTO);
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
    @Operation(summary = "Deletar uma concessionária do BD", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concessionária deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Id da concessionária tem valor null"),
            @ApiResponse(responseCode = "404", description = "Id da concessionária não foi encontrado")
    })
    @DeleteMapping(value = ("/{id}"), consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?>deleteConcessionaria(@PathVariable Long id){
        log.info("Deletando concessionária por ID");
        if(id!=null){
            try {
                Concessionaria concessionaria = concessionariaRepository.getReferenceById(id);
                ConcessionariaDTO concessionariaDTO = new ConcessionariaDTO(concessionaria);
                concessionariaRepository.delete(concessionaria);
                System.out.println(concessionariaDTO + " DELETADO com sucesso!!!");
                return ResponseEntity.ok(concessionariaDTO);
            }
            catch (Exception e){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
