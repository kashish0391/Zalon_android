package com.zalonstyles.app.zalon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zalonstyles.app.zalon.R.id.service;

/**
 * Created by KASHISH on 27-07-2016.
 */
public class ReportMain extends AppCompatActivity implements View.OnClickListener{
    private BarChart barChart;
    private Spinner spinny;
    List<String> stringlist;
    private String spinnerValue="Daily";
    private Button sale;
    private Button client;
    private Button product;
    private Button services;
    private Button employee;
    private Button campaign;
    private int ide=1;


    ArrayAdapter<String> arrayadapter;

    private ArrayList<BarEntry> barEntries=new ArrayList<BarEntry>();
    ArrayList<String> theDates=new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_main);
        barChart =(BarChart) findViewById(R.id.barchart);
        spinny = (Spinner)findViewById(R.id.spinnyreports);
        stringlist = new ArrayList<>();
//        stringlist.add("Daily");
        sale = (Button) findViewById(R.id.reportsale);
        client = (Button) findViewById(R.id.reportclient);
        product = (Button) findViewById(R.id.reportproduct);
        services = (Button) findViewById(R.id.reportservice);
        employee = (Button) findViewById(R.id.reportemployee);
        campaign = (Button) findViewById(R.id.reportcampaign);
        sale.setOnClickListener(this);
        client.setOnClickListener(this);
        services.setOnClickListener(this);
        employee.setOnClickListener(this);
        campaign.setOnClickListener(this);
        product.setOnClickListener(this);




        theDates.clear();

        barEntries.clear();




        final JSONObject params = new JSONObject();
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        try {
            params.put("access_token", value);
            params.put("sales","Monthly");
            params.put("type",ide);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/stats/sales",
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes1",response);
                        theDates.clear();
                        barEntries.clear();
                        stringlist.clear();
                        arrayadapter.notifyDataSetChanged();

                        try {
                            JSONObject jobject = new JSONObject(response);
                            JSONArray payload = jobject.getJSONArray("stats");
                            Log.e("payloaddata", String.valueOf(payload));
                            for (int i = 0; i < payload.length(); i++) {
                                try{
                                    JSONObject obj = payload.getJSONObject(i);
                                    Log.e("test", String.valueOf(obj));
                                    barEntries.add(new BarEntry(Float.parseFloat(obj.getString("total")),i));
                                    theDates.add(obj.getString("axis"));
//                                    Log.e("checksum2", String.valueOf(theDates.get(i)));
//                                    Log.e("checksum3", String.valueOf(barEntries.get(i)));





                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            JSONArray payload1 = jobject.getJSONArray("spiner");
                            Log.e("payloaddata", String.valueOf(payload1));
                            for (int i = 0; i < payload1.length(); i++) {
                                try{
                                    JSONObject obj = payload1.getJSONObject(i);

                                    stringlist.add(obj.getString("name"));




                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }arrayadapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        BarDataSet barDataSet = new BarDataSet(barEntries,"Months");
//                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        BarData theData =  new BarData(theDates,barDataSet);


                        barChart.setData(theData);
                        barChart.setTouchEnabled(true);
                        barChart.setScaleEnabled(true);
                        barChart.setDragEnabled(true);
                        barChart.setDescription("Sales");
                        barChart.animateXY(2000,3000);
                        barChart.getAxisLeft().setDrawGridLines(false);
                        barChart.getXAxis().setDrawGridLines(false);
                        barChart.getAxisRight().setDrawGridLines(false);
                        barChart.fitScreen();
                        barChart.invalidate();
                        barChart.getXAxis().setLabelsToSkip(0);
                        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

                        barChart.getAxisLeft().setDrawLabels(false);


                    }

                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("updateUPVolleyErr", error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params1 = new HashMap<String, String>();

                params1.put("payload", params.toString());

                Log.v("updateUPVolleyParams6", params1.toString());

                return params1;

            }
        };


        requestQueue.add(stringRequest);
        BarDataSet barDataSet = new BarDataSet(barEntries,"Months");
        BarData theData =  new BarData(theDates,barDataSet);
        barChart.setData(theData);
        barChart.setTouchEnabled(true);
         barChart.setScaleEnabled(true);
         barChart.setDragEnabled(true);
        barChart.animateXY(2000,2000);
        barChart.invalidate();

        spinny.setAdapter(arrayadapter=new ArrayAdapter<String>(ReportMain.this, android.R.layout.simple_spinner_item, stringlist));
        arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinny.setAdapter(arrayadapter);
        spinny.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue = spinny.getSelectedItem().toString();
                theDates.clear();
                barEntries.clear();
                final JSONObject params = new JSONObject();
                SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
                try {
                    params.put("access_token", value);
                    params.put("sales",spinnerValue);
                    params.put("type",ide);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/stats/sales",
                        new Response.Listener<String>(){

                            @Override
                            public void onResponse(String response) {
                                theDates.clear();
                                barEntries.clear();
                                Log.v("updateUPVolleyRes1",response);

                                try {
                                    JSONObject jobject = new JSONObject(response);
                                    JSONArray payload = jobject.getJSONArray("stats");
                                    Log.e("payloaddata", String.valueOf(payload));
                                    for (int i = 0; i < payload.length(); i++) {
                                        try{
                                            JSONObject obj = payload.getJSONObject(i);
                                            Log.e("test", String.valueOf(obj));
                                            barEntries.add(new BarEntry(Float.parseFloat(obj.getString("total")),i));
                                            theDates.add(obj.getString("axis"));
//                                    Log.e("checksum2", String.valueOf(theDates.get(i)));
//                                    Log.e("checksum3", String.valueOf(barEntries.get(i)));





                                        }catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                BarDataSet barDataSet = new BarDataSet(barEntries,"Months");
//                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                                BarData theData =  new BarData(theDates,barDataSet);


                                barChart.setData(theData);
                                barChart.setTouchEnabled(true);
                                barChart.setScaleEnabled(true);
                                barChart.setDragEnabled(true);
                                barChart.setDescription("Sales");
                                barChart.animateXY(2000,3000);
                                barChart.getAxisLeft().setDrawGridLines(false);
                                barChart.getXAxis().setDrawGridLines(false);
                                barChart.getAxisRight().setDrawGridLines(false);
                                barChart.fitScreen();
                                barChart.invalidate();
                                barChart.getXAxis().setLabelsToSkip(0);
                                barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

                                barChart.getAxisLeft().setDrawLabels(false);


                            }

                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("updateUPVolleyErr", error.toString());

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params1 = new HashMap<String, String>();

                        params1.put("payload", params.toString());

                        Log.v("updateUPVolleyParams6", params1.toString());

                        return params1;

                    }
                };


                requestQueue.add(stringRequest);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });




    }



    public  void reportnetworking1(String spinnerValue1,  String URL,int ide){
//    spinnerValue = spinny.getSelectedItem().toString();
    theDates.clear();
    barEntries.clear();
      stringlist.clear();
     arrayadapter.notifyDataSetChanged();
    final JSONObject params = new JSONObject();
    SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
    try {
        params.put("access_token", value);
        params.put("sales",spinnerValue1);
        params.put("type",ide);

    } catch (JSONException e) {
        e.printStackTrace();
    }
    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
            new Response.Listener<String>(){

                @Override
                public void onResponse(String response) {
                    theDates.clear();
                    barEntries.clear();
                    Log.v("updateUPVolleyRes1",response);

                    try {
                        JSONObject jobject = new JSONObject(response);
                        JSONArray payload = jobject.getJSONArray("stats");
                        Log.e("payloaddata", String.valueOf(payload));
                        for (int i = 0; i < payload.length(); i++) {
                            try{
                                JSONObject obj = payload.getJSONObject(i);
                                Log.e("test", String.valueOf(obj));
                                barEntries.add(new BarEntry(Float.parseFloat(obj.getString("total")),i));
                                theDates.add(obj.getString("axis"));
//                                    Log.e("checksum2", String.valueOf(theDates.get(i)));
//                                    Log.e("checksum3", String.valueOf(barEntries.get(i)));





                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        JSONArray payload1 = jobject.getJSONArray("spiner");
                        Log.e("payloaddata", String.valueOf(payload1));
                        for (int i = 0; i < payload1.length(); i++) {
                            try{
                                JSONObject obj = payload1.getJSONObject(i);

                                stringlist.add(obj.getString("name"));




                            }catch (JSONException e) {
                                e.printStackTrace();
                            }arrayadapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntries,"Months");
//                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    BarData theData =  new BarData(theDates,barDataSet);


                    barChart.setData(theData);
                    barChart.setTouchEnabled(true);
                    barChart.setScaleEnabled(true);
                    barChart.setDragEnabled(true);
                    barChart.setDescription("Sales");
                    barChart.animateXY(2000,3000);
                    barChart.getAxisLeft().setDrawGridLines(false);
                    barChart.getXAxis().setDrawGridLines(false);
                    barChart.getAxisRight().setDrawGridLines(false);
                    barChart.fitScreen();
                    barChart.invalidate();
                    barChart.getXAxis().setLabelsToSkip(0);
                    barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

                    barChart.getAxisLeft().setDrawLabels(false);


                }

            }
            , new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.v("updateUPVolleyErr", error.toString());

        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params1 = new HashMap<String, String>();

            params1.put("payload", params.toString());

            Log.v("updateUPVolleyParams6", params1.toString());

            return params1;

        }
    };


    requestQueue.add(stringRequest);}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case service:
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.reportsale:
                stringlist.clear();
                arrayadapter.notifyDataSetChanged();
//                spinny.setAdapter(null);


                reportnetworking1("Monthly","http://zalonstyle.in:8080/stats/sales",1);
                ide = 1;
//                stringlist.add("Select");

                break;
            case R.id.reportclient:
                stringlist.clear();
//                spinny.setAdapter(null);

                arrayadapter.notifyDataSetChanged();


                reportnetworking1("Monthly Visits","http://zalonstyle.in:8080/stats/sales",2);
                ide =2;


                break;
            case R.id.reportproduct:
                stringlist.clear();
//                spinny.setAdapter(null);
                arrayadapter.notifyDataSetChanged();
                reportnetworking1("Top Suppliers","http://zalonstyle.in:8080/stats/sales",3);
                ide =3;


                break;
            case R.id.reportservice:
                stringlist.clear();
//                spinny.setAdapter(null);

                arrayadapter.notifyDataSetChanged();
                reportnetworking1("Body","http://zalonstyle.in:8080/stats/sales",4);
                ide =4;
                break;
            case R.id.reportemployee:
                stringlist.clear();
                arrayadapter.notifyDataSetChanged();
//                spinny.setAdapter(null);
                reportnetworking1("Performance","http://zalonstyle.in:8080/stats/sales",5);
                ide =5;
                break;
            case R.id.reportcampaign:


                break;
            case R.id.addservicebtn:



                break;


        }}
}

