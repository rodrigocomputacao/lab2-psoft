package br.edu.ufcg.computacao.psoft.commerce.model;

import java.time.LocalDate;

import org.hibernate.mapping.List;

import lombok.Data;

@Data
public class Pessoa {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private List<String> telefones;
    private LocalDate dataNascimento;
    private List<Endereco> enderecos;
    private String profissao;

    // Construtores
    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }
}
