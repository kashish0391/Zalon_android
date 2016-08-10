package com.zalonstyles.app.zalon.Model;

import android.widget.Button;
import android.widget.TextView;

/**
 * Created by KASHISH on 02-08-2016.
 */
public class ViewHolder2 {

    private TextView textview1;
    private TextView textView;
    private TextView textview2;
    private TextView textView3;
    private Button button;


    public TextView getTextview2() {
        return textview2;
    }

    public void setTextview2(TextView textview2) {
        this.textview2 = textview2;
    }

    public TextView getTextView3() {
        return textView3;
    }

    public void setTextView3(TextView textView3) {
        this.textView3 = textView3;
    }

    public ViewHolder2(TextView textView, TextView textview1, TextView textview2, TextView textview3, Button button)
    {
        this.textview1 = textview1;
        this.textView = textView;
        this.textview2 = textview2;
        this.textView3 = textview3;
        this.button = button;

    }

    public TextView getTextview1()
    {
        return textview1;
    }
    public Button getButton()
    {
        return button;
    }


    public void setButton(Button button)
    {
        this.button = button;
    }


    public void setTextview1(TextView textview1)
    {
        this.textview1 = textview1;
    }

    public TextView getTextView()
    {
        return textView;
    }

    public void setTextView(TextView textView)
    {
        this.textView = textView;
    }



}
