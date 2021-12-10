package com.example.alphastore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.alphastore.FuncActivity;
import com.example.alphastore.R;
import com.example.alphastore.dao.CategoriaDAO;
import com.example.alphastore.dao.ProdutoDAO;
import com.example.alphastore.model.Categoria;
import com.example.alphastore.model.Produto;

import java.util.List;

public class ProdutosFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {


    Button btnGravarEditarProduto;


    Button btnExcluirProduto;

    Spinner spnFiltroCateg;
    EditText editNomeProduto;
    EditText editPrecoVendaProduto;

    int posicaoSelecionada = -1;

    ProdutoDAO produtoDAO;
    Produto selectedProduto;

    ListView listaProduto;
    ListAdapter listAdapter;

    String acao = "Inserir";
    CategoriaDAO categoriaDao;
    List<Categoria> listaCateg;
    BaseAdapter baseAdapter;

    FuncActivity activity;
    View rootView;

    public ProdutosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (FuncActivity) getActivity();

        produtoDAO = activity.produtoDAO;
        categoriaDao = activity.categoriaDao;
        listaCateg = activity.listaCategoria;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_produtos, container, false);

        criarComponentes();
        atualizarListaDeProdutos();

        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            activity = (FuncActivity) getActivity();
            categoriaDao = activity.categoriaDao;
            listaCateg = activity.listaCategoria;
            baseAdapter = new ArrayAdapter<Categoria>(getActivity(), android.R.layout.simple_list_item_activated_1, listaCateg);
            spnFiltroCateg.setAdapter(baseAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (v == btnGravarEditarProduto) {
            if(acao.equals("Inserir")){
                Produto novoProduto = new Produto();
                novoProduto.setNome(editNomeProduto.getText().toString());
                novoProduto.setPreco(Float.valueOf(String.valueOf(editPrecoVendaProduto.getText())));
                long id = produtoDAO.inserir(novoProduto);
                String text = String.format(getString(R.string.produto_inserido),novoProduto.getNome(),String.valueOf(id));
                Toast.makeText(activity,text,Toast.LENGTH_LONG).show();
            } else {
                selectedProduto.setNome(editNomeProduto.getText().toString());
                selectedProduto.setPreco(Float.valueOf(String.valueOf(editPrecoVendaProduto.getText())));
                long id = produtoDAO.alterar(selectedProduto);
                String text = String.format(getString(R.string.produto_alterado),selectedProduto.getNome());
                Toast.makeText(activity,text,Toast.LENGTH_LONG).show();
                acao = "Inserir";
                btnGravarEditarProduto.setText("GRAVA");
            }
        }
        else if (v == btnExcluirProduto) {
            long id = produtoDAO.excluir(selectedProduto);
            String text = String.format(getString(R.string.produto_excluido),selectedProduto.getNome());
            Toast.makeText(activity,text,Toast.LENGTH_LONG).show();
            acao = "Inserir";
            btnGravarEditarProduto.setText("GRAVA");
            editPrecoVendaProduto.setText("");
            editNomeProduto.setText("");
        }
        atualizarListaDeProdutos();
        btnExcluirProduto.setVisibility(rootView.INVISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (posicaoSelecionada == position) {
            acao = "Inserir";
            btnGravarEditarProduto.setText("GRAVAR");

            listaProduto.clearChoices();
            listaProduto.setAdapter(listAdapter);
            listaProduto.requestLayout();

            editNomeProduto.setText("");
            editPrecoVendaProduto.setText("");
            btnExcluirProduto.setVisibility(rootView.INVISIBLE);
            selectedProduto = activity.listaProduto.get(position);

            posicaoSelecionada = -1;
        } else {
            acao = "Editar";
            selectedProduto = activity.listaProduto.get(position);

            btnGravarEditarProduto.setText("ATUALIZAR");

            editNomeProduto.setText(activity.listaProduto.get(position).getNome());
            editPrecoVendaProduto.setText(activity.listaProduto.get(position).getPreco().toString());
            btnExcluirProduto.setVisibility(rootView.VISIBLE);

            posicaoSelecionada = position;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void criarComponentes() {
        btnGravarEditarProduto = rootView.findViewById(R.id.btnGravarEditarProduto);
        btnGravarEditarProduto.setOnClickListener(this);
        btnGravarEditarProduto.setText("GRAVAR");

        btnExcluirProduto = rootView.findViewById(R.id.btnExcluirProduto);
        btnExcluirProduto.setOnClickListener(this);
        btnExcluirProduto.setVisibility(rootView.INVISIBLE);


        spnFiltroCateg = rootView.findViewById(R.id.spnCategProduto);
        spnFiltroCateg.setOnItemSelectedListener(this);
        baseAdapter = new ArrayAdapter<Categoria>(getActivity(), android.R.layout.simple_list_item_1, listaCateg);
        spnFiltroCateg.setAdapter(baseAdapter);

        editNomeProduto = rootView.findViewById(R.id.editNomeProduto);
        editPrecoVendaProduto = rootView.findViewById(R.id.editPrecoVendaProduto);

        listaProduto = rootView.findViewById(R.id.listaProdutos);
        listaProduto.setOnItemClickListener(this);
    }

    private void atualizarListaDeProdutos() {
        activity.listaProduto = produtoDAO.lista();
        listAdapter = new ArrayAdapter<Produto>(activity,android.R.layout.simple_list_item_1, activity.listaProduto);
        listaProduto.setAdapter(listAdapter);
    }

}