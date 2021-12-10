package com.example.alphastore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.alphastore.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final String TABELA = "produto";
    private final String[] CAMPOS = {"prod_id, prod_nome, prod_custo"};
    private Connection conexao;
    private SQLiteDatabase banco;

    public ProdutoDAO(Context context){
        conexao = new Connection(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencherValores(Produto produto){
        ContentValues values = new ContentValues();

        values.put("prod_nome", produto.getNome());
        values.put("prod_custo", produto.getPreco());

        return values;
    }

    public long inserir(Produto produto){
        ContentValues values = preencherValores(produto);
        return banco.insert(TABELA,null,values);
    }

    public long alterar(Produto produto){
        ContentValues values = preencherValores(produto);
        return banco.update(TABELA,values,"prod_id = ?", new String[]{String.valueOf(produto.getId())});
    }

    public long excluir(Produto produto){
        return banco.delete(TABELA,"prod_id = ?",new String[]{String.valueOf(produto.getId())});
    }

    public List<Produto> lista(){
        Cursor c = banco.query(TABELA,CAMPOS,null,null,null,null,null);
        List<Produto> lista = new ArrayList<>();
        while (c.moveToNext()){
            Produto produto = new Produto();
            produto.setId(c.getLong(0));
            produto.setNome(c.getString(1));
            produto.setPreco(c.getFloat(2));
            lista.add(produto);
        }
        return lista;
    }
}