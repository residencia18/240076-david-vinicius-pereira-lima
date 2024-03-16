package org.aula02.crudpostgresonetomany.controller;

import org.aula02.crudpostgresonetomany.controller.dto.ConcessionariaDTO;
import org.aula02.crudpostgresonetomany.controller.form.ConcessionariaForm;
import org.aula02.crudpostgresonetomany.module.Concessionaria;
import org.aula02.crudpostgresonetomany.repository.ConcessionariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/concessionaria/")
public class ConcessionariaController {
    @Autowired
    private ConcessionariaRepository concessionariaRepository;
    @GetMapping
    public List<ConcessionariaDTO> retornaConcessionarias(){
        List<Concessionaria> listaConcessionarias =  concessionariaRepository.findAll();
        List<ConcessionariaDTO> listaConcessionariasDTO = new ArrayList<ConcessionariaDTO>();
        for(Concessionaria c : listaConcessionarias){
            ConcessionariaDTO concessionariaDTO = new ConcessionariaDTO(c);
            listaConcessionariasDTO.add(concessionariaDTO);
        }
        return listaConcessionariasDTO;
    }

    @GetMapping("/{id}")
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

    @PostMapping
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

    @PutMapping
    public ResponseEntity<?>updateConcessionaria(@RequestBody ConcessionariaForm cf, @PathVariable Long id){
        if(id != null){
            try{
                Concessionaria concessionaria = concessionariaRepository.getReferenceById(id);
                if(cf.getNome()!=null)
                    concessionaria.setNome(cf.getNome());
                if(cf.getCarros()!=null)
                    concessionaria.setCarros(cf.getCarros());
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

    @DeleteMapping
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
