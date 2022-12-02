package com.example.midtermandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midtermandroid.Domain.LaptopDomain;
import com.example.midtermandroid.Helper.BottomNavigation;
import com.example.midtermandroid.R;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private boolean homeClicked = false, profileClicked = true, cartClicked = false, mapClicked = false;
    private TextView tvUsername1, tvUsername2, tvEmail, tvPassword;
    private Button btnEditProfile;
    private Button btnLogoutProfile;
    private BottomNavigation bottomNavigation = new BottomNavigation(ProfileActivity.this);

    private ImageButton homeBtn;
    private ImageButton profileBtn;
    private ImageButton cartBtn;
    private ImageButton mapBtn ;


    ArrayList<LaptopDomain> laptopList;


    private void mappingXML() {
        tvUsername1 = findViewById(R.id.tvUsername1);
        tvUsername2 = findViewById(R.id.tvUsername2);
        tvEmail = findViewById(R.id.tvEmail);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnLogoutProfile = findViewById(R.id.btnLogoutProfile);
        tvPassword = findViewById(R.id.tvPassword);

        homeBtn = findViewById(R.id.btnHome);
        profileBtn = findViewById(R.id.btnProfile);
        cartBtn = findViewById(R.id.btnCart);
        mapBtn = findViewById(R.id.btnShowroom);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mappingXML();

        tvUsername1.setText(LoginActivity.user.getName());
        tvUsername2.setText(LoginActivity.user.getName());
        tvEmail.setText(LoginActivity.user.getEmail());

        logOut();
        bottomNavigation.handleNavigation("profile", homeBtn, profileBtn, cartBtn, mapBtn);



        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profileClicked){
                    Toast.makeText(ProfileActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
                    profileClicked = false;
                }
            }
        });
        tvPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(ProfileActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logOut(){
        btnLogoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.mAuthentication.signOut();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                Toast.makeText(ProfileActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

}