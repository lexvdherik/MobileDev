package com.example.lex_h.mobileapplicationdevelopment.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

import com.example.lex_h.mobileapplicationdevelopment.R;

import java.io.Serializable;
import java.sql.Blob;

@Entity(tableName = "product")
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "naam")
    String naam;
    @ColumnInfo(name = "fotoId")
    int fotoId;

    @ColumnInfo(name = "foto")
    byte[] foto;



    public Product(String naam,int fotoId, byte[] foto) {
        this.naam = naam;
        this.fotoId = fotoId;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getFotoId() {
        return fotoId;
    }

    public void setFotoIdm(int fotoId) {
        this.fotoId = fotoId;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public static final String[] PRE_DEFINED_PRODUCT_NAMES = {
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

    public static final int[] PRE_DEFINED_PRODUCT_IMAGE_IDS = {

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

}
