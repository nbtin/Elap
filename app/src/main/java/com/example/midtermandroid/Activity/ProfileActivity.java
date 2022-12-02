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
import com.example.midtermandroid.R;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private boolean homeClicked = false, profileClicked = true, cartClicked = false, mapClicked = false;
    private TextView tvUsername1, tvUsername2, tvEmail;
    private Button btnEditProfile;

    ArrayList<LaptopDomain> laptopList;


    private void mappingXML() {
        tvUsername1 = findViewById(R.id.tvUsername1);
        tvUsername2 = findViewById(R.id.tvUsername2);
        tvEmail = findViewById(R.id.tvEmail);
        btnEditProfile = findViewById(R.id.btnEditProfile);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mappingXML();

        tvUsername1.setText(LoginActivity.user.getName());
        tvUsername2.setText(LoginActivity.user.getName());
        tvEmail.setText(LoginActivity.user.getEmail());

        bottomNavigation();
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profileClicked){
                    Toast.makeText(ProfileActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
                    profileClicked = false;
                }
            }
        });
    }

    private void bottomNavigation() {
        ImageButton homeBtn = findViewById(R.id.btnHome);
        ImageButton profileBtn = findViewById(R.id.btnProfile);
        ImageButton cartBtn = findViewById(R.id.btnCart);
        ImageButton mapBtn = findViewById(R.id.btnShowroom);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                if (!homeClicked) {
//                Toast.makeText(MainActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
                    homeClicked = true;
                }
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (!profileClicked) {
////                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
//                    Toast.makeText(ProfileActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
//                    profileClicked = true;
//                }
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,CartActivity.class));
                if (!cartClicked) {
//                Toast.makeText(MainActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
                    cartClicked = true;
                }
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mapClicked) {
                    Toast.makeText(ProfileActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                    mapClicked = true;
                }
            }
        });
    }
}