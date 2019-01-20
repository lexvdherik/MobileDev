package com.example.lex_h.mobileapplicationdevelopment.viewHolders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lex_h.mobileapplicationdevelopment.R;
import com.example.lex_h.mobileapplicationdevelopment.activities.ProductActivity;
import com.example.lex_h.mobileapplicationdevelopment.models.Product;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    public TextView productNaam;
    public ImageView productFoto;
    public View view;

    public ProductViewHolder(View itemView) {
        super(itemView);
        productNaam = itemView.findViewById(R.id.productNaamTextView);
        productFoto = itemView.findViewById(R.id.productFotoImageView);
        view = itemView;
    }
}
