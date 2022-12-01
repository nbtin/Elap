package com.example.midtermandroid.Domain;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UserDomain {
    private String email;
    private String name;

    public UserDomain() {
        email = "No email";
        name = "No name";
    }

    public UserDomain(UserDomain other){
        this.name = other.name;
        this.email = other.email;
    }

    public UserDomain(String email, String displayedName) {
        this.email = email;
        this.name = displayedName;
    }

    public void update(UserDomain other){
        this.name = other.name;
        this.email = other.email;
    }

    public void createRecord() {
        DatabaseReference mDatabase = FirebaseDatabase
                .getInstance("https://elap-7b6f1-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("users");

        System.out.println(email);
        System.out.println(name);

        mDatabase.push().setValue(this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> map = new HashMap<>();

        map.put("name", this.name);
        map.put("email", this.email);

        return map;
    }
}
