package dev.jzadi.springboot.multiply.domains;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe UserTest, créée le 04/11/2022 à 17:03
 *
 * @author Joachim Zadi
 * @version 1.0 du 04/11/2022
 */
class UserTest {

    User user;

    @BeforeEach
    void init() {
        user = new User("Kim");
    }

    @AfterEach
    void after() {
        user = null;
    }

    @Test
    void testEquals() {
        User autre = new User("Kim");
        assertEquals(user, autre);
    }

    @Test
    void getId() {
        assertNull(user.getId());
    }

    @Test
    void getAlias() {
        assertEquals("Kim", user.getAlias());
    }
}