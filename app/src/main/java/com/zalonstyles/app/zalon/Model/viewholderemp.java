package com.zalonstyles.app.zalon.Model;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KASHISH on 01-09-2016.
 */
public class viewholderemp {
    private TextView textView;
    private TextView textView1;
    private ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public viewholderemp(TextView textView, TextView textView1,ImageView imageView){
        this.textView=textView;
        this.textView1=textView1;
        this.imageView = imageView;
    }

    public TextView getTextView1() {
        return textView1;
    }

    public void setTextView1(TextView textView1) {
        this.textView1 = textView1;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
