package br.edu.ufcg.computacao.psoft.commerce.service;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import br.edu.ufcg.computacao.psoft.commerce.repository.LogradouroVolatilRepository;

@Service
public class LogradouroXXXXService {

    @Autowired
    private LogradouroVolatilRepository logradouroRepository;

    public List<Logradouro> getAllLogradouros() {
        return logradouroRepository.getAllLogradouros();
    }

    public Logradouro getLogradouroById(Long id) {
        return logradouroRepository.getLogradouroById(id);
    }

    public Logradouro createLogradouro(Logradouro logradouro) {
        validateLogradouroAttributes(logradouro);
        return logradouroRepository.createLogradouro(logradouro);
    }

    public Logradouro updateLogradouro(Long id, Logradouro logradouro) {
        validateLogradouroAttributes(logradouro);
        Logradouro existingLogradouro = getLogradouroById(id);

        existingLogradouro.setTipo(logradouro.getTipo());
        existingLogradouro.setNome(logradouro.getNome());
        existingLogradouro.setBairro(logradouro.getBairro());
        existingLogradouro.setCidade(logradouro.getCidade());
        existingLogradouro.setEstado(logradouro.getEstado());
        existingLogradouro.setPais(logradouro.getPais());
        existingLogradouro.setCep(logradouro.getCep());

        return logradouroRepository.updateLogradouro(id, existingLogradouro);
    }

    public void deleteLogradouro(Long id) {
        logradouroRepository.deleteLogradouro(id);
    }

    public Logradouro patchLogradouro(Long id, List<PatchOperation> patchOperations) {
        Logradouro logradouro = getLogradouroById(id);

        for (PatchOperation operation : patchOperations) {
            if (operation.getPath().equals("/tipo")) {
                logradouro.setTipo(operation.getValue());
            }
            if (operation.getPath().equals("/nome")) {
                logradouro.setNome(operation.getValue());
            }
            // Implement other patch operations if needed
        }

        return logradouroRepository.updateLogradouro(id, logradouro);
    }

    private void validateLogradouroAttributes(Logradouro logradouro) {
        if (logradouro.getTipo() == null || logradouro.getTipo().isEmpty()) {
            throw new IllegalArgumentException("Tipo do logradouro é obrigatório.");
        }

        if (logradouro.getNome() == null || logradouro.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do logradouro é obrigatório.");
        }

        // Implement other attribute validation logic here

        if (logradouro.getCep() == null || logradouro.getCep().isEmpty()) {
            throw new IllegalArgumentException("CEP do logradouro é obrigatório.");
        }

        // Implement other validations according to business rules
    }
}