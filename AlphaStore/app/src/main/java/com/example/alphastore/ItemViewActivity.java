package com.example.alphastore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alphastore.model.Produto;

import java.util.List;

public class ItemViewActivity extends BaseAdapter{

    private LayoutInflater mInflater;
    private List lista;

    private TextView txtLinha1;
    private TextView txtLinha2;

    public ItemViewActivity(Context context, List lista) {
        this.lista = lista;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((Produto) lista.get(position)).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Object obj = lista.get(position);
        view = mInflater.inflate(R.layout.activity_item_view, null);

        txtLinha1 = (TextView) view.findViewById(R.id.txtLinha1);
        txtLinha2 = (TextView) view.findViewById(R.id.txtLinha2);

        if (obj instanceof Produto) {
            txtLinha1.setText(((Produto) obj).getNome());
            txtLinha2.setText(((Produto) obj).getPreco().toString());
        }
        return view;
    }
}