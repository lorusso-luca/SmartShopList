package com.lorusso.luca.smartshoplist;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Luca on 20/03/2018.
 */

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyViewHolder> {

    private ArrayList<Product> productList;
    private Context context;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);

            cardView = view.findViewById(R.id.cardView);
            name = view.findViewById(R.id.nameProduct);
        }

    }

    public AdapterProduct(Context context, ArrayList<Product> products) {
        this.productList = products;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.name.setText(p.getName());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

