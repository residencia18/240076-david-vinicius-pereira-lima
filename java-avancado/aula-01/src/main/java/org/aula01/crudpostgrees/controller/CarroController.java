package org.aula01.crudpostgrees.controller;

import org.aula01.crudpostgrees.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CarroController {
    @Autowired
    private CarroRepository carroRepository;
}
