package com.example.alphastore.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.alphastore.LojaActivity;
import com.example.alphastore.R;
import com.example.alphastore.model.Produto;

import java.util.ArrayList;

public class ProdutoAdapter extends ArrayAdapter<Produto> {

    private Context mContext;
    private ArrayList<Produto> produtoList = new ArrayList<>();

    public ProdutoAdapter(@NonNull Context context, /*@LayoutRes*/ ArrayList<Produto> list) {
        super(context, 0 , list);
        mContext = context;
        produtoList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.activity_item_view,parent,false);

        Produto currentProduto = produtoList.get(position);

        Button addToCart = (Button) listItem.findViewById(R.id.btnAddCart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LojaActivity activity = (LojaActivity) parent.getContext();
                activity.addToCart(currentProduto);
            }
        });

        TextView textViewNome = (TextView)listItem.findViewById(R.id.txtLinha1);
        textViewNome.setText(currentProduto.getNome());

        TextView textViewPreco = (TextView) listItem.findViewById(R.id.txtLinha2);
        textViewPreco.setText(String.valueOf(currentProduto.getPreco()));

        return listItem;
    }
}