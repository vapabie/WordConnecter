package com.example.wordconnecter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    private TextView scoretextTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_screen);

        scoretextTextView = findViewById(R.id.scoretext);

        String username =getIntent().getStringExtra("username");

        scoretextTextView.setText(username + ", your\nscore is:");
    }

    public void startGameActivity (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
