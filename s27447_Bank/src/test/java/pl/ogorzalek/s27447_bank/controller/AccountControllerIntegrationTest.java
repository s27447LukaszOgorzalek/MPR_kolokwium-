package pl.ogorzalek.s27447_bank.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ogorzalek.s27447_bank.model.Currency;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    /* @BeforeEach
    public void setUp() {
    } */

    @Test
    public void testGetAccountById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pesel").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownerFirstName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownerLastName").exists());
    }

    @Test
    public void testCreateAccount() throws Exception {
        String accountJson = "{\"pesel\":\"12345678901\",\"balance\":100.00,\"currency\":\"PLN\",\"ownerFirstName\":\"Jan\",\"ownerLastName\":\"Kowalski\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pesel").value("12345678901"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(100.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value(Currency.PLN.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownerFirstName").value("Jan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownerLastName").value("Kowalski"));
    }
}
