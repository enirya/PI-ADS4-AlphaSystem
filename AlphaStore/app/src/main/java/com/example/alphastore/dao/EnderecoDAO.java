package com.example.alphastore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.alphastore.model.Endereco;

public class EnderecoDAO {
    private final String TABELA = "endereco";
    private final String [] CAMPOS = {"cli_end_id, cli_end_logradouro, cli_end_cep, cli_end_bairro, cli_end_complemento, cli_end_numero, cli_end_uf"} ;
    private Connection conexao;
    private SQLiteDatabase banco;

    public EnderecoDAO(Context context){
        conexao = new Connection(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Endereco endereco){
        ContentValues values = preencherValor(endereco);
        return banco.insert(TABELA,null,values);
    }

    private ContentValues preencherValor(Endereco cliente){
        ContentValues values = new ContentValues();

        values.put("cli_end_logradouro", cliente.getCli_end_logradouro());
        values.put("cli_end_cep", cliente.getCli_end_cep());
        values.put("cli_end_bairro", cliente.getCli_end_bairro());
        values.put("cli_end_complemento", cliente.getCli_end_complemento());
        values.put("cli_end_numero", cliente.getCli_end_numero());
        values.put("cli_end_uf", cliente.getCli_end_uf());

        return values;
    }
}
