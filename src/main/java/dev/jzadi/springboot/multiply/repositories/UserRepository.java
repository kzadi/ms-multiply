package dev.jzadi.springboot.multiply.repositories;

import dev.jzadi.springboot.multiply.domains.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Classe UserRepository, créée le 05/11/2022 à 12:08
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByAlias(String alias);
}
