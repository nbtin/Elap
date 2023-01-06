package com.example.midtermandroid.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midtermandroid.Adapter.BestsellerAdapter;
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

public class ShowDetailActivity extends AppCompatActivity {
    private Button btnAddToCart;
    private TextView tvTitleDetail, tvFeeDetail, tvCPU, tvRAM, tvROM, tvGPU, tvOS, tvScreen;
    private ImageView ivPicDetail;
    private LaptopDomain object;
    int numberOrder = 1;
    private ManagementCart managementCart;
    private ImageButton btnBack;
    private BottomNavigation bottomNavigation = new BottomNavigation(ShowDetailActivity.this);

    private ImageButton homeBtn;
    private ImageButton profileBtn;
    private ImageButton cartBtn;
    private ImageButton mapBtn ;

    private RecyclerView.Adapter adapter, adapterBestseller;
    private RecyclerView recyclerViewBestsellerList;
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
    }

    private void recycleViewCompare() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBestsellerList = findViewById(R.id.rcv_compare);
        recyclerViewBestsellerList.setLayoutManager(linearLayoutManager);

        laptopList = new ArrayList<>();
        adapterBestseller = new CompareAdapter(laptopList, new CompareAdapter.IClickListener(){
            @Override
            public void onClickCompare(LaptopDomain laptop) {
                Intent intent = new Intent(ShowDetailActivity.this, ShowCompareActivity.class);
                intent.putExtra("object1", object);
                intent.putExtra("object2", laptop);
                ShowDetailActivity.this.startActivity(intent);
            }

        });
        recyclerViewBestsellerList.setAdapter((adapterBestseller));

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
                laptopList.add(new LaptopDomain(laptopDomain));
                adapterBestseller.notifyDataSetChanged();
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
        object = (LaptopDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
//        Glide.with(this).load(drawableResourceId).into(ivPicDetail);
        Picasso.with(this).load(object.getPic()).into(ivPicDetail);

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

        homeBtn = findViewById(R.id.btnHome);
        profileBtn = findViewById(R.id.btnProfile);
        cartBtn = findViewById(R.id.btnCart);
        mapBtn = findViewById(R.id.btnShowroom);

    }

}