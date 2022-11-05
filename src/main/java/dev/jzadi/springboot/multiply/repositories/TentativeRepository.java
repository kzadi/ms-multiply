package dev.jzadi.springboot.multiply.repositories;

import dev.jzadi.springboot.multiply.domains.Tentative;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Classe TentativeRepository, créée le 05/11/2022 à 12:13
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
public interface TentativeRepository extends CrudRepository<Tentative, Integer> {
    List<Tentative> findTop5ByUserAliasOrderByIdDesc(String alias);
}
