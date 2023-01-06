package com.example.midtermandroid.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midtermandroid.Adapter.BestsellerAdapter;
import com.example.midtermandroid.Adapter.BrandAdapter;
import com.example.midtermandroid.Domain.BrandDomain;
import com.example.midtermandroid.Domain.LaptopDomain;
import com.example.midtermandroid.Domain.UserDomain;
import com.example.midtermandroid.Helper.BottomNavigation;
import com.example.midtermandroid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static RecyclerView.Adapter adapter, adapterBestseller;
    private static RecyclerView recyclerViewBrandList, recyclerViewBestsellerList;
    private TextView tvUsername;
    private ImageButton btnLogout;
    private BottomNavigation bottomNavigation = new BottomNavigation(MainActivity.this);

    private ImageButton homeBtn;
    private ImageButton profileBtn;
    private ImageButton cartBtn;
    private ImageButton mapBtn ;

    private ConstraintLayout clBrand;

    private static String currentBrand;
    private static ArrayList<LaptopDomain> laptopWithBrandList;
    private static ArrayList<LaptopDomain> laptopList;

    private void mappingXML() {
        tvUsername = findViewById(R.id.tvUsername);
        btnLogout = findViewById(R.id.btnLogout);

        homeBtn = findViewById(R.id.btnHome);
        profileBtn = findViewById(R.id.btnProfile);
        cartBtn = findViewById(R.id.btnCart);
        mapBtn = findViewById(R.id.btnShowroom);

        clBrand = findViewById(R.id.clMainLayout);
    }

    private void init() {
        FirebaseUser firebaseUser = LoginActivity.mAuthentication.getCurrentUser();

        DatabaseReference databaseRef = FirebaseDatabase
                .getInstance("https://elap-7b6f1-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("users");

        Query query = databaseRef.orderByChild("email").equalTo(firebaseUser.getEmail());

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (LoginActivity.user == null) {
                    LoginActivity.user = new UserDomain();
                }

                LoginActivity.user.update(snapshot.getValue(UserDomain.class));

               try {
                    tvUsername.setText(LoginActivity.user.getName());
                } catch (NullPointerException exception) {
                    System.err.println(exception.toString());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (LoginActivity.user == null) {
                    LoginActivity.user = new UserDomain();
                }

                LoginActivity.user.update(snapshot.getValue(UserDomain.class));

                try {
                    tvUsername.setText(LoginActivity.user.getName());
                } catch (NullPointerException exception) {
                    System.err.println(exception.toString());
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Toast.makeText(MainActivity.this, "Account has been deleted!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Toast.makeText(MainActivity.this, "Account has been deleted!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Action has been cancelled!", Toast.LENGTH_SHORT).show();
            }
        });

        currentBrand = "All";
        loadLaptopData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mappingXML();

        init();

        bottomNavigation.handleNavigation("home", homeBtn, profileBtn, cartBtn, mapBtn);
        logOut();
        recyclerViewBrandList();
        recycleViewBestseller();
    }


    private void logOut(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.mAuthentication.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            }
        });
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
        // TODO: add a new brand name "All"
        adapter = new BrandAdapter(brand);
        recyclerViewBrandList.setAdapter(adapter);
    }
    private void recycleViewBestseller(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBestsellerList = findViewById(R.id.recyclerView2);
        recyclerViewBestsellerList.setLayoutManager(linearLayoutManager);

        // TODO: add adapter of laptop with brand list
        adapterBestseller = new BestsellerAdapter(laptopWithBrandList);
        recyclerViewBestsellerList.setAdapter((adapterBestseller));
    }

    private void loadLaptopData(){
        if (laptopList == null) {
            laptopList = new ArrayList<>();
        }

        if (laptopWithBrandList == null) {
            laptopWithBrandList = new ArrayList<>();
        }

        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://elap-7b6f1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference laptopsRef = database.getReference("laptops");

        laptopsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                LaptopDomain laptopDomain = snapshot.getValue(LaptopDomain.class);
                laptopList.add(laptopDomain);

                if (currentBrand.equals("All") || laptopDomain.getBrandName().equals(currentBrand)) {
                    laptopWithBrandList.add(laptopDomain);
                    // TODO: change to adapter of the laptop with brand list
                    adapterBestseller.notifyDataSetChanged();
                }

                // TODO: uncomment
                //adapterBestseller.notifyDataSetChanged();

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

    public static void updateLaptopWithBrandList(String brandName) {
        if (currentBrand.equals(brandName)) {
            return;
        } else {
            currentBrand = brandName;
            laptopWithBrandList.clear();
        }

        for (LaptopDomain laptopDomain: laptopList) {
            if (currentBrand.equals("All") || laptopDomain.getBrandName().equals(currentBrand)){
                laptopWithBrandList.add(laptopDomain);
            }
        }
        // TODO: convert to adapter of laptop with brand list
        adapterBestseller.notifyDataSetChanged();
    }

}