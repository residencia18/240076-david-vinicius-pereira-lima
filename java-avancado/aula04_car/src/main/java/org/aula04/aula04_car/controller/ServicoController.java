package org.aula04.aula04_car.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aula04.aula04_car.controller.dto.ServicoDTO;
import org.aula04.aula04_car.controller.form.ServicoForm;
import org.aula04.aula04_car.module.Servico;
import org.aula04.aula04_car.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/servico/", produces = {"application/json"})
@Tag(name = "api-servico")
public class ServicoController {
    @Autowired
    private ServicoRepository servicoRepository;

    @Operation(summary = "Retorna lista de todas os Serviços", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de serviços carregada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request")

    })
    @GetMapping(consumes = MediaType.ALL_VALUE)
    public List<ServicoDTO> retornaServicos(){
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
    @Operation(summary = "Atualizar serviço", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do serviço feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha na atualizção do serviço"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateServico(@PathVariable Long id, @RequestBody ServicoForm servicoForm){
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
