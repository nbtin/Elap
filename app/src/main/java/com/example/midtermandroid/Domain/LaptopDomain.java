package com.example.midtermandroid.Domain;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class LaptopDomain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private String chipName;
    private int ram;
    private float screenSize;
    private String screenType;
    private String gpuName;
    private int windowsVersion;
    private String brandName;

    private int fee;
    private int numberInCart;

    public LaptopDomain(){

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

    public LaptopDomain(String title, String pic, String description, String chipName, int ram, float screenSize, String screenType, String gpuName, int windowsVersion, String brandName, int fee, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.chipName = chipName;
        this.ram = ram;
        this.screenSize = screenSize;
        this.screenType = screenType;
        this.gpuName = gpuName;
        this.windowsVersion = windowsVersion;
        this.brandName = brandName;
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

    public String getChipName() {
        return chipName;
    }

    public void setChipName(String chipName) {
        this.chipName = chipName;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getGpuName() {
        return gpuName;
    }

    public void setGpuName(String gpuName) {
        this.gpuName = gpuName;
    }

    public int getWindowsVersion() {
        return windowsVersion;
    }

    public void setWindowsVersion(int windowsVersion) {
        this.windowsVersion = windowsVersion;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
