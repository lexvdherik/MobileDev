package com.example.lex_h.mobileapplicationdevelopment.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.lex_h.mobileapplicationdevelopment.models.Product;

@Database(entities = {Product.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDAO productDao();

    private final static String NAME_DATABASE = "winkel_db";

    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {

        if(sInstance == null) {
            sInstance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE).allowMainThreadQueries().build();
        }

        return sInstance;
    }

}
