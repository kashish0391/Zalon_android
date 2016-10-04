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
    private Button btnsrch;
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
    private   ArrayAdapter<String> arrayadapter6;
    List<String> stringlist01;


    private Button addbill;
    private Button genbill;
    private TextView txtloyal;

    private ListView billlv;
    String[] spinnerItems1 = new String[]{"1","2","3","4","5","6","7","8","9","10"};
    String[] spinnerItems01 = new String[]{"No Discount","5%","10%","15%","20%","25%","30%","35%","40%","45%","50%"};
    String[] spinnerItems = new String[]{"Select Category","Body","Hair","Nail","Face","Hair Removal","Massage","Product"};

    List<String> stringlist;
    ArrayAdapter<String> arrayadapter;
    List<String> stringlist1;
    ArrayAdapter<String> arrayadapter1;
    private ArrayList<String> servicespin;
    private ArrayList<String> servicespin1;
    private ArrayList<String> servicespin2;

    private static String url="http://zalonstyle.in:8080/billing/get_bill_info";
    private List<com.zalonstyles.app.zalon.Model.billlist> billList = new ArrayList<>();
    private ArrayAdapter<billlist> listAdapter;









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
        billlv = (ListView)findViewById(R.id.billlv);
        Category = (Spinner) findViewById(R.id.billcategory);
        stringlist = new ArrayList<>(Arrays.asList(spinnerItems1));
        stringlist01 = new ArrayList<>(Arrays.asList(spinnerItems01));
        final TextView subtotal=(TextView)findViewById(R.id.tax);
        final TextView disco=(TextView)findViewById(R.id.tax1);
        final TextView stax=(TextView)findViewById(R.id.tax2);
        final TextView vat=(TextView)findViewById(R.id.tax3);
        final TextView total=(TextView)findViewById(R.id.tax4);
        btnsrch = (Button) findViewById(R.id.btnsrch);
        txtloyal=(TextView) findViewById(R.id.txtloyalty);
        btnsrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillingMain.this,Search.class);
                startActivityForResult(intent, 2);
            }
        });




        genbill = (Button)findViewById(R.id.billgenerate);
    int counter = 1;

        stringlist1 = new ArrayList<>(Arrays.asList(spinnerItems));
        servicespin = new ArrayList<String>();
        servicespin1 = new ArrayList<String>();
        servicespin2 = new ArrayList<String>();


        genbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAdapter = new billArrayAdapter(BillingMain.this,R.layout.custombilling, billList);
                billlv.setAdapter(listAdapter);

                String name = customerName.getText().toString();
                String mob = customerMob.getText().toString();
                String invoice = Billno.getText().toString();

                JSONArray ja = new JSONArray();
                for (int i = 0; i < listAdapter.getCount(); i++) {
                    billlist massage = listAdapter.getItem(i);

                    JSONObject jo = new JSONObject();
                    try {
                        jo.put("description", massage.getDescription());
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

                                        JSONArray payload = jobject.getJSONArray("services");
                                        for (int i = 0; i < payload.length(); i++) {
                                            try {
                                                JSONObject obj = payload.getJSONObject(i);
                                                subtotal.setText(obj.getString("sub_total"));
                                                disco.setText(obj.getString("discount"));
                                                stax.setText(obj.getString("service_tax"));
                                                vat.setText(obj.getString("vat"));
                                                total.setText(obj.getString("total"));
//                                                txtloyal.setText(obj.getString("points"));

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
        r1 = (RadioButton) findViewById(R.id.billradiobtn0);
        r2 = (RadioButton) findViewById(R.id.billradiobtn01);
        addbill = (Button) findViewById(R.id.billaddbtn);
        addbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarketingMain.Helper.setListViewHeightBasedOnItems(billlv);



                SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
                final JSONObject params = new JSONObject();



                try {
                    params.put("access_token", value);
                    params.put("category",Spinnercategory);
                    params.put("Service",Spinnerservice);
                    params.put("item",Spinneritems);
                    params.put("stylist",Spinnerstylist);
                    params.put("discounts",Spinnerdiscounts);
                    params.put("promocode",Spinnerpromocode);
                    params.put("quantity",Spinnerquantity);
                    String gender = null;
                    if(r1.isChecked()){
                        gender = "male";
                    }else if (r2.isChecked()){
                        gender="female";
                    }
                    params.put("gender",gender);



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
                                            service.setDescription(obj.getString("description"));
                                            service.setStylist(obj.getString("stylist"));
                                            service.setQty(obj.getString("quantity"));
                                            service.setRate(obj.getString("rate"));
                                            service.setAmount(obj.getString("amount"));
                                            service.setDiscounts(obj.getString("discounts"));

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
                listAdapter = new billArrayAdapter(BillingMain.this,R.layout.custombilling, billList);
                billlv.setAdapter(listAdapter);
                MarketingMain.Helper.setListViewHeightBasedOnItems(billlv);


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



                    servicenetworking1(1,"http://zalonstyle.in:8080/billing/get_services");
                }if(Spinnercategory.equals("Hair")){

                    servicenetworking1(2,"http://zalonstyle.in:8080/billing/get_services");

                }if(Spinnercategory.equals("Nail")){


                    servicenetworking1(3,"http://zalonstyle.in:8080/billing/get_services");

                }if(Spinnercategory.equals("Face")){

                    servicenetworking1(4,"http://zalonstyle.in:8080/billing/get_services");

                }if(Spinnercategory.equals("Hair Removal")){


                    servicenetworking1(5,"http://zalonstyle.in:8080/billing/get_services");

                }if(Spinnercategory.equals("Massage")){


                    servicenetworking1(6,"http://zalonstyle.in:8080/billing/get_services");

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
        arrayadapter6 = new ArrayAdapter<String>(BillingMain.this,android.R.layout.simple_spinner_item,stringlist01 );

        arrayadapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Discounts.setAdapter(arrayadapter6);
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
                txtloyal.setText(points);
                customerName.setText(name);
                customerMob.setText(mob);
                if(gender.equals("male")){
                    r1.setChecked(true);
                    r2.setChecked(false);
                }else {
                    r1.setChecked(false);
                    r2.setChecked(true);
                }
            }





        }
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
            textView2.setText(sinventory.getDescription());
            textView3.setText(sinventory.getStylist());
            textView4.setText(sinventory.getQty());
            textView5.setText(sinventory.getRate());
            textView6.setText(sinventory.getAmount());
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

