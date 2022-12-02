package com.example.midtermandroid.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
    private RecyclerView.Adapter adapter, adapterBestseller;
    private RecyclerView recyclerViewBrandList, recyclerViewBestsellerList;
    private boolean homeClicked = true, profileClicked = false, cartClicked = false, mapClicked = false;
    private TextView tvUsername;
    private ImageButton btnLogout;

    ArrayList<LaptopDomain> laptopList;


    private void mappingXML() {
        tvUsername = findViewById(R.id.tvUsername);
        btnLogout = findViewById(R.id.btnLogout);
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mappingXML();

        init();

        logOut();
        recyclerViewBrandList();
        recycleViewBestseller();
        bottomNavigation();
    }

    private void logOut(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.mAuthentication.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    private void bottomNavigation() {
        ImageButton homeBtn = findViewById(R.id.btnHome);
        ImageButton profileBtn = findViewById(R.id.btnProfile);
        ImageButton cartBtn = findViewById(R.id.btnCart);
        ImageButton mapBtn = findViewById(R.id.btnShowroom);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!homeClicked) {
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }
//                Toast.makeText(MainActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
//                if (!profileClicked) {
////                    Toast.makeText(MainActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
//                    profileClicked = true;
//                    }
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,CartActivity.class));
                if (!cartClicked) {
//                Toast.makeText(MainActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
                    cartClicked = true;
                }
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mapClicked) {
//                    Toast.makeText(MainActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,MapActivity.class));
                    mapClicked = true;
                }
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

        adapter = new BrandAdapter(brand);
        recyclerViewBrandList.setAdapter(adapter);
    }
    private void recycleViewBestseller(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBestsellerList = findViewById(R.id.recyclerView2);
        recyclerViewBestsellerList.setLayoutManager(linearLayoutManager);


//        laptopList.add(new LaptopDomain("Gigabyte Gaming G5 GD-51VN123SO", "lap_1", "Intel core i5 11400H/16GB/512GB/15.6\" FHD/GeForce RTX 3050 4GB/Win 11", 19490000));
//        laptopList.add(new LaptopDomain("Asus TUF Gaming FX506LHB-HN188W", "lap_2", "Intel core i5 10300H/8GB/512GB/15.6\"FHD/GTX 1650 4GB/Win 11", 17490000));
//        laptopList.add(new LaptopDomain("Laptop Acer Nitro Gaming AN515-57-54MV", "lap_3", "Intel core i5 11400H/8GB/512GB/15.6\"FHD/GeForce RTX 3050 4GB/Win 10", 22490000));
//        laptopList.add(new LaptopDomain("Laptop MSI Modern 15 A5M 235VN", "lap_4", "AMD Ryzen 7 5700U/8GB/512GB/15.6\"FHD/./Win 11", 15890000));
//        laptopList.add(new LaptopDomain("Laptop Dell Vostro V5410", "lap_5", "Intel core i5 11320H/8GB/512GB/14.0\"FHD/./Win 11", 20490000));
//        laptopList.add(new LaptopDomain("Lenovo Yoga Slim 7 Pro 14IHU5O", "lap_6", "Intel core i5 11300H/16GB/512GB/14\"2.8K OLED/./Win 11", 20990000));

        laptopList = new ArrayList<>();
        adapterBestseller = new BestsellerAdapter(laptopList);
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
}