package com.example.lex_h.mobileapplicationdevelopment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lex_h.mobileapplicationdevelopment.R;
import com.example.lex_h.mobileapplicationdevelopment.helpers.WinkelWagenHelper;
import com.example.lex_h.mobileapplicationdevelopment.models.Product;

import java.util.List;

public class WinkelWagenProductAdapter extends ArrayAdapter<Product> {
    private final Context context;
    private final  List<Product> productList;

    public WinkelWagenProductAdapter(Context context,  List<Product> productList) {
        super(context, R.layout.activity_winkel_wagen, productList);
        this.context = context;
        this.productList = productList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder = new ViewHolder();
        if(convertView == null ) {
            convertView = inflater.inflate(R.layout.activity_winkel_wagen, parent, false);

            holder.naam = convertView.findViewById(R.id.winkelWagenProductNaam);
            holder.aantal = convertView.findViewById(R.id.winkelWagenPoductHoeveelheid);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.naam.setText(WinkelWagenHelper.getCart().get(position).getNaam());
        holder.aantal.setText(WinkelWagenHelper.getAantallen().get(position).toString());

        return convertView;
    }
    private class ViewHolder {
        TextView naam;
        TextView aantal;
    }
}
