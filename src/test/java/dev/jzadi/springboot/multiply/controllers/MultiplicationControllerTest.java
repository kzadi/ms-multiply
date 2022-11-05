package dev.jzadi.springboot.multiply.controllers;

import dev.jzadi.springboot.multiply.domains.Multiplication;
import dev.jzadi.springboot.multiply.services.IMultiplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Classe MultiplicationControllerTest, créée le 05/11/2022 à 17:12
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
@SpringBootTest
@AutoConfigureMockMvc
class MultiplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMultiplicationService multiplicationService;

    @Test
    void testCreerMultiplication() throws Exception {
        when(multiplicationService.genererMultiplication()).thenReturn(new Multiplication(30, 50));

        mockMvc.perform(get("/multiply/random")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.facteurA").value(30))
                .andExpect(jsonPath("$.facteurB").value(50))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}