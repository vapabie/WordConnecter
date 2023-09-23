package com.example.wordconnecter;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME="WordConnector.db";//adatbázis neve
    static final int DATABASE_VERSION=1;//adatbázis verziója
    static final String TABLE_WORDS="words";//adatbázis TÁBLA neve
    static final String COLUMN_ID="id";//Az id/azonosító oszlop
    static final String COLUMN_HUN="hun";//magyar szavak oszlopa
    static final String COLUMN_EN="en";//angol szavak oszlopa


    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_WORDS + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_HUN + " TEXT, " + COLUMN_EN + " TEXT);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);//konstruktor inicializálásra
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);//Ezzel az SQL paranccsal hozza létre az ADATBÁZIST
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);//ha létezne már egy tábla, azt eldobja
        onCreate(db);//meghívja az onCreate-et és létrehozza a táblát
    }
}