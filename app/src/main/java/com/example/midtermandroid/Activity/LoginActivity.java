package com.example.midtermandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.midtermandroid.R;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    ConstraintLayout clLogin, clRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        clLogin = findViewById(R.id.clLogin);
        clRegister = findViewById(R.id.clRegister);

        clLogin.setOnClickListener(new View.OnClickListener() {
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
    }
}