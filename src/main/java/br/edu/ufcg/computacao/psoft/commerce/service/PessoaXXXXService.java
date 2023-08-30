package br.edu.ufcg.computacao.psoft.commerce.service;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.repository.PessoaVolatilRepository;

@Service
public class PessoaXXXXService {

    @Autowired
    private PessoaVolatilRepository pessoaRepository;

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.getAllPessoas();
    }

    public Pessoa getPessoaById(Long id) {
        return pessoaRepository.getPessoaById(id);
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        validatePessoaAttributes(pessoa);
        return pessoaRepository.createPessoa(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        validatePessoaAttributes(pessoa);
        Pessoa existingPessoa = getPessoaById(id);

        if (!existingPessoa.getCpf().equals(pessoa.getCpf())) {
            throw new IllegalArgumentException("CPF cannot be updated.");
        }

        existingPessoa.setNome(pessoa.getNome());
        existingPessoa.setEmail(pessoa.getEmail());
        existingPessoa.setDataNascimento(pessoa.getDataNascimento());
        existingPessoa.setEnderecos(pessoa.getEnderecos());
        existingPessoa.setTelefones(pessoa.getTelefones());
        existingPessoa.setProfissao(pessoa.getProfissao());

        return pessoaRepository.updatePessoa(id, existingPessoa);
    }

    public void deletePessoa(Long id) {
        pessoaRepository.deletePessoa(id);
    }

    public Pessoa patchPessoa(Long id, List<PatchOperation> patchOperations) {
        Pessoa pessoa = getPessoaById(id);

        for (PatchOperation operation : patchOperations) {
            if (operation.getPath().equals("/email")) {
                pessoa.setEmail(operation.getValue());
            }
            // Implement other patch operations if needed
        }

        return pessoaRepository.updatePessoa(id, pessoa);
    }

    private void validatePessoaAttributes(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome da pessoa é obrigatório.");
        }

        if (pessoa.getCpf() == null || pessoa.getCpf().isEmpty()) {
            throw new IllegalArgumentException("CPF da pessoa é obrigatório.");
        }

        // Implementar validação de formato do CPF (seguindo um padrão válido)

        if (pessoa.getEmail() == null || pessoa.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email da pessoa é obrigatório.");
        }

        // Implementar validação de formato do email (usando expressão regular, por
        // exemplo)

        if (pessoa.getTelefones() == null || pessoa.getTelefones().isEmpty()) {
            throw new IllegalArgumentException("Pelo menos um telefone é obrigatório.");
        }

        // Implementar outras validações específicas dos telefones

        if (pessoa.getProfissao() == null || pessoa.getProfissao().isEmpty()) {
            throw new IllegalArgumentException("Profissão da pessoa é obrigatória.");
        }

    }
}