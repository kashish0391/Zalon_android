package com.zalonstyles.app.zalon.Model;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by KASHISH on 12-07-2016.
 */
public class ViewHolder
{
    private CheckBox checkBox;
    private TextView textView;



    public ViewHolder(TextView textView, CheckBox checkBox)
    {
        this.checkBox = checkBox;
        this.textView = textView;

    }

    public CheckBox getCheckBox()
    {
        return checkBox;
    }


    public void setCheckBox(CheckBox checkBox)
    {
        this.checkBox = checkBox;
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
