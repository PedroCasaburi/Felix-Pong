package com.example.felix_pong;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText mEmailEditText, mPasswordEditText;
    Button mLoginButton;
    TextView mRegisterTextView;
    DatabaseHelper mDatabaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailEditText = findViewById(R.id.login_email);
        mPasswordEditText = findViewById(R.id.login_password);
        mLoginButton = findViewById(R.id.login_button);
        mRegisterTextView = findViewById(R.id.register_text);

        mDatabaseHelper = new DatabaseHelper(this);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Por favor insira o email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Por favor insira a senha", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean userExists = mDatabaseHelper.checkUser(email, password);
                if (userExists){
                    Toast.makeText(Login.this, "Você logou com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("KEY_EMAIL", email);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Login.this, "Email ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, cadastro.class);
                startActivity(intent);
                finish();
            }
        });

    }
}