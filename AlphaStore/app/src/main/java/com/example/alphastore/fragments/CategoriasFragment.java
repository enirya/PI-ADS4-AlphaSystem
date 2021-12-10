package com.example.alphastore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.alphastore.FuncActivity;
import com.example.alphastore.R;
import com.example.alphastore.dao.CategoriaDAO;
import com.example.alphastore.model.Categoria;

public class CategoriasFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText edtCodigo;
    EditText edtNome;

    Button btnGravar;
    Button btnExcluir;

    ListView listaCategorias;
    ListAdapter listAdapter;

    int posicaoSelecionada = -1;

    String acao = "Inserir";
    Categoria selectedCategoria;
    CategoriaDAO dao;

    FuncActivity activity;
    View rootView;

    public CategoriasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (FuncActivity)getActivity();
        dao = activity.categoriaDao;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_categorias, container, false);

        // START usar find view by id pra cada componente
        criarComponentes();
        atualizarListaDeCategorias();

        return rootView;
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            activity = (FuncActivity) getActivity();
            atualizarListaDeCategorias();
        }
    }

    private void criarComponentes() {
        edtCodigo = rootView.findViewById(R.id.editCodigoCategoria);
        edtNome = rootView.findViewById(R.id.editNomeCategoria);
        btnGravar = rootView.findViewById(R.id.btnGravarCategoria);
        btnGravar.setOnClickListener(this);
        btnExcluir = rootView.findViewById(R.id.btnExcluirCategoria);
        btnExcluir.setOnClickListener(this);
        listaCategorias = rootView.findViewById(R.id.listaCategorias);
        listaCategorias.setOnItemClickListener(this);

        btnExcluir.setVisibility(acao.equals("Inserir") ? rootView.INVISIBLE : rootView.VISIBLE);
        // END usar find view by id pra cada componente
    }

    private void atualizarListaDeCategorias() {
        activity.listaCategoria = dao.lista();
        listAdapter = new ArrayAdapter<Categoria>(activity,android.R.layout.simple_list_item_1, activity.listaCategoria);
        listaCategorias.setAdapter(listAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == btnGravar) {
            if(acao.equals("Inserir")){
                Categoria novaCategoria = new Categoria();
                novaCategoria.setPro_cat_nome(edtNome.getText().toString());
                Long id = dao.inserir(novaCategoria);
                String text = String.format(getString(R.string.categoria_inserido),novaCategoria.getPro_cat_nome(),id.toString());
                Toast.makeText(activity,text,Toast.LENGTH_LONG).show();
                edtCodigo.setText("");
                edtNome.setText("");
            } else {
                selectedCategoria.setPro_cat_nome(edtNome.getText().toString());
                long id = dao.alterar(selectedCategoria);
                String text = String.format(getString(R.string.categoria_alterado),selectedCategoria.getPro_cat_nome());
                Toast.makeText(activity,text,Toast.LENGTH_LONG).show();
                acao = "Inserir";
                btnGravar.setText("GRAVA");
                edtCodigo.setText("");
                edtNome.setText("");
            }
        }
        else if (v == btnExcluir) {
            long id = dao.excluir(selectedCategoria);
            String text = String.format(getString(R.string.categoria_excluido),selectedCategoria.getPro_cat_nome());
            Toast.makeText(activity,text,Toast.LENGTH_LONG).show();
            acao = "Inserir";
            btnGravar.setText("GRAVA");
            edtCodigo.setText("");
            edtNome.setText("");
        }
        atualizarListaDeCategorias();
        btnExcluir.setVisibility(rootView.INVISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (posicaoSelecionada == position) {
            acao = "Inserir";
            btnGravar.setText("GRAVA");

            listaCategorias.clearChoices();
            listaCategorias.setAdapter(listAdapter);
            listaCategorias.requestLayout();

            edtCodigo.setText("");
            edtNome.setText("");
            btnExcluir.setVisibility(rootView.INVISIBLE);
            selectedCategoria = activity.listaCategoria.get(position);

            posicaoSelecionada = -1;
        } else {
            acao = "Editar";
            btnGravar.setText("ATUALIZA");

            edtCodigo.setText(activity.listaCategoria.get(position).getPro_cat_id().toString());
            edtNome.setText(activity.listaCategoria.get(position).getPro_cat_nome());
            btnExcluir.setVisibility(rootView.VISIBLE);
            selectedCategoria = activity.listaCategoria.get(position);

            posicaoSelecionada = position;
        }
    }
}