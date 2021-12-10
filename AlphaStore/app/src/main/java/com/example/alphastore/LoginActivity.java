package com.example.alphastore;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alphastore.dao.Connection;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView edtCampoSenha;
    TextView edtCampoEmail;
    TextView txtEsqueciSenha;
    Button btnEntrar;
    Connection conexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);
        edtCampoSenha = findViewById(R.id.edtCampoSenha);
        edtCampoSenha.setOnClickListener(this);
        edtCampoEmail = findViewById(R.id.edtCampoEmail);
        edtCampoEmail.setOnClickListener(this);
        txtEsqueciSenha = findViewById(R.id.txtEsqueciSenha);
        txtEsqueciSenha.setOnClickListener(this);
    }

    public Boolean Auth(){
        String email = edtCampoEmail.getText().toString();
        String senha = edtCampoSenha.getText().toString();

        conexao = new Connection(this);
        SQLiteDatabase db = conexao.getReadableDatabase();
        String sql_busca_cliente = "SELECT * FROM cliente WHERE cli_email = " + "'" + email + "';";
        Cursor mCursor = db.rawQuery(sql_busca_cliente,null);
        String senhaBanco = "";
        if (mCursor.moveToFirst()) {
            senhaBanco = mCursor.getString(5);
        }

        if (senhaBanco.equals(senha)) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean AdmAuth(){
        String email = edtCampoEmail.getText().toString();
        String senha = edtCampoSenha.getText().toString();

        if(email.equals("admin") && senha.equals("admin")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        if (Auth()==true) {
            Intent telaLoja = new Intent(this, LojaActivity.class);
            startActivity(telaLoja);
        }
        else if(AdmAuth()==true){
            Intent telaFunc = new Intent(this, FuncActivity.class);
            startActivity(telaFunc);
        }
        else {
            Toast.makeText(this,"Senha inválida",Toast.LENGTH_LONG).show();
        }
    }
}
