package org.aula12.aula12_car.controller;

import com.github.javafaker.Faker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aula12.aula12_car.module.Carro;
import org.aula12.aula12_car.module.Concessionaria;
import org.aula12.aula12_car.service.CarroService;
import org.aula12.aula12_car.service.CarroServiceV2;
import org.aula12.aula12_car.service.ConcessionariaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
@Validated
@RequestMapping(value = "/v2/carros", produces = {"application/json"})
@Tag(name = "api-carro")

@RestController
public class CarroControllerV2 extends CarroController {
    private static Logger log = LoggerFactory.getLogger(CarroController.class);
    @Qualifier("v2")
    private CarroServiceV2 carroServiceV2;

    public CarroControllerV2(CarroService carroService, ConcessionariaService concessionariaService, CarroServiceV2 carroServiceV2) {
        super(carroService, concessionariaService);
        this.carroServiceV2 = carroServiceV2;
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Carro>>findAllSorted(@RequestParam("sort")String[] sort){
        List<Carro> carros = carroServiceV2.findAllSorted(sort);
        return ResponseEntity.ok(carros);
    }


}
