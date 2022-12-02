package com.example.midtermandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midtermandroid.Adapter.CartAdapter;
import com.example.midtermandroid.Helper.ManagementCart;
import com.example.midtermandroid.Interface.ChangeNumber;
import com.example.midtermandroid.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView tvSubtotal, tvDelivery, tvTotal, tvEmpty;
    Button btnCheckout;
    ConstraintLayout clBill;
    private ScrollView scrollView;
    private boolean homeClicked = false, profileClicked = false, cartClicked = true, mapClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();

        btnCheckout = findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String formatter(int value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.rvCart);
        tvSubtotal = findViewById(R.id.tvSubtotal);
        tvDelivery = findViewById(R.id.tvDelivery);
        tvTotal = findViewById(R.id.tvTotal);
        tvEmpty = findViewById(R.id.tvEmpty);
        scrollView = findViewById(R.id.scrollView4);
        clBill = findViewById(R.id.clBill);
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managementCart.getListCart(), this, new ChangeNumber() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
    }

    private void CalculateCart(){
        if (managementCart.getListCart().size() == 0){
            scrollView.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
            return;
        }
        else {
            tvEmpty.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

        int delivery = 0;

        int itemTotal = managementCart.getTotalFee();
        int total = managementCart.getTotalFee() + delivery;

        tvSubtotal.setText(String.valueOf(formatter(itemTotal)) + " đ");
        tvDelivery.setText(String.valueOf(formatter(delivery)) + " đ");
        tvTotal.setText(String.valueOf(formatter(total)) + " đ");
    }

    private void bottomNavigation() {
        ImageButton homeBtn = findViewById(R.id.btnHome);
        ImageButton profileBtn = findViewById(R.id.btnProfile);
        ImageButton cartBtn = findViewById(R.id.btnCart);
        ImageButton mapBtn = findViewById(R.id.btnShowroom);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!homeClicked){
                startActivity(new Intent(CartActivity.this, MainActivity.class));
//                Toast.makeText(MainActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
                    homeClicked = true;
                }
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, ProfileActivity.class));
//                if (!profileClicked){
////                Toast.makeText(CartActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
//                    profileClicked = true;
//                }
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cartClicked){
                startActivity(new Intent(CartActivity.this,CartActivity.class));
//                Toast.makeText(CartActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
                    cartClicked = false;
                }
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mapClicked){
//                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                Toast.makeText(CartActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
                    mapClicked = true;
                }
            }
        });
    }
}