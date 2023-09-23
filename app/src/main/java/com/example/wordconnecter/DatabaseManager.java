package com.example.wordconnecter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Pair;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    //getReadableDatabase():Create and/or open a database.
    //Cursor:This interface provides random read-write access to the result set returned by a database query.->need query
    public List<WordPair> shuffleWordPairs(int count){
        List<WordPair> wordPairs = new ArrayList<>();//ez a lista fogja tárolni a szópárokat, legvégére kell
        List<String> hungarianWords = new ArrayList<>();
        List<String> englishWords = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();//Ezzel nyitjuk meg az adatbázist olvasásra
        Cursor cursor = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_WORDS + " ORDER BY RANDOM() LIMIT " + count, null);//itt történik a lekérdezés, szerintem érthető, hisz majdnem ugyanaz mint egy adatbázis lekérdezés


        if(cursor!=null && cursor.moveToFirst()){//(a rawQuery metódus által visszaadott Cursor objektum valóban létezik && a kurzor tud-e mozogni az eredményhalmazon belül, és ha igen, akkor az első rekordra (sor) helyezzük a kurzort)
        do{
            String hunWord = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_HUN));//a magyar szavakat nyerjük ki az adatb.ből
            String enWord = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_EN));//az angol szavakat nyerjük ki az adatb.ből

            Log.d("RandomWord", "HUN: " + hunWord + ", EN: " + enWord);//ellenőrzésre

            hungarianWords.add(hunWord);
            englishWords.add(enWord);
        }while(cursor.moveToNext());//következő sorba lépés az adatbázisban
        cursor.close();
    }

        // Most cseréljük meg a szópárokat: Random()-mal kiválasztok egy számot, átrakom azt az indexest a fő arraybe és eredetiből kiveszem. addig csinálom még az erediteben nem marad semmi
        for (int i = 0; i < count; i++) {
            int randomIndex = new Random().nextInt(hungarianWords.size());
            int randomIndex2 = new Random().nextInt(englishWords.size());
            String shuffledHunWord = hungarianWords.remove(randomIndex);
            String shuffledEnWord = englishWords.remove(randomIndex2);

            WordPair wordPair = new WordPair(shuffledHunWord, shuffledEnWord);
            wordPairs.add(wordPair);
        }


        Log.d("RandomWord", "Szópárok a keverés után:");//csak ellenőrzésre van
        for (WordPair pair : wordPairs) {
            Log.d("RandomWord", "HUN: " + pair.getHunWord() + ", EN: " + pair.getEnWord());
        }

        // Az adatbázist bezárjuk
        db.close();

        return wordPairs;
    }

    public void deleteAllWordPairs() {
        db.delete(DatabaseHelper.TABLE_WORDS, null, null);
    }
}