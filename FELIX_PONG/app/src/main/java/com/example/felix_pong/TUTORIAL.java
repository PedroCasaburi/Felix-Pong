package com.example.felix_pong;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TUTORIAL extends AppCompatActivity {

    private Button buttonjogar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        buttonjogar = findViewById(R.id.buttonjogar);

        buttonjogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TUTORIAL.this,DIFICULDADES.class);
                startActivity(intent);
            }
        });
    }
}