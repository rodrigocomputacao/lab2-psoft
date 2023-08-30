package br.edu.ufcg.computacao.psoft.commerce.repository;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.mapping.List;
import org.hibernate.mapping.Map;
import org.springframework.stereotype.Repository;

import br.edu.ufcg.computacao.psoft.commerce.model.Produto;

@Repository
public class ProdutoVolatilRepository {

    private Map<Long, Produto> produtos = new HashMap<>();
    private long nextId = 1;

    public List<Produto> getAllProdutos() {
        return new ArrayList<>(produtos.values());
    }

    public Produto getProdutoById(Long id) {
        return produtos.get(id);
    }

    public Produto createProduto(Produto produto) {
        produto.setId(nextId);
        produtos.put(nextId, produto);
        nextId++;
        return produto;
    }

    public Produto updateProduto(Long id, Produto produto) {
        if (!produtos.containsKey(id)) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        produto.setId(id);
        produtos.put(id, produto);
        return produto;
    }

    public void deleteProduto(Long id) {
        produtos.remove(id);
    }
}
