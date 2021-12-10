package com.example.alphastore.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Connection extends SQLiteOpenHelper {

    private static final String NAME = "DATABASE00";
    private static final int VERSION = 2;

    private static final String SQL_CREATE_CATEGORIA = "create table categoria ( " +
            "pro_cat_id integer primary key autoincrement, " +
            "pro_cat_nome varchar(50) " +
            ");";

    private static final String SQL_CREATE_PRODUTO = "create table produto ( " +
            "prod_id integer primary key autoincrement, " +
            "prod_nome varchar(50), " +
            "prod_custo float " +
            ");";

    private static final String SQL_CREATE_CLIENTE = "create table cliente  ( " +
            " cli_id integer primary key autoincrement, " +
            " cli_nome varchar (50), " +
            " cli_cpf integer (8), " +
            " cli_fone long (20), " +
            " cli_email varchar(20), " +
            " cli_senha varchar(24)" +
            " );";

    private static final String SQL_CREATE_ENDERECO = "create table endereco  ( " +
            " cli_end_id integer primary key autoincrement, " +
            " cli_end_logradouro varchar (50), " +
            " cli_end_cep integer (8), " +
            " cli_end_bairro varchar(50), " +
            " cli_end_complemento varchar(30), " +
            " cli_end_numero integer(10), " +
            " cli_end_uf char(2)" +
            ");";

    private static final String SQL_CREATE_PEDIDOS = "create table pedidos (" +
            " ped_id integer primary key autoincrement, " +
            " ped_data date, " +
            " ped_total float, " +
            " ped_obs text" +
            ");";

    private static final String SQL_CREATE_ITEMS_PEDIDOS = "create table itensPedido  ( " +
            " ped_item_id integer primary key autoincrement, " +
            " ped_item_qnt integer, " +
            " ped_item_valor float" +
            ");";

    private static final String SQL_CREATE = "create table cliente  ( " +
            " cli_id integer primary key autoincrement, " +
            " cli_nome varchar (50), " +
            " cli_cpf integer (8), " +
            " cli_fone long (20), " +
            " cli_email varchar(20), " +
            " cli_senha varchar(24)" +
            ");" +

            "create table endereco  ( " +
            " cli_end_id integer primary key autoincrement, " +
            " cli_end_logradouro varchar (50), " +
            " cli_end_cep integer (8), " +
            " cli_end_bairro varchar(50), " +
            " cli_end_complemento varchar(30), " +
            " cli_end_numero integer(10), " +
            " cli_end_uf char(2), " +
            " cli_id integer not null, " +
            " constraint fk_cliente_endereco foreign key (cli_id) references cliente(cli_id)" +
            ");" +

            "create table pedidos (" +
            " ped_id integer primary key autoincrement, " +
            " ped_data date, " +
            " ped_total float, " +
            " ped_obs text, " +
            " cli_id integer not null, " +
            " constraint fk_cliente_pedido foreign key (cli_id) references cliente(cli_id)" +
            ");" +

            "create table itensPedido  ( " +
            " ped_item_id integer primary key autoincrement, " +
            " ped_item_qnt integer, " +
            " ped_item_valor float, " +
            " ped_id integer  not null, " +
            " constraint fk_pedido_itensPedido foreign key (ped_id) references pedido(ped_id)" +
            ");" +

            "create table produto  ( " +
            " prod_id integer primary key autoincrement, " +
            " prod_nome varchar(20), " +
            " prod_preco float, " +
            " ped_item_id integer  not null, " +
            " constraint fk_itensPedido_produto foreign key (ped_item_id) references itensPedido(ped_item_id)" +
            ");" +

            "create table categoria ( " +
            " pro_cat_id integer primary key autoincrement, " +
            " pro_cat_nome varchar (20), " +
            " prod_id integer not null, " +
            " constraint fk_produto_categoria foreign key (prod_id) references produto(prod_id)" +
            ");";

    public Connection (@Nullable Context context) { super (context, NAME, null, VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CATEGORIA);
        db.execSQL(SQL_CREATE_PRODUTO);
        db.execSQL(SQL_CREATE_CLIENTE);
        db.execSQL(SQL_CREATE_ENDERECO);
        db.execSQL(SQL_CREATE_PEDIDOS);
        db.execSQL(SQL_CREATE_ITEMS_PEDIDOS);
//        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
