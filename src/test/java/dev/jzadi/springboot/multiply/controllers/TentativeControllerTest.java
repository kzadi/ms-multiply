package dev.jzadi.springboot.multiply.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jzadi.springboot.multiply.controllers.TentativeController.Resultat;
import dev.jzadi.springboot.multiply.domains.Multiplication;
import dev.jzadi.springboot.multiply.domains.Tentative;
import dev.jzadi.springboot.multiply.domains.User;
import dev.jzadi.springboot.multiply.services.IMultiplicationService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Classe TentativeControllerTest, créée le 05/11/2022 à 18:41
 *
 * @author Joachim Zadi
 * @version 1.0 du 05/11/2022
 */
@SpringBootTest
@AutoConfigureMockMvc
class TentativeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMultiplicationService multiplicationService;

    private JacksonTester<Tentative> jsonTentative;
    private JacksonTester<Resultat> jsonResultat;
    private JacksonTester<List<Tentative>> jsonTentatives;

    @BeforeEach
    void init() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testPostRetourneResultatCorrect() throws Exception {
        testGenerique(true);
    }

    @Test
    void testPostRetourneResultatInCorrect() throws Exception {
        testGenerique(false);
    }

    @Test
    void testStatsUser() throws Exception {

        User user = new User("Joachim");
        Multiplication multiplication = new Multiplication(50, 70);
        Tentative tentative = new Tentative(user, multiplication, 3500, true);
        List<Tentative> recentesTentatives = Lists.newArrayList(tentative, tentative);

        given(multiplicationService.tentativeByUser("Joachim")).willReturn(recentesTentatives);

        mockMvc.perform(get("/tentatives/" + user.getAlias())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    private void testGenerique(boolean correct) throws Exception {

        given(multiplicationService.reponse(any(Tentative.class))).willReturn(correct);

        User user = new User("Joachim");
        Multiplication multiplication = new Multiplication(50, 30);
        Tentative tentative = new Tentative(user, multiplication, 1500, correct);

        mockMvc.perform(post("/tentatives")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTentative.write(tentative).getJson())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correct").value(correct))
                .andDo(print())
                .andReturn();
    }
}