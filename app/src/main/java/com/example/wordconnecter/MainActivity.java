package com.example.wordconnecter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

import java.sql.SQLDataException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_screen);
        DatabaseManager manager = new DatabaseManager(this);
        try{
            manager.open();
           // manager.insert("sajat", "custom");
            manager.deleteAllWordPairs();
            insertData(manager);
            manager.shuffleWordPairs(5);
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            // Adatbázis bezárása
            manager.close();
        }
    }


    public void startGameActivity (View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }


    private void insertData(DatabaseManager manager){
        manager.insert("magyar1","angol1");
        manager.insert("magyar2","angol2");
        manager.insert("magyar3","angol3");
        manager.insert("magyar4","angol4");
        manager.insert("magyar5","angol5");
        manager.insert("magyar6","angol6");
        manager.insert("magyar7","angol7");
        manager.insert("magyar8","angol8");
        manager.insert("magyar9","angol9");
        manager.insert("magyar10","angol10");
    }


}