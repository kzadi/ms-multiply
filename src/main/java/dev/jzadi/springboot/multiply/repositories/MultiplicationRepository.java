package dev.jzadi.springboot.multiply.repositories;

import dev.jzadi.springboot.multiply.domains.Multiplication;
import org.springframework.data.repository.CrudRepository;

/**
 * Classe MultiplicationRepository, créée le 05/11/2022 à 12:12
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
public interface MultiplicationRepository extends CrudRepository<Multiplication, Integer> {
}
