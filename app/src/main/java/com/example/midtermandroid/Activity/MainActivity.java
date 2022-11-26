package com.example.midtermandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.midtermandroid.Adapter.BrandAdapter;
import com.example.midtermandroid.Domain.BrandDomain;
import com.example.midtermandroid.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerViewBrandList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewBrandList();
    }

    private void recyclerViewBrandList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBrandList = findViewById(R.id.recyclerView);
        recyclerViewBrandList.setLayoutManager(linearLayoutManager);

        ArrayList<BrandDomain> brand = new ArrayList<>();
        brand.add(new BrandDomain("MacBook", "brand_1"));
        brand.add(new BrandDomain("Dell", "brand_2"));
        brand.add(new BrandDomain("Asus", "brand_3"));
        brand.add(new BrandDomain("Acer", "brand_4"));
        brand.add(new BrandDomain("Lenovo", "brand_5"));

        adapter = new BrandAdapter(brand);
        recyclerViewBrandList.setAdapter(adapter);
    }
}