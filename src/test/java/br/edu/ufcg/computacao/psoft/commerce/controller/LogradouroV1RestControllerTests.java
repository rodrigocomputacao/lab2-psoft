package br.edu.ufcg.computacao.psoft.commerce.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LogradouroV1RestController.class)
public class LogradouroV1RestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateLogradouro() throws Exception {
        String logradouroJson = "{\"tipo\":\"Rua\",\"nome\":\"Main Street\",\"bairro\":\"Downtown\",\"cidade\":\"Cityville\",\"estado\":\"ST\",\"pais\":\"Country\",\"cep\":\"12345678\"}";

        mockMvc.perform(post("/logradouros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(logradouroJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllLogradouros() throws Exception {
        mockMvc.perform(get("/logradouros"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOneLogradouro() throws Exception {
        mockMvc.perform(get("/logradouros/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateLogradouro() throws Exception {
        String logradouroJson = "{\"tipo\":\"Avenida\",\"nome\":\"Updated Avenue\"}";

        mockMvc.perform(put("/logradouros/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(logradouroJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteLogradouro() throws Exception {
        mockMvc.perform(delete("/logradouros/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testPatchLogradouro() throws Exception {
        String patchJson = "[{\"op\":\"replace\",\"path\":\"/tipo\",\"value\":\"Alameda\"}]";

        mockMvc.perform(patch("/logradouros/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(patchJson))
                .andExpect(status().isOk());
    }
}
