package com.zalonstyles.app.zalon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;

/**
 * Created by KASHISH on 03-11-2016.
 */

public class Acctype extends AppCompatActivity{
    private Button gen;
    private Button othr;
    private Button supplier;
    private Button employee;
    private Button Bank;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acctype);
        gen = (Button) findViewById(R.id.accgeneral);
        othr =(Button)findViewById(R.id.accothers) ;
        supplier = (Button) findViewById(R.id.accsupp);
        employee= (Button) findViewById(R.id.accemployee);
        Bank = (Button) findViewById(R.id.accbank);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.60),(int)(height*.66));

    }
}
