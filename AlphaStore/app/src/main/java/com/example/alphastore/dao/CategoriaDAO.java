package com.example.alphastore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alphastore.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private final String TABELA = "categoria";
    private final String[] CAMPOS = {"pro_cat_id, pro_cat_nome"};
    private Connection conexao;
    private SQLiteDatabase banco;

    public CategoriaDAO(Context context){
        conexao = new Connection(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencherValores(Categoria categoria){
        ContentValues values = new ContentValues();
        values.put("pro_cat_nome", categoria.getPro_cat_nome());

        return values;
    }

    public long inserir(Categoria categoria){
        ContentValues values = preencherValores(categoria);
        return banco.insert(TABELA,null,values);
    }

    public long alterar(Categoria categoria){
        ContentValues values = preencherValores(categoria);
        return banco.update(TABELA,values,"pro_cat_id = ?", new String[]{String.valueOf(categoria.getPro_cat_id())});
    }

    public long excluir(Categoria categoria){
        return banco.delete(TABELA,"pro_cat_id = ?",new String[]{String.valueOf(categoria.getPro_cat_id())});
    }

    public List<Categoria> lista(){
        Cursor c = banco.query(TABELA,CAMPOS,null,null,null,null,null);
        List<Categoria> lista = new ArrayList<>();
        while (c.moveToNext()){
            Categoria categoria = new Categoria();
            categoria.setPro_cat_id(c.getLong(0));
            categoria.setPro_cat_nome(c.getString(1));
            lista.add(categoria);
        }
        return lista;
    }
}
