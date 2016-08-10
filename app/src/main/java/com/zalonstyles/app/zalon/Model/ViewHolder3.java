package com.zalonstyles.app.zalon.Model;

import android.widget.TextView;

/**
 * Created by KASHISH on 06-08-2016.
 */
public class ViewHolder3 {
    private TextView textview1;
    private TextView textView;

    public ViewHolder3(TextView textView, TextView textview1)
    {
        this.textview1 = textview1;
        this.textView = textView;

    }

    public TextView getTextview1()
    {
        return textview1;
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
