package com.zalonstyles.app.zalon.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zalonstyles.app.zalon.R;

/**
 * Created by KASHISH on 29-07-2016.
 */
public class CustomAdapter1 extends BaseAdapter {
    String [] result;
    Context context;
    int [] imageId1;
    private static LayoutInflater inflater=null;
    public CustomAdapter1 (Activity SettingsMain, String[] prgmNameList1, int[] prgmImages1) {
        // TODO Auto-generated constructor stub
        result=prgmNameList1;
        context=SettingsMain;
        imageId1=prgmImages1;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv1;
        ImageView img1;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.customlistsettings2, null);
        holder.tv1=(TextView) rowView.findViewById(R.id.tv2);
        holder.img1=(ImageView) rowView.findViewById(R.id.iv2);
        holder.tv1.setText(result[position]);
        holder.img1.setImageResource(imageId1[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}

