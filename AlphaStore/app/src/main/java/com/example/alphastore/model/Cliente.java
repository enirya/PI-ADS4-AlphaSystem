package com.example.alphastore.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    private Long cli_id;
    private String cli_nome;
    private Long cli_cpf;
    private Long cli_fone;
    private String cli_email;
    private String cli_senha;

    public Cliente() {
    }

    public Cliente(String cli_nome, Long cli_cpf, Long cli_fone, String cli_email, String cli_senha) {

        this.cli_nome = cli_nome;
        this.cli_cpf = cli_cpf;
        this.cli_fone = cli_fone;
        this.cli_email = cli_email;
        this.cli_senha = cli_senha;
    }

    public Long getCli_id() {
        return cli_id;
    }

    public void setCli_id(Long cli_id) {
        this.cli_id = cli_id;
    }

    public String getCli_nome() {
        return cli_nome;
    }

    public void setCli_nome(String cli_nome) {
        if (this.validaNome(cli_nome)) {
            this.cli_nome = cli_nome;
        } else {
            throw new RuntimeException("Nome inválido");
        }
    }

    public Long getCli_cpf() {
        return cli_cpf;
    }

    public void setCli_cpf(Long cli_cpf) {
        this.cli_cpf = cli_cpf;
    }

    public Long getCli_fone() {
        return cli_fone;
    }

    public void setCli_fone(Long cli_fone) {
        this.cli_fone = cli_fone;
    }

    public String getCli_email() {
        return cli_email;
    }

    public void setCli_email(String cli_email) {
        this.cli_email = cli_email;
    }

    public String getCli_senha() {
        return cli_senha;
    }

    public void setCli_senha(String cli_senha) {
        this.cli_senha = cli_senha;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cli_id=" + cli_id +
                ", cli_nome='" + cli_nome + '\'' +
                ", cli_cpf=" + cli_cpf +
                ", cli_fone=" + cli_fone +
                ", cli_email='" + cli_email + '\'' +
                ", cli_senha='" + cli_senha + '\'' +
                '}';
    }
    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    public static boolean validaNome(String cli_nome) {
        String concatNome = cli_nome.replace(" ", "");
        if (concatNome.matches("[a-zA-Z]+")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean validaEmail(String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

//    public static boolean validaCPF(String cli_cpf){
//        if(cli_cpf.length()<11){
//
//        }
//    }

}
