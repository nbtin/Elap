package com.example.midtermandroid.Domain;

import java.io.Serializable;

public class LaptopDomain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private int fee;
    private int numberInCart;

    public LaptopDomain(){

    }

    public LaptopDomain(LaptopDomain other){
        this.title = other.title;
        this.pic = other.pic;
        this.description = other.description;
        this.fee = other.fee;
    }

    public LaptopDomain(String title, String pic, String description, int fee) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
    }

    public LaptopDomain(String title, String pic, String description, int fee, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
