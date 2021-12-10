package com.example.alphastore.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.alphastore.R;
import com.example.alphastore.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoAdapter extends ArrayAdapter<Pedido> {

    private Context mContext;
    private List<Pedido> pedidosList = new ArrayList<>();

    public PedidoAdapter(@NonNull Context context, /*@LayoutRes*/ ArrayList<Pedido> list) {
        super(context, 0 , list);
        mContext = context;
        pedidosList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.pedido_cell,parent,false);

        Pedido currentPedido = pedidosList.get(position);

        TextView textViewNome = (TextView)listItem.findViewById(R.id.txtNomeCliente);
        textViewNome.setText(currentPedido.getCliente().getCli_nome());

        TextView textViewId = (TextView) listItem.findViewById(R.id.txtIdPedido);
        textViewId.setText(String.valueOf(currentPedido.getId()));

        TextView textViewData = (TextView) listItem.findViewById(R.id.txtDataPedido);
        textViewData.setText(currentPedido.getDate().toString());

        return listItem;
    }
}