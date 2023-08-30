package br.edu.ufcg.computacao.psoft.commerce.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProdutoV1RestController.class)
public class ProdutoV1RestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateProduto() throws Exception {
        String produtoJson = "{\"nome\":\"Product ABC\",\"codigoBarras\":\"1234567890123\",\"valor\":10.0,\"fabricante\":\"Company XYZ\"}";

        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllProdutos() throws Exception {
        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOneProduto() throws Exception {
        mockMvc.perform(get("/produtos/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateProduto() throws Exception {
        String produtoJson = "{\"nome\":\"Updated Product\",\"valor\":15.0}";

        mockMvc.perform(put("/produtos/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProduto() throws Exception {
        mockMvc.perform(delete("/produtos/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testPatchCodigoBarrasProduto() throws Exception {
        String patchJson = "[{\"op\":\"replace\",\"path\":\"/codigoBarras\",\"value\":\"9876543210123\"}]";

        mockMvc.perform(patch("/produtos/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(patchJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testPatchValorProduto() throws Exception {
        String patchJson = "[{\"op\":\"replace\",\"path\":\"/valor\",\"value\":20.0}]";

        mockMvc.perform(patch("/produtos/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(patchJson))
                .andExpect(status().isOk());
    }
}
