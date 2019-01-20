package com.example.lex_h.mobileapplicationdevelopment.activities;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.lex_h.mobileapplicationdevelopment.DB.AppDatabase;
import com.example.lex_h.mobileapplicationdevelopment.DB.ProductProvider;
import com.example.lex_h.mobileapplicationdevelopment.R;
import com.example.lex_h.mobileapplicationdevelopment.adapters.ProductObjectAdapter;
import com.example.lex_h.mobileapplicationdevelopment.models.Product;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import static com.example.lex_h.mobileapplicationdevelopment.DB.DatabaseContract.*;

public class WinkelActivity extends AppCompatActivity {

    static AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winkel);

//        this.deleteDatabase("winkel_db");
//        db = AppDatabase.getInstance(this);
//        ProductProvider productProvider = new ProductProvider();
//        productProvider.onCreate();

        ContentValues cv = new ContentValues();
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < Product.PRE_DEFINED_PRODUCT_NAMES.length; i++) {
            Product product = new Product(
                    Product.PRE_DEFINED_PRODUCT_NAMES[i],
                    Product.PRE_DEFINED_PRODUCT_IMAGE_IDS[i],
                    getBitmapAsByteArray(BitmapFactory.decodeResource(getResources(), Product.PRE_DEFINED_PRODUCT_IMAGE_IDS[i])) );
            products.add(product);
            cv.put(COL_PRODUCT_NAME,product.getNaam());
            cv.put(COL_PRODUCT_FOTO_ID,product.getFotoId());
            cv.put(COL_PRODUCT_FOTO,product.getFoto());
            Uri uri = getContentResolver().insert(ProductProvider.CONTENT_URI, cv);
        }

        String URL = "content://com.example.lex_h.mobileapplicationdevelopment.DB.ProductProvider";

        Uri producten = Uri.parse(URL);
        Cursor c = getContentResolver().query(producten, null, null, null, "naam");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(COL_PRODUCT_ID)) +
                                ", " +  c.getString(c.getColumnIndex( COL_PRODUCT_NAME)) +
                                ", " + c.getInt(c.getColumnIndex( COL_PRODUCT_FOTO_ID))+
                                ", " + c.getBlob(c.getColumnIndex( COL_PRODUCT_FOTO)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }



//        for (Product p : productProvider.GetAll()){
//            System.out.println(p.getId());
//            System.out.println(p.getNaam());
//            System.out.println(p.getFoto());
//        }

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
