package com.example.lex_h.mobileapplicationdevelopment.helpers;

import android.content.res.Resources;

import com.example.lex_h.mobileapplicationdevelopment.models.Product;

import java.util.ArrayList;
import java.util.List;


public class WinkelWagenHelper {

    private static List<Product> winkelWagen;
    private static List<Integer> aantallen;

    public static List<Product> getCart() {
        if(winkelWagen == null) {
            winkelWagen = new ArrayList<>();
        }

        return winkelWagen;
    }
    public static void addCart(Product product) {
        Boolean found = false;
        if(winkelWagen == null) {
            winkelWagen = new ArrayList<>();
            aantallen = new ArrayList<>();
            winkelWagen.add(product);
            aantallen.add(1);
        }else {
            for (int i = 0; i < winkelWagen.size(); i++) {
                if (winkelWagen.get(i).getId() == product.getId()) {
                    aantallen.set(i, (aantallen.get(i) + 1));
                    found = true;
                    break;
                }
            }
            if (!found){
                winkelWagen.add(product);
                aantallen.add(1);
            }
        }
    }

    public static List<Integer> getAantallen(){
        if(aantallen == null) {
            aantallen = new ArrayList<>();
        }

        return aantallen;
    }
}
