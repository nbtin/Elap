package com.example.midtermandroid.Activity;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midtermandroid.Adapter.CompareAdapter;
import com.example.midtermandroid.Domain.LaptopDomain;
import com.example.midtermandroid.Helper.BottomNavigation;
import com.example.midtermandroid.Helper.ManagementCart;
import com.example.midtermandroid.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowCompareActivity extends AppCompatActivity {
    // Laptop chọn
    private TextView tvTitle1, tvFee1, tvCPU1, tvRAM1, tvROM1, tvGPU1, tvOS1, tvScreen1;

    // Laptop muốn so sánh
    private TextView tvTitle2, tvFee2, tvCPU2, tvRAM2, tvROM2, tvGPU2, tvOS2, tvScreen2;

    private ImageView ivPic1, ivPic2;
    private LaptopDomain object1, object2;
    private ImageButton btnBack;
    private BottomNavigation bottomNavigation = new BottomNavigation(ShowCompareActivity.this);

    private ImageButton homeBtn;
    private ImageButton profileBtn;
    private ImageButton cartBtn;
    private ImageButton mapBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_compare);

        initView();
        getBundle();
        bottomNavigation.handleNavigation("compare", homeBtn, profileBtn, cartBtn, mapBtn);
    }

    private void loadLaptopData(){


        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://elap-7b6f1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference laptopsRef = database.getReference("laptops");

        laptopsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                LaptopDomain laptopDomain = snapshot.getValue(LaptopDomain.class);
//                laptopList.add(new LaptopDomain(laptopDomain));
//                adapterBestseller.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static String formatter(int value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

    private void getBundle() {
        object1 = (LaptopDomain) getIntent().getSerializableExtra("object1");
        object2 = (LaptopDomain) getIntent().getSerializableExtra("object2");

        Log.d("Name of laptop 1: ", object1.getTitle());
        Log.d("Name of laptop 2: ", object2.getTitle());

        int drawableResourceId = this.getResources().getIdentifier(object1.getPic(), "drawable", this.getPackageName());
//        Glide.with(this).load(drawableResourceId).into(ivPicDetail);
        Picasso.with(this).load(object1.getPic()).into(ivPic1);

        tvTitle1.setText(object1.getTitle());
        tvFee1.setText(String.valueOf(formatter(object1.getFee())));

        String [] token_detail_1 = object1.getDescription().split("/", -1);

        tvCPU1.setText(token_detail_1[0]);
        tvRAM1.setText(token_detail_1[1]);
        tvROM1.setText(token_detail_1[2]);
        tvScreen1.setText(token_detail_1[3]);
        if (token_detail_1[4].equals(".")){
            tvGPU1.setText("");
        }
        else {
            tvGPU1.setText(token_detail_1[4]);
        }
        tvOS1.setText(token_detail_1[5]);

        // Laptop 2
        Picasso.with(this).load(object2.getPic()).into(ivPic2);

        tvTitle2.setText(object2.getTitle());
        tvFee2.setText(String.valueOf(formatter(object2.getFee())));

        String [] token_detail_2 = object2.getDescription().split("/", -1);

        tvCPU2.setText(token_detail_2[0]);
        tvRAM2.setText(token_detail_2[1]);
        tvROM2.setText(token_detail_2[2]);
        tvScreen2.setText(token_detail_2[3]);
        if (token_detail_2[4].equals(".")){
            tvGPU2.setText("");
        }
        else {
            tvGPU2.setText(token_detail_2[4]);
        }
        tvOS2.setText(token_detail_2[5]);

        // numberOrder...



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowCompareActivity.this, ShowDetailActivity.class);
                intent.putExtra("object", object1);
                ShowCompareActivity.this.startActivity(intent);
            }
        });


    }

    private void initView() {
        btnBack = findViewById(R.id.btnBack);

        ivPic1 = findViewById(R.id.pic_laptop_1);
        tvTitle1 = findViewById(R.id.title_laptop_1);
        tvFee1 = findViewById(R.id.fee_laptop_1);
        tvCPU1 = findViewById(R.id.cpu_1);
        tvRAM1 = findViewById(R.id.ram_1);
        tvROM1 = findViewById(R.id.rom_1);
        tvScreen1 = findViewById(R.id.screen_1);
        tvGPU1 = findViewById(R.id.gpu_1);
        tvOS1 = findViewById(R.id.os_1);

        ivPic2 = findViewById(R.id.pic_laptop_2);
        tvTitle2 = findViewById(R.id.title_laptop_2);
        tvFee2 = findViewById(R.id.fee_laptop_2);
        tvCPU2 = findViewById(R.id.cpu_2);
        tvRAM2 = findViewById(R.id.ram_2);
        tvROM2 = findViewById(R.id.rom_2);
        tvScreen2 = findViewById(R.id.screen_2);
        tvGPU2 = findViewById(R.id.gpu_2);
        tvOS2 = findViewById(R.id.os_2);


        homeBtn = findViewById(R.id.btnHome);
        profileBtn = findViewById(R.id.btnProfile);
        cartBtn = findViewById(R.id.btnCart);
        mapBtn = findViewById(R.id.btnShowroom);

    }

}