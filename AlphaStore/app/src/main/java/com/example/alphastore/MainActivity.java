package com.example.alphastore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLoja;
    Button btnFuncionario;
    Button btnLogin;
    TextView txtCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoja = findViewById(R.id.btnLoja);
        btnLoja.setOnClickListener(this);
        btnFuncionario = findViewById(R.id.btnFuncionario);
        btnFuncionario.setOnClickListener(this);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        txtCadastrar = findViewById(R.id.txtCadastrar);
        txtCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnLoja:
                Intent telaLoja = new Intent(this, LojaActivity.class);
                startActivity(telaLoja);
                break;
            case R.id.btnFuncionario:
                // do your code
                break;
            case R.id.btnLogin:
                Intent telaLogin = new Intent(this, LoginActivity.class);
                startActivity(telaLogin);
                break;
            case R.id.txtCadastrar:
                Intent telaCadastro = new Intent(this, RegisterActivity.class);
                startActivity(telaCadastro);
                break;
            default:
                break;
        }
    }
}