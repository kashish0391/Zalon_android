package com.zalonstyles.app.zalon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Created by KASHISH on 27-07-2016.
 */
public class ReportMain extends AppCompatActivity {
    private BarChart barChart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_main);
        barChart =(BarChart) findViewById(R.id.barchart);
        ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();
        barEntries.add(new BarEntry(44f,0));
        barEntries.add(new BarEntry(54f,1));
        barEntries.add(new BarEntry(64f,2));
        barEntries.add(new BarEntry(74f,3));
        barEntries.add(new BarEntry(84f,4));
        BarDataSet barDataSet = new BarDataSet(barEntries,"Months");

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("may");
        theDates.add("june");
        theDates.add("july");
        theDates.add("august");
        theDates.add("september");
        BarData theData =  new BarData(theDates,barDataSet);
        barChart.setData(theData);
        //barChart.setTouchEnabled(true);
     // barChart.setScaleEnabled(true);
       // barChart.setDragEnabled(true);
        barChart.animateXY(2000,2000);
        barChart.invalidate();


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
                Intent intent = new Intent(ReportMain.this,ServicesMain.class);
                startActivity(intent);

                return true;

            case R.id.bill:
                Intent intent1 = new Intent(ReportMain.this,BillingMain.class);
                startActivity(intent1);
                return true;
            case R.id.mar:
                Intent intent2 = new Intent(ReportMain.this,MarketingMain.class);
                startActivity(intent2);
                return true;
            case R.id.set:
                Intent intent3 = new Intent(ReportMain.this,SettingsMain.class);
                startActivity(intent3);
                return true;
            case R.id.rep:
                Intent intent4 = new Intent(ReportMain.this,ReportMain.class);
                startActivity(intent4);
                return true;
            case R.id.cal:
                Intent intent5 = new Intent(ReportMain.this,CalendarMain.class);
                startActivity(intent5);
                return true;
            case R.id.acccounting:
                Intent intent6 = new Intent(ReportMain.this,AccountingMain.class);
                startActivity(intent6);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

