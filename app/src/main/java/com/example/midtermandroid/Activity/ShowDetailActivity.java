package com.example.midtermandroid.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.midtermandroid.Adapter.BestsellerAdapter;
import com.example.midtermandroid.Adapter.CompareAdapter;
import com.example.midtermandroid.Domain.LaptopDomain;
import com.example.midtermandroid.Helper.BottomNavigation;
import com.example.midtermandroid.Helper.ManagementCart;
import com.example.midtermandroid.R;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Vector;


import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Base64;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShowDetailActivity extends AppCompatActivity {
    private Button btnAddToCart;
    private TextView tvTitleDetail, tvFeeDetail, tvCPU, tvRAM, tvROM, tvGPU, tvOS, tvScreen, tvSoldout;
    private ConstraintLayout SR1, SR2, SR3, SR4, SR5;
    private ImageView ivPicDetail;
    private LaptopDomain object;
    int numberOrder = 1;
    private ManagementCart managementCart;
    private ImageButton btnBack;
    private BottomNavigation bottomNavigation = new BottomNavigation(ShowDetailActivity.this);

    private ImageButton homeBtn;
    private ImageButton profileBtn;
    private ImageButton cartBtn;
    private ImageButton mapBtn;
    private Button btnMap1, btnMap2, btnMap3, btnMap4, btnMap5;
    private ImageButton btnShare;

    private String Pic_link;
    private String Lap_name;

    private RecyclerView.Adapter adapter, adapterCompare;
    private RecyclerView recyclerViewCompareList;
    ArrayList<LaptopDomain> laptopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);

        initView();
        getBundle();
        recycleViewCompare();
        bottomNavigation.handleNavigation("detail", homeBtn, profileBtn, cartBtn, mapBtn);
        btnMapHandler();
        btnShareHandler();

    }

    private void recycleViewCompare() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCompareList = findViewById(R.id.rcv_compare);
        recyclerViewCompareList.setLayoutManager(linearLayoutManager);

        laptopList = new ArrayList<>();
        adapterCompare = new CompareAdapter(laptopList, new CompareAdapter.IClickListener(){
            @Override
            public void onClickCompare(LaptopDomain laptop) {
                Intent intent = new Intent(ShowDetailActivity.this, ShowCompareActivity.class);
                intent.putExtra("object1", object);
                intent.putExtra("object2", laptop);
                ShowDetailActivity.this.startActivity(intent);
            }

        });
        recyclerViewCompareList.setAdapter((adapterCompare));

        loadLaptopData();
    }

    private void loadLaptopData(){


        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://elap-7b6f1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference laptopsRef = database.getReference("laptops");

        laptopsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                LaptopDomain laptopDomain = snapshot.getValue(LaptopDomain.class);
                if(laptopDomain.getTitle().compareTo(object.getTitle()) != 0 && Math.abs(laptopDomain.getFee() - object.getFee()) <= 500000){
                    laptopList.add(laptopDomain);
                }
                adapterCompare.notifyDataSetChanged();
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
        btnMapHandler();
        btnShareHandler();

            }
        });
    }

    public static String formatter(int value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

    private void getBundle() {
        object = (LaptopDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
//        Glide.with(this).load(drawableResourceId).into(ivPicDetail);
        Picasso.with(this).load(object.getPic()).into(ivPicDetail);

        // assign object.pic for Pic_link
        this.Pic_link = object.getPic();
        this.Lap_name = object.getTitle();

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

        /* Show showrooms have this laptop */
        int[] available = {1, 1, 1, 1, 1};
        int sum = 0;
        for (int j : available) {
            sum += j;
        }
        System.out.println("The sum is " + sum);
        if (sum == 0){
            tvSoldout.setVisibility(View.VISIBLE);
            SR1.setVisibility(View.GONE);
            SR2.setVisibility(View.GONE);
            SR3.setVisibility(View.GONE);
            SR4.setVisibility(View.GONE);
            SR5.setVisibility(View.GONE);
        }
        else{
            Vector<Boolean> visible = new Vector<Boolean>();
            for (int i = 0; i < 5; i++){
                visible.add(available[i] == 1);
            }
            tvSoldout.setVisibility(View.GONE);
            for (int i = 0; i < available.length; i++){
                System.out.println("available is " + available[i]);
                switch(i){
                    case 0:
                        if (!visible.get(i))
                            SR1.setVisibility(View.GONE);
                        else SR1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        if (!visible.get(i))
                            SR2.setVisibility(View.GONE);
                        else SR2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        if (!visible.get(i))
                            SR3.setVisibility(View.GONE);
                        else SR3.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        if (!visible.get(i))
                            SR4.setVisibility(View.GONE);
                        else SR4.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        if (!visible.get(i))
                            SR5.setVisibility(View.GONE);
                        else SR5.setVisibility(View.VISIBLE);
                        break;

                }
            }
        }



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

        SR1 = findViewById(R.id.SR1);
        SR2 = findViewById(R.id.SR2);
        SR3 = findViewById(R.id.SR3);
        SR4 = findViewById(R.id.SR4);
        SR5 = findViewById(R.id.SR5);
        tvSoldout = findViewById(R.id.tvSoldout);

        ivPicDetail = findViewById(R.id.ivPicDetail);

        homeBtn = findViewById(R.id.btnHome);
        profileBtn = findViewById(R.id.btnProfile);
        cartBtn = findViewById(R.id.btnCart);
        mapBtn = findViewById(R.id.btnShowroom);

        btnMap1 = findViewById(R.id.btnMap1);
        btnMap2 = findViewById(R.id.btnMap2);
        btnMap3 = findViewById(R.id.btnMap3);
        btnMap4 = findViewById(R.id.btnMap4);
        btnMap5 = findViewById(R.id.btnMap5);

        btnShare = findViewById(R.id.btnShare);

    }


    private void btnMapHandler(){
        btnMap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:10.762772,106.682504?q=10.762772,106.682504(Showroom in University of Science)"));
                startActivity(intent);
            }
        });
        btnMap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:10.761274,106.682007?q=10.761274,106.682007(Showroom in University of Education)"));
                startActivity(intent);
            }
        });
        btnMap3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:10.760157,106.682247?q=10.760157,106.682247(Showroom in Saigon University)"));
                startActivity(intent);
            }
        });
        btnMap4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:10.771981,106.657983?q=10.771981,106.657983(Showroom in University of Technology)"));
                startActivity(intent);
            }
        });
        btnMap5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:10.783174,106.694646?q=10.783174,106.694646(Showroom in University of Economics)"));
                startActivity(intent);
            }
        });

    }

    private void btnShareHandler(){
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse(Pic_link))
                        .setShareHashtag(new ShareHashtag.Builder()
                                .setHashtag("#" + Lap_name.replaceAll("\\s+", "") + "_atElap")
                                .build())
                        .build();

//                ShareDialog shareDialog = new ShareDialog(ShowDetailActivity.this);
//                shareDialog.show(content, ShareDialog.Mode.WEB);
                ShareDialog.show(ShowDetailActivity.this, content);
            }
        });
    }


}