package com.zalonstyles.app.zalon.Model;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by KASHISH on 23-08-2016.
 */
public class Viewholder4 {

    private TextView editText1;
    private EditText editText2;
    private EditText editText3;

    public TextView getEditText1() {
        return editText1;
    }

    public void setEditText1(TextView editText1) {
        this.editText1 = editText1;
    }

    public Viewholder4(TextView editText1, EditText editText2, EditText editText3)
    {
        this.editText1 = editText1;
        this.editText2 = editText2;
        this.editText3 = editText3;


    }

    public EditText getEditText3() {
        return editText3;
    }

    public void setEditText3(EditText editText3) {
        this.editText3 = editText3;
    }

    public EditText getEditText2() {
        return editText2;
    }

    public void setEditText2(EditText editText2) {
        this.editText2 = editText2;
    }



}
