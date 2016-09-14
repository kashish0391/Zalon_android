package com.zalonstyles.app.zalon.Model;

import android.widget.Button;
import android.widget.TextView;

/**
 * Created by KASHISH on 09-09-2016.
 */
public class viewholderbill {
    private TextView textview1;
    private TextView textView;
    private TextView textview2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private Button button;
    public viewholderbill(TextView textView, TextView textview1, TextView textview2, TextView textview3,TextView textView4,TextView textView5, Button button)
    {
        this.textview1 = textview1;
        this.textView = textView;
        this.textview2 = textview2;
        this.textView3 = textview3;
        this.textView4 = textView4;
        this.textView5 = textView5;
        this.button = button;

    }


    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public TextView getTextview1() {
        return textview1;
    }

    public void setTextview1(TextView textview1) {
        this.textview1 = textview1;
    }

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

    public TextView getTextView4() {
        return textView4;
    }

    public void setTextView4(TextView textView4) {
        this.textView4 = textView4;
    }

    public TextView getTextView5() {
        return textView5;
    }

    public void setTextView5(TextView textView5) {
        this.textView5 = textView5;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
