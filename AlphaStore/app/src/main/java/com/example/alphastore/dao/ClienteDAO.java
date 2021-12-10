package com.example.alphastore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alphastore.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private final String TABELA = "cliente";
    private final String [] CAMPOS = {"cli_id, cli_nome, cli_cpf, cli_fone, cli_email, cli_senha"} ;
    private Connection conexao;
    private SQLiteDatabase banco;

//    DatabaseHelper myDb = new DatabaseHelper(LoginActivity.this);
//    storedPassword = myDb.getData(name, password);

    public ClienteDAO(Context context){
        conexao = new Connection(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Cliente cliente){
        ContentValues values = preencherValor(cliente);
        return banco.insert(TABELA,null,values);
    }

    private ContentValues preencherValor(Cliente cliente){
        ContentValues values = new ContentValues();

        values.put("cli_nome", cliente.getCli_nome());
        values.put("cli_cpf", cliente.getCli_cpf());
        values.put("cli_fone", cliente.getCli_fone());
        values.put("cli_email", cliente.getCli_email());
        values.put("cli_senha", cliente.getCli_senha());

        return values;
    }

    public List<Cliente> lista(){
        Cursor c = banco.query(TABELA,CAMPOS,null,null,null,null,null);
        List<Cliente> lista = new ArrayList<>();
        while (c.moveToNext()){
            Cliente cliente = new Cliente();
            cliente.setCli_id(c.getLong(0));
            cliente.setCli_nome(c.getString(1));
            cliente.setCli_fone(c.getLong(2));
            cliente.setCli_email(c.getString(3));
            cliente.setCli_senha(c.getString(4));
            lista.add(cliente);
        }
        return lista;
    }
}
