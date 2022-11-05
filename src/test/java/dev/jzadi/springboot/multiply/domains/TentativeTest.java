package dev.jzadi.springboot.multiply.domains;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe TentativeTest, créée le 04/11/2022 à 17:42
 *
 * @author Joachim Zadi
 * @version 1.0 du 04/11/2022
 */
class TentativeTest {

    User user;
    Multiplication multiplication;
    Tentative tentative;

    @BeforeEach
    void setUp() {
        user = new User("Kim");
        multiplication = new Multiplication(15, 11);
        tentative = new Tentative(user, multiplication, 165, true);
    }

    @AfterEach
    void tearDown() {
        user = null;
        multiplication = null;
        tentative = null;
    }


    @Test
    void getId() {
        assertNull(tentative.getId());
    }

    @Test
    void getUser() {
        assertEquals(tentative.getUser(), user);
    }

    @Test
    void getMultiplication() {
        assertEquals(tentative.getMultiplication(), multiplication);
    }

    @Test
    void getReponse() {
        assertEquals(165, tentative.getReponse());
        assertNotEquals(160, tentative.getReponse());
    }

    @Test
    void isCorrect() {
        assertTrue(tentative.isCorrect());
    }
}