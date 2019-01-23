package com.example.lex_h.mobileapplicationdevelopment.helpers;

import android.content.res.Resources;

import com.example.lex_h.mobileapplicationdevelopment.models.Product;

import java.util.ArrayList;
import java.util.List;


public class WinkelWagenHelper {

    private static List<Product> winkelWagen = new ArrayList<>();
    private static List<Integer> aantallen = new ArrayList<>();

    public static List<Product> getCart() {
        return winkelWagen;
    }
    public static void addCart(Product product, int amount) {
        Boolean found = false;
        if(winkelWagen == null) {
            winkelWagen = new ArrayList<>();
            aantallen = new ArrayList<>();
            winkelWagen.add(product);
            aantallen.add(amount);
        }else {
            for (int i = 0; i < winkelWagen.size(); i++) {
                if (winkelWagen.get(i).getId() == product.getId()) {
                    aantallen.set(i, (aantallen.get(i) + amount));
                    found = true;
                    break;
                }
            }
            if (!found){
                winkelWagen.add(product);
                aantallen.add(amount);
            }
        }
    }

    public static List<Integer> getAantallen(){
        return aantallen;
    }
}
