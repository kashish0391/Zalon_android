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



    public viewHolder1(TextView textView, TextView textview1,Button button)
    {
        this.textview1 = textview1;
        this.textView = textView;
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

