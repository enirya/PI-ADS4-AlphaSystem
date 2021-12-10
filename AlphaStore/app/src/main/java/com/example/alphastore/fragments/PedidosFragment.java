package com.example.alphastore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.alphastore.Adapters.PedidoAdapter;
import com.example.alphastore.R;
import com.example.alphastore.model.Cliente;
import com.example.alphastore.model.Pedido;
import com.example.alphastore.model.Produto;

import java.util.ArrayList;
import java.util.Calendar;

public class PedidosFragment extends Fragment implements View.OnClickListener{

    Button btnVerPedido;
    ListView listview;

    public PedidosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pedidos, container, false);

        // START MOCK
        ArrayList<Pedido> pedidosList = new ArrayList<>();

        Cliente clienteTay = new Cliente("Tayrine", 3188548133L, 99017602L, "tuts@gmail.com", "123");
        Calendar calendar = Calendar.getInstance();
        ArrayList<Produto> produtosList = new ArrayList<>();

        pedidosList.add(new Pedido(1, calendar.getTime(), 100L, "Que gay", clienteTay, produtosList));
        pedidosList.add(new Pedido(2, calendar.getTime(), 200L, "Que gay 2", clienteTay, produtosList));
        pedidosList.add(new Pedido(3, calendar.getTime(), 300L, "Que gay 3", clienteTay, produtosList));
        pedidosList.add(new Pedido(4, calendar.getTime(), 400L, "Que gay 4", clienteTay, produtosList));
        // END MOCK

        //Build adapter
        ArrayAdapter<Pedido> pedidosAdapter = new PedidoAdapter(this.getActivity(), pedidosList);

        ListView listView = (ListView) rootView.findViewById(R.id.listViewPedidos);
        listView.setAdapter(pedidosAdapter);

        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}