package com.zalonstyles.app.zalon;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zalonstyles.app.zalon.Model.billlist;
import com.zalonstyles.app.zalon.Model.viewholderbill;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.value;
import static com.zalonstyles.app.zalon.R.id.set;

/**
 * Created by KASHISH on 02-08-2016.
 */
public class BillingMain extends AppCompatActivity  {
    private Button srch;
    private TextView Billno;
    private TextView Date;
    private EditText customerName;
    private EditText customerMob;
    private Spinner stylist;
    private Spinner promocode;
    private Spinner paymentmthd;
    private Spinner Discounts;
    private TextView service;
    private EditText total;
    private Button btnsrch;
    private ListView billlv;
    private String gender = null;
    private String quantity = "1";
    private ImageButton one;
    private ImageButton two;
    private ImageButton three;
    private ImageButton four;
    private ImageButton five;
    private Button printbill;

    ArrayAdapter<String> arrayadapter11;
    ArrayAdapter<String> arrayadapter12;
    private String Spinnerstylist;
    private String Spinnerpromocode;
    private String Spinnerpayment;
    protected String type;

    private String Spinnerdiscounts;
    private CheckBox chk;
    private Button addbill;
    private Button genbill;
    private ImageButton male;
    private ImageButton female;

    private   ArrayAdapter<String> arrayadapter3;
    private ArrayAdapter<billlist> listAdapter;
    List<String> stringlist1;


    private TextView txtloyal;
    String[] spinnerItems01 = new String[]{"No Discount","5%","10%","15%","20%","25%","30%","35%","40%","45%","50%"};
    private   ArrayAdapter<String> arrayadapter;
    private List<com.zalonstyles.app.zalon.Model.billlist> billList = new ArrayList<>();

    List<String> stringlist01;
    private static String url="http://zalonstyle.in:8080/billing/get_bill_info";
    private ArrayList<String> servicespin;
    private ArrayList<String> servicespin1;
    private ArrayList<String> servicespin2;
    private ArrayList<String> servicespin3;
    private ArrayList<String> servicespin4;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billingnew);
        srch = (Button) findViewById(R.id.btnsrchserv);
        btnsrch = ( Button) findViewById(R.id.btnsrch);
        service = (TextView) findViewById(R.id.billserv);
        male = (ImageButton) findViewById(R.id.billimagemale);
        female = (ImageButton) findViewById(R.id.billimagefemale);
        chk =(CheckBox)findViewById(R.id.chkbill);
        Billno =(TextView) findViewById(R.id.billno);
        Date = (TextView) findViewById(R.id.billdate);
        customerName = (EditText)findViewById(R.id.billname);
        customerMob = (EditText)findViewById(R.id.billmob);
        stylist = (Spinner)findViewById(R.id.billstylist);
        promocode = (Spinner)findViewById(R.id.billpromo);
        paymentmthd =(Spinner)findViewById(R.id.billpayment) ;
        Discounts = (Spinner)findViewById(R.id.billdiscounts);
        billlv = (ListView)findViewById(R.id.billlv);
        stringlist01 = new ArrayList<>(Arrays.asList(spinnerItems01));
        servicespin = new ArrayList<String>();
        addbill = (Button)findViewById(R.id.billaddbill);
        one = ( ImageButton) findViewById(R.id.billquan1);
        two = ( ImageButton) findViewById(R.id.billquan2);
        three = ( ImageButton) findViewById(R.id.billquan3);
        four = ( ImageButton) findViewById(R.id.billquan4);
        five = ( ImageButton) findViewById(R.id.billquan5);
        genbill = (Button)findViewById(R.id.billgen);
        printbill = (Button)findViewById(R.id.printbill);





