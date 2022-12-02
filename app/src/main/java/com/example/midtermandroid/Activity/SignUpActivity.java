package com.example.midtermandroid.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.midtermandroid.Domain.UserDomain;
import com.example.midtermandroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuthentication;
    EditText etUsername, etPassword, etName, etRetypePassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuthentication = FirebaseAuth.getInstance();
        mappingXML();
        signUp();
    }

    private void mappingXML(){
        etUsername = findViewById(R.id.signUp_etUsername);
        etPassword = findViewById(R.id.signUp_etPassword);
        etName = findViewById(R.id.signUp_etName);
        btnRegister = findViewById(R.id.signUp_btnRegister);
        etRetypePassword = findViewById(R.id.signUp_etRetypePassword);
    }

    private void signUp(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String name = etName.getText().toString();
                String retypePassword = etRetypePassword.getText().toString();

                if (email.isEmpty() || password.isEmpty() || name.isEmpty() || retypePassword.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(retypePassword)){
                    Toast.makeText(SignUpActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                }
                else if (password.length() < 6){
                    Toast.makeText(SignUpActivity.this, "Passwords must be at least 6 characters!", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuthentication.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        //Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser firebaseUser = mAuthentication.getCurrentUser();

                                        UserDomain userDomain = new UserDomain(email, name);
                                        userDomain.createRecord();

                                        Toast.makeText(SignUpActivity.this, "Signed up successfully.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignUpActivity.this, "Cannot create user with given email!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
    });
}
}