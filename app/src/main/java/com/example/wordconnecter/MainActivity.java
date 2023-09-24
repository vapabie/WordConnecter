package com.example.wordconnecter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import android.util.Pair;

import java.sql.SQLDataException;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEdittext;
    private boolean isNameEmpty;
    private Button startButton;
    import androidx.appcompat.app.AppCompatActivity;
    private EditText usernameEdittext;
    private Button startButton;
    private TextView countdownTextView; // Hozzáadott TextView
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 30000; // 30 másodperc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_screen);

        usernameEdittext = findViewById(R.id.username);
        startButton = findViewById(R.id.start_button);
        countdownTextView = findViewById(R.id.countdownTextView); // TextView inicializálása

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEdittext.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            }
        });

        // Itt hívja meg a startCountdown() metódust, amikor az Activity létrejön
        startCountdown();
    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                countdownTextView.setText("Time is over!");
            }
        }.start();
    }

    private void updateCountdownText() {
        int seconds = (int) (timeLeftInMillis / 1000);
        countdownTextView.setText(Integer.toString(seconds) + " másodperc"); // Az időzítő kijelzése a TextView-ben
    }
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_screen);
        usernameEdittext =findViewById(R.id.username);
        startButton = findViewById(R.id.start_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEdittext.getText().toString();

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            }
        });

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




    private void insertData(DatabaseManager manager){
        manager.insert("keretrendszer","framework");
        manager.insert("böngésző","browser");
        manager.insert("képpont","pixel");
        manager.insert("tűzfal","firewall");
        manager.insert("gyorsítótár","cache");
        manager.insert("adat","data");
        manager.insert("csomópont","node");
        manager.insert("útválasztó","router");
        manager.insert("karakterkészlet","font");
        manager.insert("jelismétlő","repeater");
    }


}
