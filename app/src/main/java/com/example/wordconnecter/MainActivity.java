package com.example.wordconnecter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_screen);
        DatabaseManager manager = new DatabaseManager(this);
        try{
            manager.open();
            manager.insert("sajat", "custom");
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
}