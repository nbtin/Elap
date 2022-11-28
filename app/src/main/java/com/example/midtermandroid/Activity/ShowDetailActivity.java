package com.example.midtermandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.midtermandroid.Domain.LaptopDomain;
import com.example.midtermandroid.Helper.ManagementCart;
import com.example.midtermandroid.R;

public class ShowDetailActivity extends AppCompatActivity {
private TextView tvAddToCart;
private TextView tvTitleDetail, tvFeeDetail, tvDescriptionDetail;
private ImageView ivPicDetail;
private LaptopDomain object;
int numberOrder = 1;
private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);

        initView();
        getBundle();
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
        tvDescriptionDetail.setText(object.getDescription());

        // numberOrder...

        tvAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertLaptop(object);
            }
        });


    }

    private void initView() {
        tvAddToCart = findViewById(R.id.tvAddToCart);

        tvTitleDetail = findViewById(R.id.tvTitleDetail);
        tvFeeDetail = findViewById(R.id.tvFeeDetail);
        tvDescriptionDetail = findViewById(R.id.tvDescriptionDetail);
        ivPicDetail = findViewById(R.id.ivPicDetail);

    }
}