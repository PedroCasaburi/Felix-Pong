package com.example.felix_pong;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VITORIAENEMY extends AppCompatActivity {

    private Button joga, fecha;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitoriaenemy);
        fecha = findViewById(R.id.fecha);
        joga = findViewById(R.id.joga);

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(VITORIAENEMY.this,TELAFINAL.class);
                startActivity(intent);
            }
        });
        joga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VITORIAENEMY.this,DIFICULDADES.class);
                startActivity(intent);
            }
        });
    }
}