package com.example.lex_h.mobileapplicationdevelopment.DB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lex_h.mobileapplicationdevelopment.models.Product;

import java.util.List;

@Dao
public interface ProductDAO {

        @Query("SELECT * FROM Product")
        public List<Product> getAllProducts();

        @Insert
        public void insertProduct(Product product);

        @Delete
        public void deleteProduct(Product product);

        @Update
        public void updateProdect(Product product);
}
