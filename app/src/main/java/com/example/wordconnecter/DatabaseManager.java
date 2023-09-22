package com.example.wordconnecter;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;//Adatbázis segédosztály példány, ezt használja az adatbázis műveletek végrehajtásához.
    private SQLiteDatabase db;// Adatbázis példány


    public DatabaseManager(Context context){
        dbHelper = new DatabaseHelper(context);//Adatbázis segédosztály inicalizása
    }
    public void open() throws SQLDataException {
        db = dbHelper.getWritableDatabase();// Az adatbázis írásra való megnyitása
    }

    public void close(){
        dbHelper.close(); // Adatbázis segédosztály bezárása
    }

    public void insert(String hunWord, String enWord){
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_HUN,hunWord);
        values.put(dbHelper.COLUMN_EN, enWord);
        db.insert(dbHelper.TABLE_WORDS, null,values);
    }


    //Adatbázis megnyitása és inicializálása
    //dbHelper = new DatabaseHelper(this); //a this a Contex példány
   // db = dbHelper.getWritableDatabase();//Ezzel leszünk képesek az adatbázis írásra való megnyitásra

}
