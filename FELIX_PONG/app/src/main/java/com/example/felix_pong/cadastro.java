package com.example.felix_pong;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class cadastro extends AppCompatActivity {

    EditText mEmailEditText;

    EditText mPasswordEditText;

    EditText mConfirmPasswordEditText;

    Button mRegisterButton;

    TextView mLoginTextView;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mEmailEditText = findViewById(R.id.register_email);
        mPasswordEditText = findViewById(R.id.register_password);
        mConfirmPasswordEditText = findViewById(R.id.register_confirm_password);
        mRegisterButton = findViewById(R.id.register_button);
        mLoginTextView = findViewById(R.id.login_text);

        mDatabaseHelper = new DatabaseHelper(this);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(cadastro.this, "Insira o campo e-mail", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(cadastro.this, "Insira o campo senha", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(cadastro.this, "Por favor insira o campo de confirma senha", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(confirmPassword)){
                    Toast.makeText(cadastro.this, "As senhas não conferem", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean userExists = mDatabaseHelper.checkUser(email, password);

                if(userExists){
                    Toast.makeText(cadastro.this, "Usuario com este e-mail já existe", Toast.LENGTH_SHORT).show();
                    return;
                }

                long id = mDatabaseHelper.createUser(email, password);

                if(id > 0){
                    Toast.makeText(cadastro.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(cadastro.this, Login.class);
                    startActivity(intent);

                    finish();
                }else{
                    Toast.makeText(cadastro.this, "Cadastro com falha, usuário ja existe", Toast.LENGTH_SHORT).show();
                }


            }
        });

        mLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cadastro.this, Login.class);
                startActivity(intent);

                finish();
            }
        });
    }
}