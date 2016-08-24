package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 23-08-2016.
 */
public class pricevariable {
    private String name = null;
    private int price;
    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
