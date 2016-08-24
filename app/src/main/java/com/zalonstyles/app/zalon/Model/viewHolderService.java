package com.zalonstyles.app.zalon.Model;

import android.widget.TextView;

/**
 * Created by KASHISH on 16-08-2016.
 */
public class viewHolderService {

        private TextView textView;

        public viewHolderService(TextView textView)
        {
            this.textView = textView;

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


