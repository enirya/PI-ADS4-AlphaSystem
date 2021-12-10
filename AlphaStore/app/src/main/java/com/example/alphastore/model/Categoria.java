package com.example.alphastore.model;

public class Categoria {
    private Long pro_cat_id;
    private String pro_cat_nome;

    public Categoria() {
    }

    public Categoria(Long pro_cat_id, String pro_cat_nome) {
        this.pro_cat_id = pro_cat_id;
        this.pro_cat_nome = pro_cat_nome;
    }

    public Long getPro_cat_id() {
        return pro_cat_id;
    }

    public void setPro_cat_id(Long pro_cat_id) {
        this.pro_cat_id = pro_cat_id;
    }

    public String getPro_cat_nome() {
        return pro_cat_nome;
    }

    public void setPro_cat_nome(String pro_cat_nome) {
        this.pro_cat_nome = pro_cat_nome;
    }

    @Override
    public String toString() {
        return pro_cat_id + " - " + pro_cat_nome;
    }

}
