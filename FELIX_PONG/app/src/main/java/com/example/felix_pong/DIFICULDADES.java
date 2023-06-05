package com.example.felix_pong;




import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DIFICULDADES extends AppCompatActivity {
    private Button buttondifiFacil, buttondifiMedio, buttondifiGema;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificuldades);

        buttondifiFacil = findViewById(R.id.buttondifiFacil);
        buttondifiMedio = findViewById(R.id.buttondifiMedio);
        buttondifiGema = findViewById(R.id.buttondifiGema);

        buttondifiFacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameActivity("easy");
            }
        });
        buttondifiMedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameActivity("medium");
            }
        });
        buttondifiGema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameActivity("hard");
            }
        });
    }

    private void startGameActivity(String difficulty) {
        Intent intent = new Intent(DIFICULDADES.this, GAME.class);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
    }
}