package com.example.alphastore.model;

public class Produto implements java.io.Serializable {

    private Long prod_id;
    private String prod_nome = "";
    private Float prod_preco = 0F;

    public Produto() {

    }

    public Produto(Long id, String nome, Float preco) {
        this.prod_id = id;
        this.prod_nome = nome;
        this.prod_preco = preco;
    }

    public Long getId() {
        return prod_id;
    }

    public void setId(Long id) {
        this.prod_id = id;
    }

    public String getNome() {
        return prod_nome;
    }

    public void setNome(String nome) {
        this.prod_nome = nome;
    }

    public Float getPreco() {
        return prod_preco;
    }

    public void setPreco(Float preco) {
        this.prod_preco = preco;
    }

    @Override
    public String toString() {
        return prod_id.toString() + " - " + prod_nome + " - " + prod_preco.toString();
    }


}