package com.zalonstyles.app.zalon.Model;

import android.widget.Button;
import android.widget.TextView;

/**
 * Created by KASHISH on 21-07-2016.
 */
public class viewHolder1
{
    private TextView textview1;
    private TextView textView;
    private Button button;
    private TextView textView3;

    public TextView getTextView3() {
        return textView3;
    }

    public void setTextView3(TextView textView3) {
        this.textView3 = textView3;
    }

    public viewHolder1(TextView textView, TextView textview1, Button button,TextView textView3)
    {
        this.textview1 = textview1;
        this.textView = textView;
        this.button = button;
        this.textView3 = textView3;

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

