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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KASHISH on 27-07-2016.
 */
public class BillingMain extends AppCompatActivity {
    private Spinner Category;
    private TextView Billno;
    private TextView Date;
    private EditText customerName;
    private EditText customerMob;
    private Spinner service;
    private Spinner stylist;
    private Spinner items;
    private Spinner promocode;
    private Spinner Discounts;
    private Spinner quantity;
    private RadioButton r1;
    private RadioButton r2;
    private EditText total;
    private String Spinnerservice;
    private String Spinnerstylist;
    private String Spinneritems;
    private String Spinnerpromocode;
    private String Spinnerdiscounts;
    private String Spinnerquantity;
    private String Spinnercategory;
    private   ArrayAdapter<String> arrayadapter3;
    private   ArrayAdapter<String> arrayadapter4;
    private   ArrayAdapter<String> arrayadapter5;
    private Button addbill;

    private ListView billlv;
    String[] spinnerItems1 = new String[]{"1","2","3","4","5","6","7","8","9","10"};
    String[] spinnerItems = new String[]{"Select Category","Body","Hair","Nail","Face","Hair Removal","Massage","Product"};

    List<String> stringlist;
    ArrayAdapter<String> arrayadapter;
    List<String> stringlist1;
    ArrayAdapter<String> arrayadapter1;
    private ArrayList<String> servicespin;
    private ArrayList<String> servicespin1;
    private ArrayList<String> servicespin2;
    private static String url="http://52.41.72.46:8080/billing/get_bill_info";








