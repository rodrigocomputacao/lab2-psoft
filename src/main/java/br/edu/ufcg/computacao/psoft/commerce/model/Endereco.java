package br.edu.ufcg.computacao.psoft.commerce.model;

import lombok.Data;

@Data
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;

    public Endereco() {
    }

    public Endereco(String logradouro, String numero, String complemento) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
    }

}
