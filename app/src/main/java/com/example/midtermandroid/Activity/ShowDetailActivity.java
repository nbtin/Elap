package com.example.midtermandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.midtermandroid.Domain.LaptopDomain;
import com.example.midtermandroid.Helper.ManagementCart;
import com.example.midtermandroid.R;

public class ShowDetailActivity extends AppCompatActivity {
    private Button btnAddToCart;
    private TextView tvTitleDetail, tvFeeDetail, tvCPU, tvRAM, tvROM, tvGPU, tvOS, tvScreen;
    private ImageView ivPicDetail;
    private LaptopDomain object;
    int numberOrder = 1;
    private ManagementCart managementCart;
    private ImageButton btnBack;
    private boolean homeClicked = false, profileClicked = false, cartClicked = false, settingClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);

        initView();
        getBundle();
        bottomNavigation();

    }

    public static String formatter(int value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

    private void getBundle() {
        object = (LaptopDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(ivPicDetail);

        tvTitleDetail.setText(object.getTitle());
        tvFeeDetail.setText(String.valueOf(formatter(object.getFee())));

        String [] token_detail = object.getDescription().split("/", -1);

        tvCPU.setText(token_detail[0]);
        tvRAM.setText(token_detail[1]);
        tvROM.setText(token_detail[2]);
        tvScreen.setText(token_detail[3]);
        if (token_detail[4].equals(".")){
            tvGPU.setText("");
        }
        else {
            tvGPU.setText(token_detail[4]);
        }
        tvOS.setText(token_detail[5]);

        // numberOrder...

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertLaptop(object);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowDetailActivity.this, MainActivity.class));
            }
        });


    }

    private void initView() {
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBack = findViewById(R.id.btnBack);
        tvTitleDetail = findViewById(R.id.tvTitleDetail);
        tvFeeDetail = findViewById(R.id.tvFeeDetail);
        tvCPU = findViewById(R.id.tvCPU);
        tvRAM = findViewById(R.id.tvRAM);
        tvROM = findViewById(R.id.tvROM);
        tvScreen = findViewById(R.id.tvScreen);
        tvGPU = findViewById(R.id.tvGPU);
        tvOS = findViewById(R.id.tvOS);

        ivPicDetail = findViewById(R.id.ivPicDetail);

    }

    private void bottomNavigation() {
        ImageButton homeBtn = findViewById(R.id.btnHome);
        ImageButton profileBtn = findViewById(R.id.btnProfile);
        ImageButton cartBtn = findViewById(R.id.btnCart);
        ImageButton settingBtn = findViewById(R.id.btnSetting);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!homeClicked) {
                    startActivity(new Intent(ShowDetailActivity.this, MainActivity.class));
                }
//                Toast.makeText(MainActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!profileClicked) {
//                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    Toast.makeText(ShowDetailActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
                    profileClicked = true;
                }
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cartClicked) {
                    startActivity(new Intent(ShowDetailActivity.this,CartActivity.class));
//                Toast.makeText(MainActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
                    cartClicked = true;
                }
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!settingClicked) {
                    Toast.makeText(ShowDetailActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                    settingClicked = true;
                }
            }
        });
    }
}