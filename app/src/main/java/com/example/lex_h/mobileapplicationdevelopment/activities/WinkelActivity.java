package com.example.lex_h.mobileapplicationdevelopment.activities;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.lex_h.mobileapplicationdevelopment.DB.DatabaseHelper;
import com.example.lex_h.mobileapplicationdevelopment.R;
import com.example.lex_h.mobileapplicationdevelopment.adapters.ProductObjectAdapter;
import com.example.lex_h.mobileapplicationdevelopment.models.Product;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class WinkelActivity extends AppCompatActivity {

    static DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winkel);

        this.deleteDatabase("winkel_db");

        db = new DatabaseHelper(this);


        List<Product> products = new ArrayList<>();

        for (int i = 0; i < Product.PRE_DEFINED_PRODUCT_NAMES.length; i++) {
            Product product = new Product(i,
                    Product.PRE_DEFINED_PRODUCT_NAMES[i],
                    Product.PRE_DEFINED_PRODUCT_IMAGE_IDS[i],
                    getBitmapAsByteArray(BitmapFactory.decodeResource(getResources(), Product.PRE_DEFINED_PRODUCT_IMAGE_IDS[i])) );
            db.insertData(product);

        }


        Cursor cur = db.getAllData();
        Product product = new Product();
       while(cur.moveToNext()) {
           product.setId(cur.getInt(0));
           product.setNaam(cur.getString(1));
           product.setFotoId(cur.getInt(2));
           product.setFoto(cur.getBlob(3));
           products.add(product);
           product = new Product();
       }



        RecyclerView productRecyclerView = findViewById(R.id.productRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        productRecyclerView.setLayoutManager(mLayoutManager);
        ProductObjectAdapter mAdapter = new ProductObjectAdapter(products);
        productRecyclerView.setAdapter(mAdapter);

    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
