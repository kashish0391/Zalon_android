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
public class CustomAdapter2 extends BaseAdapter {
    String [] result;
    Context context;
    int [] imageId1;
    private static LayoutInflater inflater=null;
    public CustomAdapter2 (Activity SettingsMain, String[] prgmNameList2, int[] prgmImages2) {
        // TODO Auto-generated constructor stub
        result=prgmNameList2;
        context=SettingsMain;
        imageId1=prgmImages2;
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
        TextView tv2;
        ImageView img2;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.customlistsettings3, null);
        holder.tv2=(TextView) rowView.findViewById(R.id.tv3);
        holder.img2=(ImageView) rowView.findViewById(R.id.iv3);
        holder.tv2.setText(result[position]);
        holder.img2.setImageResource(imageId1[position]);
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