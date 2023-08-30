package br.edu.ufcg.computacao.psoft.commerce.model;

import lombok.Data;

@Data
public class Logradouro {
    private Long id;
    private String tipo;
    private String nome;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;

    public Logradouro() {
    }

    public Logradouro(String tipo, String nome, String bairro, String cidade, String estado, String pais, String cep) {
        this.tipo = tipo;
        this.nome = nome;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
    }

}
