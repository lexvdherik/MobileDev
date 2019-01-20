package com.example.lex_h.mobileapplicationdevelopment.DB;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import android.text.TextUtils;

import com.example.lex_h.mobileapplicationdevelopment.models.Product;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.lex_h.mobileapplicationdevelopment.DB.DatabaseContract.*;

public class ProductProvider extends ContentProvider{

    static final String PROVIDER_NAME = "com.example.lex_h.mobileapplicationdevelopment.DB.ProductProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/products";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    private static HashMap<String, String> PRODUCTS_PROJECTION_MAP;

    public static final int PRODUCTS = 1;
    public static final int PRODUCT_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "products", PRODUCTS);
        uriMatcher.addURI(PROVIDER_NAME, "products/#", PRODUCT_ID);
    }

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * Add a new product record
         */
        long rowID = db.insert(	DATABASE_NAME, "", values);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection,String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(PRODUCT_TABLE);

        switch (uriMatcher.match(uri)) {
            case PRODUCTS:
                qb.setProjectionMap(PRODUCTS_PROJECTION_MAP);
                break;

            case PRODUCT_ID:
                qb.appendWhere( PRODUCT_ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = COL_PRODUCT_NAME;
        }

        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case PRODUCTS:
                count = db.delete(PRODUCT_TABLE, selection, selectionArgs);
                break;

            case PRODUCT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete( PRODUCT_TABLE, COL_PRODUCT_ID +  " = " + id +
                                (!TextUtils.isEmpty(selection) ?
                                        " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case PRODUCTS:
                count = db.update(PRODUCT_TABLE, values, selection, selectionArgs);
                break;

            case PRODUCT_ID:
                count = db.update(PRODUCT_TABLE, values,
                        COL_PRODUCT_ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ?
                                        " AND (" +selection + ')' : ""), selectionArgs);

                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all product records
             */
            case PRODUCTS:
                return "vnd.android.cursor.dir/vnd.example.products";
            /**
             * Get a particular student
             */
            case PRODUCT_ID:
                return "vnd.android.cursor.item/vnd.example.products";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


    public ArrayList<Product> GetAll() {
        ArrayList<Product> products = new ArrayList<>();
        Cursor res = db.rawQuery("select * from " + PRODUCT_TABLE + ";",null);

        while(res.moveToNext()){
            Product product = new Product(res.getString(2),res.getInt(3),res.getBlob(4));
            product.setId(res.getInt(1));
            products.add(product);
        }
        return products;
    }


}

