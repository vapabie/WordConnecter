package com.example.wordconnecter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_screen);
    }

    public void startGameActivity (View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
