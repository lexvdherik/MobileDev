package com.example.lex_h.mobileapplicationdevelopment.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.lex_h.mobileapplicationdevelopment.DB.DatabaseHelper;
import com.example.lex_h.mobileapplicationdevelopment.R;
import com.example.lex_h.mobileapplicationdevelopment.adapters.ProductObjectAdapter;

public class WinkelActivity extends AppCompatActivity {

    static DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winkel);
        db = new DatabaseHelper(this);

        RecyclerView productRecyclerView = findViewById(R.id.productRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        productRecyclerView.setLayoutManager(mLayoutManager);
        ProductObjectAdapter mAdapter = new ProductObjectAdapter(db.getAllData());
        productRecyclerView.setAdapter(mAdapter);

    }

}
