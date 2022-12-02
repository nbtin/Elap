package com.example.midtermandroid.Helper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.midtermandroid.Activity.CartActivity;
import com.example.midtermandroid.Activity.MainActivity;
import com.example.midtermandroid.Activity.MapActivity;
import com.example.midtermandroid.Activity.ProfileActivity;
import com.example.midtermandroid.R;

public class BottomNavigation extends AppCompatActivity {
    private Context context;

    public BottomNavigation(Context context) {
        this.context = context;
    }

    public void handleNavigation(String activity, ImageButton homeBtn, ImageButton profileBtn, ImageButton cartBtn, ImageButton mapBtn) {

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!activity.equals("home")){context.startActivity(new Intent(context, MainActivity.class));}
            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!activity.equals("profile")){context.startActivity(new Intent(context, ProfileActivity.class));}
            }
        });


        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!activity.equals("cart")){context.startActivity(new Intent(context, CartActivity.class));}
            }
        });



        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!activity.equals("showroom")){context.startActivity(new Intent(context, MapActivity.class));}
            }
        });


    }
}
