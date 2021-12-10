package com.example.alphastore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alphastore.Adapters.ProdutoAdapter;
import com.example.alphastore.dao.ProdutoDAO;
import com.example.alphastore.model.Produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LojaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    Button btnCart;

    ListView lstDados;
    ArrayList<Produto> listaProd;
    ProdutoDAO produtoDao;

    List<Produto> listaCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);

        btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(this);

        listaCarrinho = new ArrayList<Produto>();

        produtoDao = new ProdutoDAO(this);
        listaProd = (ArrayList<Produto>) produtoDao.lista();
        ArrayAdapter<Produto> produtoAdapter = new ProdutoAdapter(this, listaProd);

        lstDados = findViewById(R.id.lstCarrinho);
        lstDados.setAdapter(produtoAdapter);
        lstDados.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onClick(View v) {
        if (v == btnCart) {
            Intent telaCarrinho = new Intent(this, CarrinhoActivity.class);
            Bundle extras = new Bundle();
            extras.putSerializable("listaCarrinho", (Serializable)listaCarrinho);
            telaCarrinho.putExtras(extras);
            startActivity(telaCarrinho);
        }
    }

    public void addToCart(Produto produto) {
        listaCarrinho.add(produto);
    }
}