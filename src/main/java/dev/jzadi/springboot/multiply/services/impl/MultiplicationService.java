package dev.jzadi.springboot.multiply.services.impl;

import dev.jzadi.springboot.multiply.domains.Multiplication;
import dev.jzadi.springboot.multiply.domains.Tentative;
import dev.jzadi.springboot.multiply.domains.User;
import dev.jzadi.springboot.multiply.repositories.TentativeRepository;
import dev.jzadi.springboot.multiply.repositories.UserRepository;
import dev.jzadi.springboot.multiply.services.IFacteurGenerateurService;
import dev.jzadi.springboot.multiply.services.IMultiplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe MultiplicationService, créée le 05/11/2022 à 12:44
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
@Service
public class MultiplicationService implements IMultiplicationService {

    private IFacteurGenerateurService facteurGenerateur;
    private TentativeRepository tentativeRepository;
    private UserRepository userRepository;

    public MultiplicationService(final IFacteurGenerateurService facteurGenerateur, final TentativeRepository tentativeRepository, final UserRepository userRepository) {
        this.facteurGenerateur = facteurGenerateur;
        this.tentativeRepository = tentativeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Multiplication genererMultiplication() {
        int facteurA = facteurGenerateur.genererFacteur();
        int facteurB = facteurGenerateur.genererFacteur();
        return new Multiplication(facteurA, facteurB);
    }

    @Override
    public boolean reponse(Tentative tentative) {
        Optional<User> user = userRepository.findByAlias(tentative.getUser().getAlias());
        boolean isCorrect = tentative.getReponse() == tentative.getMultiplication().getFacteurA() * tentative.getMultiplication().getFacteurB();

        Tentative tentativeReponse = new Tentative(
                user.orElse(tentative.getUser()),
                tentative.getMultiplication(),
                tentative.getReponse(),
                isCorrect
        );

        tentativeRepository.save(tentativeReponse);
        return isCorrect;
    }

    @Override
    public List<Tentative> tentativeByUser(String alias) {
        return tentativeRepository.findTop5ByUserAliasOrderByIdDesc(alias);
    }
}
