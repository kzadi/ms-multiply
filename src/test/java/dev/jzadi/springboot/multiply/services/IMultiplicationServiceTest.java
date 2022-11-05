package dev.jzadi.springboot.multiply.services;

import dev.jzadi.springboot.multiply.domains.Multiplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Classe IMultiplicationServiceTest, créée le 05/11/2022 à 12:44
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
@SpringBootTest
class IMultiplicationServiceTest {

    @MockBean
    private IFacteurGenerateurService facteurGenerateur;

    @Autowired
    private IMultiplicationService multiplicationService;

    @Test
    void genererMultiplication() {
        given(facteurGenerateur.genererFacteur()).willReturn(30, 50);
        Multiplication multiplication = multiplicationService.genererMultiplication();
        assertThat(multiplication.getFacteurA()).isEqualTo(30);
        assertThat(multiplication.getFacteurB()).isEqualTo(50);
    }
}