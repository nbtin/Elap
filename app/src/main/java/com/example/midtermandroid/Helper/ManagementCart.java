package com.example.midtermandroid.Helper;

import android.content.Context;
import android.widget.Toast;

import com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool;
import com.example.midtermandroid.Domain.LaptopDomain;
import com.example.midtermandroid.Interface.ChangeNumber;
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

    public void addNumberLaptop(ArrayList<LaptopDomain> laptopList, int position, ChangeNumber changeNumber){
        laptopList.get(position).setNumberInCart(laptopList.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList", laptopList);
        changeNumber.changed();
    }
    public void minusNumberLaptop(ArrayList<LaptopDomain> laptopList, int position, ChangeNumber changeNumber){
        if (laptopList.get(position).getNumberInCart() == 1){
            laptopList.remove(position);
        }
        else {
            laptopList.get(position).setNumberInCart(laptopList.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList", laptopList);
        changeNumber.changed();
    }
    public int getTotalFee(){
        ArrayList<LaptopDomain> laptopList = getListCart();
        int fee = 0;
        for (int i = 0; i < laptopList.size(); i++){
            fee += laptopList.get(i).getFee() * laptopList.get(i).getNumberInCart();
        }
        return fee;
    }
}
