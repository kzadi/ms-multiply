package dev.jzadi.springboot.multiply.services.impl;

import dev.jzadi.springboot.multiply.domains.Multiplication;
import dev.jzadi.springboot.multiply.domains.Tentative;
import dev.jzadi.springboot.multiply.domains.User;
import dev.jzadi.springboot.multiply.repositories.TentativeRepository;
import dev.jzadi.springboot.multiply.repositories.UserRepository;
import dev.jzadi.springboot.multiply.services.IFacteurGenerateurService;
import dev.jzadi.springboot.multiply.services.IMultiplicationService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Classe MultiplicationServiceTest, créée le 05/11/2022 à 16:27
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
@SpringBootTest
class MultiplicationServiceTest {

    @Mock
    private IFacteurGenerateurService facteurGenerateur;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TentativeRepository tentativeRepository;

    private IMultiplicationService multiplicationService;

    @BeforeEach
    void init() {
        openMocks(this);

        multiplicationService = new MultiplicationService(
                facteurGenerateur,
                tentativeRepository,
                userRepository
        );
    }

    @Test
    void genererMultiplication() {
        given(facteurGenerateur.genererFacteur()).willReturn(15, 11);
        Multiplication multiplication = multiplicationService.genererMultiplication();
        assertThat(multiplication.getFacteurA()).isEqualTo(15);
        assertThat(multiplication.getFacteurB()).isEqualTo(11);
    }

    @Test
    void testBonneResponse() {
        given(facteurGenerateur.genererFacteur()).willReturn(30, 50);
        Multiplication multiplication = multiplicationService.genererMultiplication();
        User user = new User("Joachim");
        Tentative tentative = new Tentative(user, multiplication, 1500, true);

        given(userRepository.findByAlias("Joachim")).willReturn(Optional.empty());
        boolean reponse = multiplicationService.reponse(tentative);
        assertThat(reponse).isTrue();
        verify(tentativeRepository).save(tentative);
    }

    @Test
    void testMauvaiseResponse() {
        given(facteurGenerateur.genererFacteur()).willReturn(40, 50);
        Multiplication multiplication = multiplicationService.genererMultiplication();
        User user = new User("Joachim");
        Tentative tentative = new Tentative(user, multiplication, 2010, false);

        given(userRepository.findByAlias("Joachim")).willReturn(Optional.empty());
        boolean reponse = multiplicationService.reponse(tentative);
        assertThat(reponse).isFalse();
        verify(tentativeRepository).save(tentative);
    }

    @Test
    public void testStatsTentatives() {
        Multiplication multiplication = new Multiplication(50, 30);
        User user = new User("Joachim");
        Tentative tentative1 = new Tentative(user, multiplication, 1510, false);
        Tentative tentative2 = new Tentative(user, multiplication, 1501, false);

        List<Tentative> dernieresTentatives = Lists.newArrayList(tentative1, tentative2);

        given(userRepository.findByAlias("Joachim")).willReturn(Optional.empty());
        given(tentativeRepository.findTop5ByUserAliasOrderByIdDesc("Joachim")).willReturn(dernieresTentatives);

        List<Tentative> dernieresTentativesUser = multiplicationService.tentativeByUser("Joachim");

        assertThat(dernieresTentativesUser).isEqualTo(dernieresTentatives);
    }
}