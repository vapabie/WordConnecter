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

public class MainActivity extends AppCompatActivity {

    private EditText usernameEdittext;
    private boolean isNameEmpty;
    private Button startButton;

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