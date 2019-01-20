package com.example.lex_h.mobileapplicationdevelopment.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lex_h.mobileapplicationdevelopment.R;
import com.example.lex_h.mobileapplicationdevelopment.activities.ProductActivity;
import com.example.lex_h.mobileapplicationdevelopment.models.Product;
import com.example.lex_h.mobileapplicationdevelopment.viewHolders.ProductViewHolder;

import java.util.List;

public class ProductObjectAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    public List<Product> productList;

    public ProductObjectAdapter(List<Product> productList ){
        this.productList = productList;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_cell,
                viewGroup, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        final Product product = productList.get(i);

        Bitmap bmp = BitmapFactory.decodeByteArray(product.getFoto(), 0, product.getFoto().length);

        productViewHolder.productFoto.setImageBitmap(bmp);
        productViewHolder.productNaam.setText(product.getNaam());

        productViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ProductActivity.class);
                intent.putExtra("product", product);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
