package br.edu.ufcg.computacao.psoft.commerce.repository;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.mapping.List;
import org.hibernate.mapping.Map;
import org.springframework.stereotype.Repository;

import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;

@Repository
public class LogradouroVolatilRepository {

    private Map<Long, Logradouro> logradouros = new HashMap<>();
    private long nextId = 1;

    public List<Logradouro> getAllLogradouros() {
        return new ArrayList<>(logradouros.values());
    }

    public Logradouro getLogradouroById(Long id) {
        return logradouros.get(id);
    }

    public Logradouro createLogradouro(Logradouro logradouro) {
        logradouro.setId(nextId);
        logradouros.put(nextId, logradouro);
        nextId++;
        return logradouro;
    }

    public Logradouro updateLogradouro(Long id, Logradouro logradouro) {
        if (!logradouros.containsKey(id)) {
            throw new IllegalArgumentException("Logradouro não encontrado.");
        }
        logradouro.setId(id);
        logradouros.put(id, logradouro);
        return logradouro;
    }

    public void deleteLogradouro(Long id) {
        logradouros.remove(id);
    }
}
