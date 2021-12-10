package com.example.alphastore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alphastore.Adapters.CarrinhoAdapter;
import com.example.alphastore.model.Produto;

import java.util.ArrayList;

public class CarrinhoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {


    Button btnCart;
    Button btnMenu;
    Button btnVoltar;
    Button btnFinalizar;
    ListView lstCarrinho;
    EditText edtCepFrete;
    TextView txtValorFrete;
    TextView txtTotal;

    ArrayList<Produto> listaCarrinho;
    Float total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(this);
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(this);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnFinalizar.setOnClickListener(this);
        edtCepFrete = findViewById(R.id.edtCepFrete);
        edtCepFrete.setOnClickListener(this);
        txtValorFrete = findViewById(R.id.txtValorFrete);
        txtValorFrete.setOnClickListener(this);
        txtTotal = findViewById(R.id.txtTotal);
        txtTotal.setOnClickListener(this);
        lstCarrinho = findViewById(R.id.lstCarrinho);
        lstCarrinho.setOnItemClickListener(this);

        listaCarrinho = (ArrayList<Produto>) getIntent().getExtras().getSerializable("listaCarrinho");
        ArrayAdapter<Produto> carrinhoAdapter = new CarrinhoAdapter(this, listaCarrinho);
        lstCarrinho.setAdapter(carrinhoAdapter);

        total = 0F;
        for (Produto produto : listaCarrinho) {
            total = total + produto.getPreco();
        }
        String totalString = String.format("%.02f", total);
        txtTotal.setText(totalString);
    }

    @Override
    public void onClick(View v) {
        if (v == btnVoltar) {
            Intent intLoja = new Intent(this, LojaActivity.class);
            startActivityForResult(intLoja, 1);
        } else if (v == btnFinalizar){
            Intent intLoja = new Intent(this, LojaActivity.class);
            startActivityForResult(intLoja, 1);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}