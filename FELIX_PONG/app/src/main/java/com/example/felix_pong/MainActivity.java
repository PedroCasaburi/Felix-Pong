package com.example.felix_pong;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttoniniciarjogo, buttoncreditos, buttontutorial;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttontutorial = findViewById(R.id.buttontutorial);
        buttoniniciarjogo = findViewById(R.id.buttoniniciarjogo);
        buttoncreditos = findViewById(R.id.buttoncreditos);

        buttontutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,TUTORIAL.class);
                startActivity(intent);
            }
        });

        buttoniniciarjogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DIFICULDADES.class);
                startActivity(intent);
            }
        });

        buttoncreditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent INTENT = new Intent(MainActivity.this, creditos.class);
                startActivity(INTENT);
            }
        });
    }

}