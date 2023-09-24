package com.example.wordconnecter;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLDataException;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private DatabaseManager manager;
    private List<WordPair> wordPairs;
    private Button[] hunButtons;
    private Button[] engButtons;

    private TextView timerTextView;

    private CountDownTimer cdtimer;
     private long timeLeft = 30000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        //inicalizáljuk a gombokat és a szavakat
        initButtons();
        initWordPairs();

        timerTextView = findViewById(R.id.timer);

        startCountdown();
    }

    private void startCountdown(){
        cdtimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long left ) {
                timeLeft = left;
                updateTimer();
            }

            @Override
            Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
            intent.putExtra("score", yourScoreValue); 
            startActivity(intent);
    }

    private void updateTimer(){
        int seconds = (int) (timeLeft / 1000);
        timerTextView.setText(Integer.toString(seconds) + " másodperc");
    }

    public void OpenScore(){
        String username = getIntent().getStringExtra("username");

        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    private void initButtons() {
        hunButtons = new Button[5];
        engButtons = new Button[5];

        // Inicializáld a gombokat a layoutban lévő id-k alapján
        for (int i = 0; i < 5; i++) {// A getResources().getIdentifier() metódussal a gombokat id alapján keressük meg
            hunButtons[i] = findViewById(getResources().getIdentifier("hun_button" + (i + 1), "id", getPackageName()));
            engButtons[i] = findViewById(getResources().getIdentifier("eng_button" + (i + 1), "id", getPackageName()));
        }
    }
    //getResources():basically az alkalmazás erőforrásait kezeli
    //getIdentifier():Ez a metódus segít megtalálni egy erőforrás azonosítóját az erőforrás neve és típusa alapján
    //getPackageName():Ez a metódus a jelenlegi Android alkalmazás csomagjának nevét adja vissza. Ezt a csomagnevet a harmadik paraméterként használjuk a getIdentifier() metódusban, hogy megtaláljuk az erőforrásokat az aktuális alkalmazásban.

    private void initWordPairs() {//szópárok inicializálása
        manager = new DatabaseManager(this);

        try {
            manager.open();
            wordPairs = manager.shuffleWordPairs(5); // itt fog az öt szópár kiválasztása és összakavarása történni
        } catch (SQLDataException e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        // A szópárokat állítja be a gombokra
        for (int i = 0; i < 5; i++) {
            hunButtons[i].setText(wordPairs.get(i).getHunWord());
            engButtons[i].setText(wordPairs.get(i).getEnWord());
        }
    }

}
