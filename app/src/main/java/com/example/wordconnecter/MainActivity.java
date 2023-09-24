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
