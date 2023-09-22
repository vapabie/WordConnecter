package com.example.wordconnecter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DatabaseHelper dbHelper;//Adatbázis segédosztály példány
    private SQLiteDatabase db;// Adatbázis példány

    public DatabaseManager(Context context){
        dbHelper = new DatabaseHelper(context);//Adatbázis segédosztály inicalizása
    }
    public void open(){
        db = dbHelper.getWritableDatabase();// Az adatbázis írásra való megnyitása
    }

    public void close(){
        dbHelper.close(); // Adatbázis segédosztály bezárása
    }




    //Adatbázis megnyitása és inicializálása
    //dbHelper = new DatabaseHelper(this); //a this a Contex példány
   // db = dbHelper.getWritableDatabase();//Ezzel leszünk képesek az adatbázis írásra való megnyitásra

}
