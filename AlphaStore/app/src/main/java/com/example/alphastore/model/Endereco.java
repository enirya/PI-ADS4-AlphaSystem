package com.example.alphastore.model;

public class Endereco {

    private String cli_end_logradouro;
    private int cli_end_cep;
    private String cli_end_bairro;
    private String cli_end_complemento;
    private int cli_end_numero;
    private String cli_end_uf;

    public Endereco() {
    }

    public Endereco(String cli_end_logradouro, int cli_end_cep, String cli_end_bairro, String cli_end_complemento, int cli_end_numero, String cli_end_uf) {

        this.cli_end_logradouro = cli_end_logradouro;
        this.cli_end_cep = cli_end_cep;
        this.cli_end_bairro = cli_end_bairro;
        this.cli_end_complemento = cli_end_complemento;
        this.cli_end_numero = cli_end_numero;
        this.cli_end_uf = cli_end_uf;
    }


    public String getCli_end_logradouro() {
        return cli_end_logradouro;
    }

    public void setCli_end_logradouro(String cli_end_logradouro) {
        this.cli_end_logradouro = cli_end_logradouro;
    }

    public int getCli_end_cep() {
        return cli_end_cep;
    }

    public void setCli_end_cep(int cli_end_cep) {
        this.cli_end_cep = cli_end_cep;
    }

    public String getCli_end_bairro() {
        return cli_end_bairro;
    }

    public void setCli_end_bairro(String cli_end_bairro) {
        this.cli_end_bairro = cli_end_bairro;
    }

    public String getCli_end_complemento() {
        return cli_end_complemento;
    }

    public void setCli_end_complemento(String cli_end_complemento) {
        this.cli_end_complemento = cli_end_complemento;
    }

    public int getCli_end_numero() {
        return cli_end_numero;
    }

    public void setCli_end_numero(int cli_end_numero) {
        this.cli_end_numero = cli_end_numero;
    }

    public String getCli_end_uf() {
        return cli_end_uf;
    }

    public void setCli_end_uf(String cli_end_uf) {
        this.cli_end_uf = cli_end_uf;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cli_end_logradouro='" + cli_end_logradouro + '\'' +
                ", cli_end_cep=" + cli_end_cep +
                ", cli_end_bairro='" + cli_end_bairro + '\'' +
                ", cli_end_complemento='" + cli_end_complemento + '\'' +
                ", cli_end_numero=" + cli_end_numero +
                ", cli_end_uf='" + cli_end_uf + '\'' +
                '}';
    }
}
