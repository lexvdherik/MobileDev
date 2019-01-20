package com.example.lex_h.mobileapplicationdevelopment.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

import com.example.lex_h.mobileapplicationdevelopment.R;

import java.io.Serializable;
import java.sql.Blob;


public class Product implements Serializable {

    int id;
    String naam;
    int fotoId;
    byte[] foto;


    public Product(){

    }
    public Product(int id, String naam,int fotoId, byte[] foto) {
        this.id = id;
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

    public void setFotoId(int fotoId) {
        this.fotoId = fotoId;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
