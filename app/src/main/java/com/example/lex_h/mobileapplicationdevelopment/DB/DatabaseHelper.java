package com.example.lex_h.mobileapplicationdevelopment.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lex_h.mobileapplicationdevelopment.models.Product;

import static com.example.lex_h.mobileapplicationdevelopment.DB.DatabaseContract.*;

/**
 * Helper class that actually creates and manages
 * the provider's underlying data repository.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public SQLiteDatabase db;

    public DatabaseHelper(Context context ){
        super(context, DATABASE_NAME, null, 1);

        db = this.getWritableDatabase();
        onUpgrade(db,1,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createProductTable = "create TABLE " + PRODUCT_TABLE
                + "("+ COL_PRODUCT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ""+ COL_PRODUCT_NAME +" TEXT,"
                + ""+ COL_PRODUCT_FOTO_ID + " INTEGER,"
                + ""+ COL_PRODUCT_FOTO +" BLOB);";


        db.execSQL(createProductTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  PRODUCT_TABLE);
        onCreate(db);
    }

    public boolean insertData(Product product){
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_PRODUCT_ID, product.getId());
        contentValues.put(COL_PRODUCT_NAME, product.getNaam());
        contentValues.put(COL_PRODUCT_FOTO_ID, product.getFotoId());
        contentValues.put(COL_PRODUCT_FOTO, product.getFoto());
        long result = db.insert(PRODUCT_TABLE, null, contentValues);
        if (result == -1)
            return false;
        else{
            return true;
        }
    }

    public Cursor getAllData(){
        Cursor cursor = db.rawQuery("select * from " +PRODUCT_TABLE, null);
        return cursor;
    }
}