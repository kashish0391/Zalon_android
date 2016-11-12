package com.zalonstyles.app.zalon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.zalonstyles.app.zalon.Adapter.CustomAdapter;
import com.zalonstyles.app.zalon.Adapter.CustomAdapter1;
import com.zalonstyles.app.zalon.Adapter.CustomAdapter2;

import java.util.ArrayList;

/**
 * Created by KASHISH on 27-07-2016.
 */
public class SettingsMain extends AppCompatActivity {
    ListView lv;
    ListView lv1;
    ListView lv2;
    Context context;

    ArrayList prgmName;
    public static int [] prgmImages={R.drawable.zalon,R.drawable.zalon,R.drawable.zalon,R.drawable.zalon,R.drawable.zalon,R.drawable.zalon};
    public static String [] prgmNameList={"Employee Management","Inventory","Sellable","Profile","Loyalty Points","Business Hours"};
    ArrayList prgmName1;
    public static int [] prgmImages1={R.drawable.zalon,R.drawable.zalon,};
    public static String [] prgmNameList1={"Customer","Billings"};
    ArrayList prgmName2;
    public static int [] prgmImages2={R.drawable.zalon,R.drawable.zalon,R.drawable.zalon,R.drawable.zalon};
    public static String [] prgmNameList2={"Change Password","Policy","About","Logout"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_main);

        context=this;

        lv=(ListView) findViewById(R.id.lvSettings);
        lv.setAdapter(new CustomAdapter(this, prgmNameList,prgmImages));
        lv1=(ListView) findViewById(R.id.lvSettings1);
        lv1.setAdapter(new CustomAdapter1(this,prgmNameList1,prgmImages1));
        lv2=(ListView) findViewById(R.id.lvSettings2);
        lv2.setAdapter(new CustomAdapter2(this,prgmNameList2,prgmImages2));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.service:
                Intent intent = new Intent(SettingsMain.this,ServicesMain.class);
                startActivity(intent);

                return true;

            case R.id.bill:
                Intent intent1 = new Intent(SettingsMain.this,BillingMain.class);
                startActivity(intent1);
                return true;
            case R.id.mar:
                Intent intent2 = new Intent(SettingsMain.this,MarketingMain.class);
                startActivity(intent2);
                return true;
            case R.id.set:
                Intent intent3 = new Intent(SettingsMain.this,SettingsMain.class);
                startActivity(intent3);
                return true;
            case R.id.rep:
                Intent intent4 = new Intent(SettingsMain.this,ReportMain.class);
                startActivity(intent4);
                return true;
            case R.id.cal:
                Intent intent5 = new Intent(SettingsMain.this,CalendarMain.class);
                startActivity(intent5);
                return true;
            case R.id.acccounting:
                Intent intent6 = new Intent(SettingsMain.this,AccountingMain.class);
                startActivity(intent6);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

