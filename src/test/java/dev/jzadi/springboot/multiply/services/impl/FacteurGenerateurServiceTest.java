package dev.jzadi.springboot.multiply.services.impl;

import dev.jzadi.springboot.multiply.services.IFacteurGenerateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe FacteurGenerateurServiceTest, créée le 05/11/2022 à 16:23
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
@SpringBootTest
class FacteurGenerateurServiceTest {

    private IFacteurGenerateurService generateurService;

    @BeforeEach
    void init() {
        generateurService = new FacteurGenerateurService();
    }

    @Test
    void genererFacteur() {
        List<Integer> facteursGeneres = IntStream.range(0, 1000)
                .map(i -> generateurService.genererFacteur())
                .boxed()
                .collect(toList());

        assertThat(facteursGeneres)
                .containsAnyElementsOf(
                        IntStream.range(11, 99).boxed().collect(toList())
                );
    }
}