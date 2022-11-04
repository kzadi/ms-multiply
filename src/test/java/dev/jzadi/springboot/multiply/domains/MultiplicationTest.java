package dev.jzadi.springboot.multiply.domains;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe MultiplicationTest, créée le 04/11/2022 à 17:19
 *
 * @author Joachim Zadi
 * @version 1.0 du 04/11/2022
 */
class MultiplicationTest {

    Multiplication multiplication;

    @BeforeEach
    void setUp() {
        multiplication = new Multiplication(15, 11);
    }

    @AfterEach
    void tearDown() {
        multiplication = null;
    }

    @Test
    void testEquals() {
        Multiplication autre = new Multiplication(15, 11);
        assertEquals(multiplication, autre);
    }

    @Test
    void getId() {
        assertNull(multiplication.getId());
    }

    @Test
    void getFacteurA() {
        assertEquals(multiplication.getFacteurA(), 15);
    }

    @Test
    void getFacteurB() {
        assertEquals(multiplication.getFacteurB(), 11);
    }
}