//        Category = (Spinner) findViewById(R.id.billcategory);
//        stringlist = new ArrayList<>(Arrays.asList(spinnerItems1));
//        stringlist01 = new ArrayList<>(Arrays.asList(spinnerItems01));
        final TextView subtotal=(TextView)findViewById(R.id.tax);
        final TextView disco=(TextView)findViewById(R.id.tax1);
        final TextView stax=(TextView)findViewById(R.id.tax2);
        final TextView vat=(TextView)findViewById(R.id.tax3);
        final TextView total=(TextView)findViewById(R.id.tax4);
        final TextView lpoints=(TextView)findViewById(R.id.tax5);

        btnsrch = (Button) findViewById(R.id.btnsrch);
        txtloyal=(TextView) findViewById(R.id.txtloyalty);

        srch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillingMain.this,searchservice.class);
                startActivityForResult(intent, 3);
            }
        });
        btnsrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillingMain.this,Search.class);
                startActivityForResult(intent, 2);
            }
        });
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender ="male";
                male.setBackgroundResource(R.drawable.male1);
                female.setBackgroundResource(R.drawable.female);

            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender ="male";
                male.setBackgroundResource(R.drawable.male);
                female.setBackgroundResource(R.drawable.female1);

            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = "1";
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = "2";
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = "3";
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = "4";
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = "5";
            }
        });




        servicespin = new ArrayList<String>();
        servicespin1 = new ArrayList<String>();
        servicespin2 = new ArrayList<String>();
        servicespin3 = new ArrayList<String>();
        servicespin4 = new ArrayList<String>();
        arrayadapter11 = new ArrayAdapter<String>(BillingMain.this,android.R.layout.simple_spinner_item,servicespin3 );

        arrayadapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        promocode.setAdapter(arrayadapter11);
        promocode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinnerpromocode = promocode.getSelectedItem().toString();
                final JSONObject params = new JSONObject();
                try {
                    params.put("invoice",Billno.getText().toString());
                    params.put("promo",Spinnerpromocode);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    params.put("access_token", value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/billing/applyPromoCode",
                        new Response.Listener<String>(){

                            @Override
                            public void onResponse(String response) {
                                try {
                                    if (new JSONObject(response).getString("status").equals("success")) {
                                        Log.v("updat011",response);
                                        JSONObject jobject = null;
                                        try {
                                            jobject = new JSONObject(response);

                                            JSONArray payload = jobject.getJSONArray("services");
                                            for (int i = 0; i < payload.length(); i++) {
                                                try{
                                                    JSONObject obj = payload.getJSONObject(i);
                                                    subtotal.setText(obj.getString("sub_total"));
                                                    disco.setText(obj.getString("discount"));
                                                    stax.setText(obj.getString("service_tax"));
                                                    vat.setText(obj.getString("vat"));
                                                    lpoints.setText(obj.getString("points"));


                                                }catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }else {
                                        Toast.makeText(getApplicationContext(), " Not applicable on your order", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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
                Spinnerpromocode ="";

            }
        });
        arrayadapter12 = new ArrayAdapter<String>(BillingMain.this,android.R.layout.simple_spinner_item,servicespin4 );

        arrayadapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        paymentmthd.setAdapter(arrayadapter12);
        paymentmthd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinnerpayment = paymentmthd.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Spinnerpayment ="";

            }
        });
        printbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final JSONObject params = new JSONObject();
                try {
                    params.put("invoice",Billno.getText().toString());
                    params.put("payment_method",Spinnerpayment);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/billing/applyPaymentMethod",
                        new Response.Listener<String>(){

                            @Override
                            public void onResponse(String response) {
                                try {
                                    if (new JSONObject(response).getString("status").equals("success")) {
                                        Intent intent = new Intent(BillingMain.this,BillingMain.class);
                                        startActivity(intent);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

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
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));

            String value1 = extras.getString("eventid");
            Log.v("valtest",value1);
            final JSONObject params = new JSONObject();
            try {
                params.put("event_id",value1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                params.put("access_token", value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/calendar/getBillingData",
                    new Response.Listener<String>(){

                        @Override
                        public void onResponse(String response) {

                            Log.v("test101",response);

                            try {
                                JSONObject jobject = new JSONObject(response);
                                JSONArray payload = jobject.getJSONArray("data");
                                Log.e("payloaddata", String.valueOf(payload));
                                for (int i = 0; i < payload.length(); i++) {
                                    try{
                                        JSONObject obj = payload.getJSONObject(i);
                                        customerName.setText(obj.getString("customer_name"));
                                        customerMob.setText(obj.getString("mobile"));
                                        service.setText((obj.getString("service")));
                                        txtloyal.setText(obj.getString("points"));







                                    }catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

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


        genbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAdapter = new BillingMain.billArrayAdapter(BillingMain.this,R.layout.custombilling, billList);
                billlv.setAdapter(listAdapter);
                String redem;
                if (chk.isChecked()){
                    redem = "true";
                }else {
                    redem = "false";
                }

                String name = customerName.getText().toString();
                String mob = customerMob.getText().toString();
                String invoice = Billno.getText().toString();

                JSONArray ja = new JSONArray();
                for (int i = 0; i < listAdapter.getCount(); i++) {
                    billlist massage = listAdapter.getItem(i);

                    JSONObject jo = new JSONObject();
                    try {
                        jo.put("description",  massage.getDescription());
                        jo.put("stylist", massage.getStylist());
                        jo.put("quantity", massage.getQty());
                        jo.put("rate", massage.getRate());
                        jo.put("amount", massage.getAmount());
                        jo.put("discounts", massage.getDiscounts());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ja.put(jo);
                }


                SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));

                final JSONObject params = new JSONObject();

                try {
                    params.put("access_token", value);
                    params.put("customer_name",name);
                    params.put("invoice",invoice);
                    params.put("info",ja);
                    params.put("mobile", mob);
                    params.put("redem",redem);
                } catch (JSONException e) {
                    e.printStackTrace();
                }






                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/billing/generate_bill",
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Log.v("respodata", response);


                                JSONObject jobject = null;

                                try {
                                    jobject = new JSONObject(response);
                                    total.setText(jobject.getString("total"));
                                    JSONArray payload2 = jobject.getJSONArray("payment_methods");
                                    for (int i = 0; i < payload2.length(); i++) {
                                        try {
                                            JSONObject obj = payload2.getJSONObject(i);
                                            servicespin4.add(obj.getString("payment_method"));



//                                                Log.v("spinnydata",servicespin3.get(i));

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }arrayadapter12.notifyDataSetChanged();

                                    }
                                    JSONArray payload1 = jobject.getJSONArray("campaign");
                                    for (int i = 0; i < payload1.length(); i++) {
                                        try {
                                            JSONObject obj = payload1.getJSONObject(i);
                                            servicespin3.add(obj.getString("campaign_name"));



                                            Log.v("spinnydata",servicespin3.get(i));

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }arrayadapter11.notifyDataSetChanged();

                                    }

                                    JSONArray payload = jobject.getJSONArray("services");
                                    for (int i = 0; i < payload.length(); i++) {
                                        try {
                                            JSONObject obj = payload.getJSONObject(i);
                                            subtotal.setText(obj.getString("sub_total"));
                                            disco.setText(obj.getString("discount"));
                                            stax.setText(obj.getString("service_tax"));
                                            vat.setText(obj.getString("vat"));
                                            lpoints.setText(obj.getString("points"));


//                                    Log.v("logdata",servicespin.get(i));

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }


                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("updateUPVolleyErr", error.toString());

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params1 = new HashMap<String, String>();

                        params1.put("payload", params.toString());

                        Log.v("sellableParams", params1.toString());

                        return params1;

                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(BillingMain.this);
                requestQueue.add(stringRequest);




            }


        });


        arrayadapter = new ArrayAdapter<String>(BillingMain.this,android.R.layout.simple_spinner_item,stringlist01 );

        arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Discounts.setAdapter(arrayadapter);
        Discounts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinnerdiscounts = Discounts.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Spinnerdiscounts ="5%";

            }
        });

        addbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
                final JSONObject params = new JSONObject();



                try {
                    params.put("access_token", value);
                    params.put("stylist",Spinnerstylist);
                    params.put("discounts",Spinnerdiscounts);
                    params.put("gender",gender);
                    params.put("category",type);
                    params.put("quantity",quantity);
                    params.put("Service",service.getText().toString());
                    Log.v("checkquan",quantity);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/billing/calculate_bill",
                        new Response.Listener<String>(){

                            @Override
                            public void onResponse(String response) {
                                Log.v("RESPONSE#",response);

                                try {
                                    JSONObject jobject = new JSONObject(response);
                                    JSONObject obj = jobject.getJSONObject("list");


                                    com.zalonstyles.app.zalon.Model.billlist service = new com.zalonstyles.app.zalon.Model.billlist();


//                                           service.setSno(String.valueOf(counter));
                                    service.setDescription(String.valueOf(obj.getString("description")));
                                    service.setStylist(String.valueOf(obj.getString("stylist")));
                                    service.setQty(String.valueOf(obj.getString("quantity")));
                                    service.setRate(String.valueOf(obj.getString("rate")));
                                    service.setAmount(String.valueOf(obj.getString("amount")));
                                    service.setDiscounts(String.valueOf(obj.getString("discounts")));

                                    billList.add(service);
                                    Log.v("respo111", String.valueOf(billList.get(0)));









                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }listAdapter.notifyDataSetChanged();



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
                listAdapter = new BillingMain.billArrayAdapter(BillingMain.this,R.layout.custombilling, billList);
                billlv.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();


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

                        Log.v("update211",response);
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


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            if (data != null)
            {
                String name=data.getStringExtra("name");
                String mob = data.getStringExtra("mob");
                String gender = data.getStringExtra("gender");
                String points = data.getStringExtra("points");
                txtloyal.setText(String.valueOf(points));
                customerName.setText(String.valueOf(name));
                customerMob.setText(String.valueOf(mob));
                if(gender.equals("male")){
                    male.setBackgroundResource(R.drawable.male1);
                    female.setBackgroundResource(R.drawable.female);

                }else {
                    male.setBackgroundResource(R.drawable.male);
                    female.setBackgroundResource(R.drawable.female1);
                }
            }





        }else if (requestCode==3){
            if (data != null)
            {
                String name=data.getStringExtra("name");
                String id = data.getStringExtra("id");
                type = data.getStringExtra("type");
                service.setText(String.valueOf(name));

                Log.v("checkval",type);

            }

       }
// else if (requestCode==5){
//            if (data != null)
//            {
//                String eventId=data.getStringExtra("eventid");
//                service.setText(String.valueOf(eventId));
//
//
//                Log.v("checkval1",eventId);
//
//            }
//
//        }
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
            case set:
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


    private static class billArrayAdapter extends ArrayAdapter<com.zalonstyles.app.zalon.Model.billlist>
    {

        private LayoutInflater inflater;
        private List<com.zalonstyles.app.zalon.Model.billlist> billlist;
        private Context context;

        public billArrayAdapter(Context context, int resourceId, List<com.zalonstyles.app.zalon.Model.billlist> billlist)
        {
            super(context, R.layout.custombilling, billlist);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.billlist = billlist;
            this.context = context;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            // SERVICES to display
            final com.zalonstyles.app.zalon.Model.billlist sinventory = (com.zalonstyles.app.zalon.Model.billlist) this.getItem(position);

            // The child views in each row.
            TextView textView1;
            TextView textView2;
            TextView textView3;
            TextView textView4;
            TextView textView5;
            TextView textView6;
            Button button;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.custombilling,null );

                // Find the child views.
                textView1 = (TextView) convertView
                        .findViewById(R.id.custombilltv);
                textView2 = (TextView) convertView.findViewById(R.id.custombilltv1);
                textView3 = (TextView) convertView.findViewById(R.id.custombilltv2);
                textView4 = (TextView) convertView.findViewById(R.id.custombilltv3);
                textView5 = (TextView)convertView.findViewById(R.id.custombilltv4);
                textView6 = (TextView)convertView.findViewById(R.id.custombilltv5);
                button = (Button) convertView.findViewById(R.id.custombillbtn);

                // Optimization: Tag the row with it's child views, so we don't
                // have to
                // call findViewById() later when we reuse the row.
                convertView.setTag(new viewholderbill(textView1, textView2,textView3,textView4,textView5,textView6,button));




                // If CheckBox is toggled, update the planet it is tagged with.

            }
            // Reuse existing row view
            else
            {notifyDataSetChanged();
                // Because we use a ViewHolder, we avoid having to call
                // findViewById().
                viewholderbill viewHolder = (viewholderbill) convertView
                        .getTag();
                textView1 = viewHolder.getTextView();
                textView2 = viewHolder.getTextview1();
                textView3 = viewHolder.getTextview2();
                textView4 = viewHolder.getTextView3();
                textView5 = viewHolder.getTextView4();
                textView6 = viewHolder.getTextView5();

                button = viewHolder.getButton();
                button.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        Button cb = (Button) v;
                        com.zalonstyles.app.zalon.Model.billlist sinventory = (com.zalonstyles.app.zalon.Model.billlist) cb.getTag();
                        sinventory.setClicked(cb.isSelected());
                    }
                });
            }


            // Display Service data
            textView1.setText(String.valueOf(position+1));
            textView2.setText(String.valueOf(sinventory.getDescription()));
            textView3.setText(String.valueOf(sinventory.getStylist()));
            textView4.setText(String.valueOf(sinventory.getQty()));
            textView5.setText(String.valueOf(sinventory.getRate()));
            textView6.setText(String.valueOf(sinventory.getAmount()));
            button.setTag(sinventory);
            button.setSelected(sinventory.isClicked());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {


                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked
                                    Button cb = (Button) v;
                                    com.zalonstyles.app.zalon.Model.billlist inventory = (com.zalonstyles.app.zalon.Model.billlist) cb.getTag();
                                    inventory.setClicked(cb.isSelected());




                                    billlist.remove(position);
                                    notifyDataSetChanged();



                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    // No button clicked //
                                    // do nothing
                                    break; } } };
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure?") .setPositiveButton("Yes", dialogClickListener) .setNegativeButton("No", dialogClickListener).show();






                }

            });

            return convertView;
        }

    }
}
