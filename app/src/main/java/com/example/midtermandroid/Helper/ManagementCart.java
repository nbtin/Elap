package com.example.midtermandroid.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.midtermandroid.Domain.LaptopDomain;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertLaptop(LaptopDomain item){
        ArrayList<LaptopDomain> listLaptop = getListCart();
        boolean alreadyExist = false;
        int n = 0;
        for (int i = 0; i < listLaptop.size(); i++){
            if (listLaptop.get(i).getTitle().equals(item.getTitle())){
                alreadyExist = true;
                n = i;
                break;
            }
        }

        if (alreadyExist){
            listLaptop.get(n).setNumberInCart(item.getNumberInCart());
        }
        else {
            listLaptop.add(item);
        }
        tinyDB.putListObject("CartList", listLaptop);
        Toast.makeText(context,"Added to your cart!", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<LaptopDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }
}
