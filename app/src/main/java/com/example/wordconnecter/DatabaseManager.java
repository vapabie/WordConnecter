package com.example.wordconnecter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;//Adatbázis segédosztály példány, ezt használja az adatbázis műveletek végrehajtásához.
    private SQLiteDatabase db;// Adatbázis példány
    private Context context;


    public DatabaseManager(Context ctx){
        context = ctx;
    }

    public DatabaseManager open() throws SQLDataException {
        dbHelper = new DatabaseHelper(context);//we gave context
        db = dbHelper.getWritableDatabase();//initialiazed the database instance by accessing the database->Az adatbázis írásra való megnyitása
        return this;
    }

    public void close(){
        dbHelper.close(); // Adatbázis segédosztály bezárása
    }

    public void insert(String hunWord, String enWord){
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_HUN,hunWord);
        values.put(dbHelper.COLUMN_EN, enWord);
        long result =db.insert(dbHelper.TABLE_WORDS, null,values);
        if (result != -1) {
            // Az adat beszúrása sikerült
            Log.d("DatabaseManager", "Adat beszúrása sikerült");
        } else {
            // Az adat beszúrása sikertelen
            Log.e("DatabaseManager", "Adat beszúrása sikertelen");
        }
    }

}