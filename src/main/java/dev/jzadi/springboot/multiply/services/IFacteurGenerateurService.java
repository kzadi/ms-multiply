package dev.jzadi.springboot.multiply.services;

/**
 * Classe IFacteurGenerateurService, créée le 05/11/2022 à 12:21
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
public interface IFacteurGenerateurService {
    /**
     * Permet de generer aleatoirement un nombre entier entre 11 & 99
     *
     * @return Le nombre generé
     */
    int genererFacteur();
}
