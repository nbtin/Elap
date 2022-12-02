package com.example.midtermandroid.Activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.midtermandroid.Domain.ShowroomDomain;
import com.example.midtermandroid.Helper.BottomNavigation;
import com.example.midtermandroid.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<MarkerOptions> markerOptions;

    private BottomNavigation bottomNavigation = new BottomNavigation(MapActivity.this);

    private ImageButton homeBtn;
    private ImageButton profileBtn;
    private ImageButton cartBtn;
    private ImageButton mapBtn ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initView();
        bottomNavigation.handleNavigation("showroom", homeBtn, profileBtn, cartBtn, mapBtn);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


//        bottomNavigation.handleNavigation("showroom", MapActivity.this);
    }

    private void initView() {
        homeBtn = findViewById(R.id.btnHome);
        profileBtn = findViewById(R.id.btnProfile);
        cartBtn = findViewById(R.id.btnCart);
        mapBtn = findViewById(R.id.btnShowroom);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        loadShowroomData();
    }

    private void loadShowroomData() {
        if (markerOptions == null) {
            markerOptions = new ArrayList<>();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://elap-7b6f1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference showroomRef = database.getReference("showrooms");

        showroomRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                markerOptions.clear();
                int i = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ShowroomDomain showroomDomain = (ShowroomDomain) snapshot.getValue(ShowroomDomain.class);
                    markerOptions.add(showroomDomain.toMarkerOptions());
                    mMap.addMarker((markerOptions.get(i)));
                    i++;
                }

                if (markerOptions.size() > 0){
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(markerOptions.get(0).getPosition()));
                } else{
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-34, 151)));
                }

                mMap.animateCamera(CameraUpdateFactory.zoomTo(13.0f));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                markerOptions.clear();
                int i = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ShowroomDomain showroomDomain = (ShowroomDomain) snapshot.getValue(ShowroomDomain.class);
                    markerOptions.add(showroomDomain.toMarkerOptions());
                    mMap.addMarker((markerOptions.get(i)));
                    i++;
                }

                if (markerOptions.size() > 0){
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(markerOptions.get(0).getPosition()));
                } else{
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-34, 151)));
                }

                mMap.animateCamera(CameraUpdateFactory.zoomTo(13.0f));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                markerOptions.clear();
                int i = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ShowroomDomain showroomDomain = (ShowroomDomain) snapshot.getValue(ShowroomDomain.class);
                    markerOptions.add(showroomDomain.toMarkerOptions());
                    mMap.addMarker((markerOptions.get(i)));
                    i++;
                }

                if (markerOptions.size() > 0){
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(markerOptions.get(0).getPosition()));
                } else{
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-34, 151)));
                }

                mMap.animateCamera(CameraUpdateFactory.zoomTo(13.0f));
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                markerOptions.clear();
                int i = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ShowroomDomain showroomDomain = (ShowroomDomain) snapshot.getValue(ShowroomDomain.class);
                    markerOptions.add(showroomDomain.toMarkerOptions());
                    mMap.addMarker((markerOptions.get(i)));
                    i++;
                }

                if (markerOptions.size() > 0){
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(markerOptions.get(0).getPosition()));
                } else{
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-34, 151)));
                }

                mMap.animateCamera(CameraUpdateFactory.zoomTo(13.0f));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MapActivity.this, "Actions has been cancelled!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}