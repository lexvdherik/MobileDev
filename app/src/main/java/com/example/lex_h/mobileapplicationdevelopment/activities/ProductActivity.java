package com.example.lex_h.mobileapplicationdevelopment.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lex_h.mobileapplicationdevelopment.R;
import com.example.lex_h.mobileapplicationdevelopment.helpers.WinkelWagenHelper;
import com.example.lex_h.mobileapplicationdevelopment.models.Product;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ImageView productImageView = findViewById(R.id.productActivityProductFotoImageView);
        TextView productTextView = findViewById(R.id.productActivityProductNaamTextView);
        Button productButton = findViewById(R.id.productActivityButton);
        Button productWinkelWagenButton = findViewById(R.id.productActivityWinkelWagenButton);

        final Product product =(Product)getIntent().getSerializableExtra("product");

        Bitmap bmp = BitmapFactory.decodeByteArray(product.getFoto(), 0, product.getFoto().length);
        productImageView.setImageBitmap(bmp);
        productTextView.setText(product.getNaam());


        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Toegevoegd aan winkelwagen", Toast.LENGTH_LONG).show();
                EditText editText = findViewById(R.id.editText2);
                WinkelWagenHelper.addCart(product, Integer.parseInt(editText.getText().toString()));
            }
        });

        productWinkelWagenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WinkelWagenActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
