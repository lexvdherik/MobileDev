package com.example.lex_h.mobileapplicationdevelopment.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.lex_h.mobileapplicationdevelopment.helpers.WinkelWagenHelper;
import com.example.lex_h.mobileapplicationdevelopment.adapters.WinkelWagenProductAdapter;

public class WinkelWagenActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new WinkelWagenProductAdapter(this,WinkelWagenHelper.getCart()) );
        
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {


    }
}
