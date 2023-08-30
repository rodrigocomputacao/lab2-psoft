package br.edu.ufcg.computacao.psoft.commerce.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PessoaV1RestController.class)
public class PessoaV1RestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreatePessoa() throws Exception {
        String pessoaJson = "{\"nome\":\"John Doe\",\"cpf\":\"12345678900\",\"email\":\"john@example.com\"}";

        mockMvc.perform(post("/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pessoaJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllPessoas() throws Exception {
        mockMvc.perform(get("/pessoas"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOnePessoa() throws Exception {
        mockMvc.perform(get("/pessoas/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePessoa() throws Exception {
        String pessoaJson = "{\"nome\":\"Updated Name\",\"email\":\"updated@example.com\"}";

        mockMvc.perform(put("/pessoas/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(pessoaJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePessoa() throws Exception {
        mockMvc.perform(delete("/pessoas/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testPatchPessoa() throws Exception {
        String patchJson = "[{\"op\":\"replace\",\"path\":\"/email\",\"value\":\"newemail@example.com\"}]";

        mockMvc.perform(patch("/pessoas/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(patchJson))
                .andExpect(status().isOk());
    }
}