    public  void servicenetworking1(int serviceid,String URL){

        service.setAdapter(arrayadapter4=new ArrayAdapter<String>(BillingMain.this, android.R.layout.simple_spinner_item, servicespin1));
        items.setAdapter(null);

        servicespin1.clear();
        service.setAdapter(null);
        arrayadapter4.notifyDataSetChanged();


        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        final JSONObject params = new JSONObject();
        try {
            params.put("service_id",serviceid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            params.put("access_token", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes1",response);
                        JSONObject jobject = null;
                        try {
                            jobject = new JSONObject(response);

                            JSONArray payload = jobject.getJSONArray("services");
                            for (int i = 0; i < payload.length(); i++) {
                                try{
                                    JSONObject obj = payload.getJSONObject(i);
                                    servicespin1.add(obj.getString("category_name"));
//                                    Log.v("logdata",servicespin.get(i));

                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        service.setAdapter(arrayadapter4=new ArrayAdapter<String>(BillingMain.this, android.R.layout.simple_spinner_item, servicespin1));
                        arrayadapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Spinnerservice = service.getSelectedItem().toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                Spinnerservice ="";

                            }
                        });

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billin_main);
        Billno =(TextView) findViewById(R.id.billno);
        Date = (TextView) findViewById(R.id.billdate);
        customerName = (EditText)findViewById(R.id.billname);
        customerMob = (EditText)findViewById(R.id.billmob);
        service = (Spinner)findViewById(R.id.billservice);
        stylist = (Spinner)findViewById(R.id.billstylist);
        items = (Spinner)findViewById(R.id.billitem);
        promocode = (Spinner)findViewById(R.id.billpromo);
        Discounts = (Spinner)findViewById(R.id.billdiscounts);
        quantity = (Spinner) findViewById(R.id.billquantity);
        total = (EditText) findViewById(R.id.billtotal);
        billlv = (ListView)findViewById(R.id.billlv);
        Category = (Spinner) findViewById(R.id.billcategory);
        stringlist = new ArrayList<>(Arrays.asList(spinnerItems1));
        stringlist1 = new ArrayList<>(Arrays.asList(spinnerItems));
        servicespin = new ArrayList<String>();
        servicespin1 = new ArrayList<String>();
        servicespin2 = new ArrayList<String>();
        r1 = (RadioButton) findViewById(R.id.billradiobtn0);
        r2 = (RadioButton) findViewById(R.id.billradiobtn01);
        addbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
                final JSONObject params = new JSONObject();



                try {
                    params.put("access_token", value);
                    params.put("category",Spinnercategory);
                    params.put("Service",Spinnerservice);
                    params.put("item",Spinneritems);
                    params.put("stylist",Spinnerstylist);
                    params.put("Discounts",Spinnerdiscounts);
                    params.put("promocode",Spinnerpromocode);
                    params.put("quantity",Spinnerquantity);
                    String gender;
                    if(r1.isChecked()){
                        gender = "male";
                    }else if (r2.isChecked()){
                        gender="female";
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>(){

                            @Override
                            public void onResponse(String response) {




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
        });
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r2.setChecked(false);
                r1.setChecked(true);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r1.setChecked(false);
                r2.setChecked(true);
            }
        });



        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        final JSONObject params = new JSONObject();

        try {
            params.put("access_token", value);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes1",response);
                        JSONObject jobject = null;
                        try {
                            jobject = new JSONObject(response);
                            Billno.setText(jobject.getString("invoice_number"));
                            Date.setText(jobject.getString("date"));
                            JSONArray payload = jobject.getJSONArray("categories");
                            for (int i = 0; i < payload.length(); i++) {
                                try{
                                    JSONObject obj = payload.getJSONObject(i);
                                    servicespin.add(obj.getString("category"));
                                    Log.v("logdata",servicespin.get(i));

                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        stylist.setAdapter(arrayadapter3=new ArrayAdapter<String>(BillingMain.this, android.R.layout.simple_spinner_dropdown_item, servicespin));
                        arrayadapter3.setDropDownViewResource(R.layout.textviewspinner);

                        stylist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Spinnerstylist = stylist.getSelectedItem().toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                Spinnerstylist ="";

                            }
                        });


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

        arrayadapter1 = new ArrayAdapter<String>(BillingMain.this,android.R.layout.simple_spinner_item,stringlist1 );

        arrayadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Category.setAdapter(arrayadapter1);
        Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinnercategory = Category.getSelectedItem().toString();
                if(Spinnercategory.equals("Body")){



                    servicenetworking1(1,"http://52.41.72.46:8080/billing/get_services");
                }if(Spinnercategory.equals("Hair")){

                    servicenetworking1(2,"http://52.41.72.46:8080/billing/get_services");

                }if(Spinnercategory.equals("Nail")){


                    servicenetworking1(3,"http://52.41.72.46:8080/billing/get_services");

                }if(Spinnercategory.equals("Face")){

                    servicenetworking1(4,"http://52.41.72.46:8080/billing/get_services");

                }if(Spinnercategory.equals("Hair Removal")){


                    servicenetworking1(5,"http://52.41.72.46:8080/billing/get_services");

                }if(Spinnercategory.equals("Massage")){


                    servicenetworking1(6,"http://52.41.72.46:8080/billing/get_services");

                }if(Spinnercategory.equals("Product")){



                    SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
                    items.setAdapter(arrayadapter5=new ArrayAdapter<String>(BillingMain.this, android.R.layout.simple_spinner_item, servicespin2));
                    service.setAdapter(null);
                    servicespin2.clear();
                    items.setAdapter(null);
                    arrayadapter5.notifyDataSetChanged();
                    String serviceid = "7";
                    final JSONObject params = new JSONObject();
                    try {
                        params.put("service_id",serviceid);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        params.put("access_token", value);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://52.41.72.46:8080/billing/get_services",
                            new Response.Listener<String>(){

                                @Override
                                public void onResponse(String response) {

                                    Log.v("updateUPVolleyRes1",response);
                                    JSONObject jobject = null;
                                    try {
                                        jobject = new JSONObject(response);

                                        JSONArray payload = jobject.getJSONArray("items");
                                        for (int i = 0; i < payload.length(); i++) {
                                            try{
                                                JSONObject obj = payload.getJSONObject(i);
                                                servicespin2.add(obj.getString("item_name"));
                                                 Log.v("logdata11",servicespin2.get(i));

                                            }catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    items.setAdapter(arrayadapter5=new ArrayAdapter<String>(BillingMain.this, android.R.layout.simple_spinner_item, servicespin2));
                                    arrayadapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    items.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            Spinneritems = items.getSelectedItem().toString();
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {
                                            Spinneritems ="";

                                        }
                                    });

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






            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Spinnercategory ="1";

            }
        });
        arrayadapter1 = new ArrayAdapter<String>(BillingMain.this,android.R.layout.simple_spinner_item,stringlist );

        arrayadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        quantity.setAdapter(arrayadapter1);
        quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinnerquantity = quantity.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Spinnerquantity ="1";

            }
        });
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
                Intent intent = new Intent(BillingMain.this,ServicesMain.class);
                startActivity(intent);

                return true;

            case R.id.bill:
                Intent intent1 = new Intent(BillingMain.this,BillingMain.class);
                startActivity(intent1);
                return true;
            case R.id.mar:
                Intent intent2 = new Intent(BillingMain.this,MarketingMain.class);
                startActivity(intent2);
                return true;
            case R.id.set:
                Intent intent3 = new Intent(BillingMain.this,SettingsMain.class);
                startActivity(intent3);
                return true;
            case R.id.rep:
                Intent intent4 = new Intent(BillingMain.this,ReportMain.class);
                startActivity(intent4);
                return true;
            case R.id.cal:
                Intent intent5 = new Intent(BillingMain.this,CalendarMain.class);
                startActivity(intent5);
                return true;
            case R.id.acccounting:
                Intent intent6 = new Intent(BillingMain.this,AccountingMain.class);
                startActivity(intent6);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

