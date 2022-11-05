package dev.jzadi.springboot.multiply.services.impl;

import dev.jzadi.springboot.multiply.services.IFacteurGenerateurService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Classe FacteurGenerateurService, créée le 05/11/2022 à 12:24
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
@Service
public class FacteurGenerateurService implements IFacteurGenerateurService {

    private final int FACTEUR_MIN = 11;
    private final int FACTEUR_MAX = 99;

    @Override
    public int genererFacteur() {
        return new Random().nextInt(FACTEUR_MAX - FACTEUR_MIN + 1) + FACTEUR_MIN;
    }
}