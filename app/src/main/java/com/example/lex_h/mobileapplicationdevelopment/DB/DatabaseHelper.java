package com.example.lex_h.mobileapplicationdevelopment.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.lex_h.mobileapplicationdevelopment.R;
import com.example.lex_h.mobileapplicationdevelopment.models.Product;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.lex_h.mobileapplicationdevelopment.DB.DatabaseContract.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    public SQLiteDatabase db;

    public DatabaseHelper(Context context ){
        super(context, DATABASE_NAME, null, 1);

        db = this.getWritableDatabase();
        addDummyData(context);
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

    public List<Product> getAllData(){

        Cursor cursor = db.rawQuery("select * from " +PRODUCT_TABLE, null);
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        while(cursor.moveToNext()) {
            product.setId(cursor.getInt(0));
            product.setNaam(cursor.getString(1));
            product.setFotoId(cursor.getInt(2));
            product.setFoto(cursor.getBlob(3));
            products.add(product);
            product = new Product();
        }

        return products;
    }

    private void addDummyData(Context context){
        onUpgrade(db,1,1);

        final String[] PRE_DEFINED_PRODUCT_NAMES = {
                "GTA5",
                "Google Chrome Cast",
                "Iphone oortjes",
                "Kast",
                "Koelkast",
                "Koffie apparaat",
                "Laden kast",
                "Product X",
                "Speelgoed",
                "Strijkijzer",
                "Tafel",
                "Walter Wallet"
        };

        final int[] PRE_DEFINED_PRODUCT_IMAGE_IDS = {

                R.drawable.gta5,
                R.drawable.google_chrome_cast,
                R.drawable.iphone_oortjes,
                R.drawable.kast,
                R.drawable.koelkast,
                R.drawable.koffie_apparaat,
                R.drawable.laden_kast,
                R.drawable.product_naam,
                R.drawable.speelgoed,
                R.drawable.strijkijzer,
                R.drawable.tafel,
                R.drawable.walter_wallet

        };

        for (int i = 0; i < PRE_DEFINED_PRODUCT_NAMES.length; i++) {
            Product product = new Product(i,
                    PRE_DEFINED_PRODUCT_NAMES[i],
                    PRE_DEFINED_PRODUCT_IMAGE_IDS[i],
                    getBitmapAsByteArray(BitmapFactory.decodeResource(context.getResources(), PRE_DEFINED_PRODUCT_IMAGE_IDS[i])) );
            insertData(product);

        }


    }

    private byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}