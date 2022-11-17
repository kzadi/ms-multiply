package dev.jzadi.springboot.multiply.controllers;

import dev.jzadi.springboot.multiply.domains.Multiplication;
import dev.jzadi.springboot.multiply.services.IMultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Classe MultiplicationController, créée le 05/11/2022 à 12:18
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
@Controller
@RequestMapping("/api/multiply")
public class MultiplicationController {
    private final IMultiplicationService multiplicationService;

    @Autowired
    public MultiplicationController(IMultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/random")
    public ResponseEntity<Multiplication> creerMultiplication() {
        return ResponseEntity.ok().body(multiplicationService.genererMultiplication());
    }
}
