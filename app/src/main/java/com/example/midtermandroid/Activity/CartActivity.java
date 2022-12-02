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
import com.example.midtermandroid.Helper.BottomNavigation;
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
    private BottomNavigation bottomNavigation = new BottomNavigation(CartActivity.this);

    private ImageButton homeBtn;
    private ImageButton profileBtn;
    private ImageButton cartBtn;
    private ImageButton mapBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        CalculateCart();
        bottomNavigation.handleNavigation("cart", homeBtn, profileBtn, cartBtn, mapBtn);

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

        homeBtn = findViewById(R.id.btnHome);
        profileBtn = findViewById(R.id.btnProfile);
        cartBtn = findViewById(R.id.btnCart);
        mapBtn = findViewById(R.id.btnShowroom);
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

}