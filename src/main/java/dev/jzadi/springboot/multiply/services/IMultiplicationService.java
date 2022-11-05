package dev.jzadi.springboot.multiply.services;

import dev.jzadi.springboot.multiply.domains.Multiplication;
import dev.jzadi.springboot.multiply.domains.Tentative;

import java.util.List;

/**
 * Classe IMultiplicationService, créée le 05/11/2022 à 12:40
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
public interface IMultiplicationService {
    Multiplication genererMultiplication();

    boolean reponse(final Tentative tentative);

    List<Tentative> tentativeByUser(String alias);
}
