package dev.jzadi.springboot.multiply.controllers;

import dev.jzadi.springboot.multiply.domains.Tentative;
import dev.jzadi.springboot.multiply.services.IMultiplicationService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe TentativeController, créée le 05/11/2022 à 18:14
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
@RestController
@RequestMapping("/tentatives")
public class TentativeController {

    private final IMultiplicationService multiplicationService;

    @Autowired
    public TentativeController(IMultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resultat> postTentative(@RequestBody Tentative tentative) {
        return ResponseEntity.ok(new Resultat(multiplicationService.reponse(tentative)));
    }

    @GetMapping(value = "/{alias}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tentative>> getTentativesByUser(@PathVariable("alias") String alias) {
        return ResponseEntity.ok(multiplicationService.tentativeByUser(alias));
    }

    @Getter
    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    static final class Resultat {
        private final boolean correct;
    }
}