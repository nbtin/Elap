package com.example.midtermandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
TextView tvSubtotal, tvDelivery, tvTotal, tvEmpty, tvCheckout;
private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();

        tvCheckout = findViewById(R.id.tvCheckout);
        tvCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartActivity.this, "This feature is not developed yet!", Toast.LENGTH_SHORT).show();
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
        if (managementCart.getListCart().isEmpty()){
            tvEmpty.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else {
            tvEmpty.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart(){
        int delivery = 10000;

        int itemTotal = managementCart.getTotalFee();
        int total = managementCart.getTotalFee() + delivery;

        tvSubtotal.setText(String.valueOf(formatter(itemTotal)) + " đ");
        tvDelivery.setText(String.valueOf(formatter(delivery)) + " đ");
        tvTotal.setText(String.valueOf(formatter(total)) + " đ");
    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.llHome);
        LinearLayout profileBtn = findViewById(R.id.llProfile);
        LinearLayout cartBtn = findViewById(R.id.llCart);
        LinearLayout settingBtn = findViewById(R.id.llSetting);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
//                Toast.makeText(MainActivity.this, "This feature is not developed yet!", Toast.LENGTH_SHORT).show();
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                Toast.makeText(CartActivity.this, "This feature is not developed yet!", Toast.LENGTH_SHORT).show();
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,CartActivity.class));
//                Toast.makeText(CartActivity.this, "This feature is not developed yet!", Toast.LENGTH_SHORT).show();
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                Toast.makeText(CartActivity.this, "This feature is not developed yet!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}