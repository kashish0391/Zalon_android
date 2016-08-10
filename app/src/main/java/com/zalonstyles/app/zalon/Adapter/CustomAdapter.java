package com.zalonstyles.app.zalon.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zalonstyles.app.zalon.EmployeeManagement;
import com.zalonstyles.app.zalon.GiftVouchers;
import com.zalonstyles.app.zalon.Inventory;
import com.zalonstyles.app.zalon.Profile;
import com.zalonstyles.app.zalon.R;
import com.zalonstyles.app.zalon.SellableInventory;

/**
 * Created by KASHISH on 28-07-2016.
 */
public class CustomAdapter extends BaseAdapter {
    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter(Activity SettingsMain, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=SettingsMain;
        imageId=prgmImages;
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
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.customlistsettings1, null);
        holder.tv=(TextView) rowView.findViewById(R.id.tv1);
        holder.img=(ImageView) rowView.findViewById(R.id.iv1);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (position == 0) {
                    v.getContext().startActivity(new Intent(context, EmployeeManagement.class));

                }

                if (position == 1) {
                    v.getContext().startActivity(new Intent(context, Inventory.class));

                }

                if (position == 2) {
                    v.getContext().startActivity(new Intent(context, SellableInventory.class));

                }

                if (position == 3) {
                    v.getContext().startActivity(new Intent(context, Profile.class));
                }

                    if (position == 4) {
                    v.getContext().startActivity(new Intent(context, GiftVouchers.class));

                }

            }
        });
        return rowView;
    }

}
