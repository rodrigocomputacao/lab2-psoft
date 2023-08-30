package br.edu.ufcg.computacao.psoft.commerce.repository;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.mapping.List;
import org.hibernate.mapping.Map;
import org.springframework.stereotype.Repository;

import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;

@Repository
public class PessoaVolatilRepository {

    private Map<Long, Pessoa> pessoas = new HashMap<>();
    private long nextId = 1;

    public List<Pessoa> getAllPessoas() {
        return new ArrayList<>(pessoas.values());
    }

    public Pessoa getPessoaById(Long id) {
        return pessoas.get(id);
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        pessoa.setId(nextId);
        pessoas.put(nextId, pessoa);
        nextId++;
        return pessoa;
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        if (!pessoas.containsKey(id)) {
            throw new IllegalArgumentException("Pessoa não encontrada.");
        }
        pessoa.setId(id);
        pessoas.put(id, pessoa);
        return pessoa;
    }

    public void deletePessoa(Long id) {
        pessoas.remove(id);
    }
}
