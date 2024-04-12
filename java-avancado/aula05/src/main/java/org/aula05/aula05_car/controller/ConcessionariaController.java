package org.aula04.aula04_car.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aula04.aula04_car.controller.dto.ConcessionariaDTO;
import org.aula04.aula04_car.controller.form.ConcessionariaForm;
import org.aula04.aula04_car.module.Concessionaria;
import org.aula04.aula04_car.repository.ConcessionariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/concessionaria/", produces = {"application/json"})
@Tag(name = "api-concessionária")
public class ConcessionariaController {
    @Autowired
    private ConcessionariaRepository concessionariaRepository;
    @Operation(summary = "Retorna lista de concessionárias", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de concessionária retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping(consumes = MediaType.ALL_VALUE)
    public List<ConcessionariaDTO> retornaConcessionarias(){
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
    @Operation(summary = "Atualizar dados da concessionária", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concessionária atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Id da concessionária é null"),
            @ApiResponse(responseCode = "404", description = "Id da concessionária não foi encontrado")
    })
    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>updateConcessionaria(@RequestBody ConcessionariaForm cf, @PathVariable Long id){
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
