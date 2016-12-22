package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 07-08-2016.
 */
public class ProfileModel1 {
    private String day;
    private String time;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ProfileModel1(){

    }
    public ProfileModel1(String day)
    {
        this.day = day;
    }

    public ProfileModel1(String day, String time)
    {
        this.day = day;
        this.time = time;

    }}


