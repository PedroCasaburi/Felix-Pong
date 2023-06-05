package com.example.felix_pong;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VITORIAPLAYER extends AppCompatActivity {

    private Button buttonfjogo,buttonjdnv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitoriaplayer);

        buttonfjogo= findViewById(R.id.buttonfjogo);
        buttonjdnv=findViewById(R.id.buttonjdnv);

        buttonfjogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VITORIAPLAYER.this, TELAFINAL.class);
                startActivity(intent);
            }
        });

        buttonjdnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VITORIAPLAYER.this, DIFICULDADES.class);
                startActivity(intent);
            }
        });
    }
}