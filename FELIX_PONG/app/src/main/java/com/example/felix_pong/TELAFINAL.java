package com.example.felix_pong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TELAFINAL extends AppCompatActivity {

    private Button buttonjoganovamente, buttonfecharjogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telafinal);

        buttonjoganovamente = findViewById(R.id.buttonjoganovamente);
        buttonfecharjogo = findViewById(R.id.buttonfecharjogo);

        buttonjoganovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TELAFINAL.this,DIFICULDADES.class);
                startActivity(intent);
            }
        });

        buttonfecharjogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TELAFINAL.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}