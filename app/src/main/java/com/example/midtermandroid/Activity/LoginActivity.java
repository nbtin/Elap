package com.example.midtermandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.midtermandroid.R;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (etUsername.getText().toString().equals("admin") && etPassword.getText().toString().equals("123")){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Incorrect Username or Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, R.string.alert , Toast.LENGTH_SHORT).show();
            }
        });
    }
}