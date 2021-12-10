package com.example.alphastore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alphastore.Util.MaskEditUtil;
import com.example.alphastore.dao.ClienteDAO;
import com.example.alphastore.dao.EnderecoDAO;
import com.example.alphastore.model.Cliente;
import com.example.alphastore.model.Endereco;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegistrar;
    EditText edtNome;
    EditText edtCpf;
    EditText edtTelefone;
    EditText edtEmail;
    EditText edtSenha;
    EditText edtSenhaConfirma;
    EditText edtLogradouro;
    EditText edtComplemento;
    EditText edtCep;
    EditText edtNumero;
    EditText edtBairro;
    Spinner spnUf;

    CompoundButton checkBoxPoliticaProvacidade;

    ClienteDAO clienteDao;
    EnderecoDAO enderecoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        clienteDao = new ClienteDAO(this);
        enderecoDao = new EnderecoDAO(this);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);

        edtNome = findViewById(R.id.edtNome);
        edtNome.setOnClickListener(this);
        edtCpf = findViewById(R.id.edtCpf);
        edtCpf.setOnClickListener(this);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtTelefone.setOnClickListener(this);
        edtEmail = findViewById(R.id.edtEmail);
        edtEmail.setOnClickListener(this);
        edtSenha = findViewById(R.id.edtSenha);
        edtSenha.setOnClickListener(this);
        edtSenhaConfirma = findViewById(R.id.edtSenhaConfirma);
        edtSenhaConfirma.setOnClickListener(this);
        edtLogradouro = findViewById(R.id.edtLogradouro);
        edtLogradouro.setOnClickListener(this);
        edtComplemento = findViewById(R.id.edtComplemento);
        edtComplemento.setOnClickListener(this);
        edtCep = findViewById(R.id.edtCep);
        edtCep.setOnClickListener(this);
        edtNumero = findViewById(R.id.edtNumero);
        edtNumero.setOnClickListener(this);
        edtBairro = findViewById(R.id.edtBairro);
        edtBairro.setOnClickListener(this);

        spnUf = findViewById(R.id.spnUf);

        edtTelefone.addTextChangedListener(MaskEditUtil.mask(edtTelefone, MaskEditUtil.FORMAT_FONE));
        edtCep.addTextChangedListener(MaskEditUtil.mask(edtCep,MaskEditUtil.FORMAT_CEP));
        edtCpf.addTextChangedListener(MaskEditUtil.mask(edtCpf,MaskEditUtil.FORMAT_CPF));

        edtNome.setText("");

        checkBoxPoliticaProvacidade = findViewById(R.id.checkBoxPoliticaProvacidade);
        checkBoxPoliticaProvacidade.setOnCheckedChangeListener(null);
        checkBoxPoliticaProvacidade.setChecked(false);
        checkBoxPoliticaProvacidade.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        boolean isAllFieldsChecked = CheckAllFields();

        if (!isAllFieldsChecked) {
            return;
        }
        Cliente cliente = new Cliente(edtNome.getText().toString(),
                Long.parseLong(edtCpf.getText().toString()),
                Long.parseLong(edtTelefone.getText().toString()),
                edtEmail.getText().toString(),
                edtSenha.getText().toString());
        clienteDao.inserir(cliente);

        Endereco endereco = new Endereco(edtLogradouro.getText().toString(),
                Integer.parseInt(edtCep.getText().toString()),
                edtBairro.getText().toString(),
                edtComplemento.getText().toString(),
                Integer.parseInt(edtNumero.getText().toString()),
                spnUf.getSelectedItem().toString());
                enderecoDao.inserir(endereco);

        Toast.makeText(this,"Registrado",Toast.LENGTH_LONG).show();
        finish();
    }

    private boolean CheckAllFields(){
        boolean fieldsOk = true;
        if (edtNome.getText().length() == 0) {
            edtNome.setError("Campo Obrigatório");
            fieldsOk = false;
        }

        if (edtCpf.getText().length() == 0) {
            edtCpf.setError("Campo Obrigatório");
            fieldsOk = false;
        }

        if (edtEmail.getText().length() == 0) {
            edtEmail.setError("Campo Obrigatório");
            fieldsOk = false;
        }

        if (edtTelefone.getText().length() == 0) {
            edtTelefone.setError("Campo Obrigatório");
            fieldsOk = false;
        }
        if (edtLogradouro.getText().length() == 0) {
            edtLogradouro.setError("Campo Obrigatório");
            fieldsOk = false;
        }
        if (edtCep.getText().length() == 0) {
            edtCep.setError("Campo Obrigatório");
            fieldsOk = false;
        }
        if (edtCep.getText().length() < 8) {
            edtCep.setError("CEP Inválido");
            fieldsOk = false;
        }
        if (edtBairro.getText().length() == 0) {
            edtBairro.setError("Campo Obrigatório");
            fieldsOk = false;
        }

        if (edtComplemento.getText().length() == 0) {
            edtComplemento.setError("Campo Obrigatório");
            fieldsOk = false;
        }
        if (edtNumero.getText().length() == 0) {
            edtNumero.setError("Campo Obrigatório");
            fieldsOk = false;
        }
        if (edtSenha.getText().length() == 0) {
            edtSenha.setError("Campo Obrigatório");
            fieldsOk = false;
        } else if (edtSenha.getText().length() < 5) {
            edtSenha.setError("Senha deve conter no mínimo 5 caracteres");
            fieldsOk = false;
        } else if(!edtSenha.getText().toString().equals(edtSenhaConfirma.getText().toString())){
            edtSenhaConfirma.setError("Senhas não coincidem!");
            fieldsOk = false;
        }
        if (!Cliente.validaNome(edtNome.getText().toString())) {
            EditText til = (EditText) findViewById(R.id.edtNome);
            til.setError("Nome inválido! Somente caracteres de [A-Z], [a-z]");
            fieldsOk = false;
        }else if(!Cliente.validaEmail(edtEmail.getText().toString())){
            EditText til = (EditText) findViewById(R.id.edtEmail);
            til.setError("Email inválido!");
            fieldsOk = false;
        }
        // after all validation return true.
        return fieldsOk;
    }
}