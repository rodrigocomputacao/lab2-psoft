package br.edu.ufcg.computacao.psoft.commerce.model;

import lombok.Data;

@Data
public class Produto {
    private Long id;
    private String nome;
    private String codigoBarras;
    private double valor;
    private String fabricante;

    public Produto() {
    }

    public Produto(String nome, String codigoBarras, double valor, String fabricante) {
        this.nome = nome;
        this.codigoBarras = codigoBarras;
        this.valor = valor;
        this.fabricante = fabricante;
    }

}
