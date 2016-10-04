package com.zalonstyles.app.zalon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zalonstyles.app.zalon.Model.EditServiceModel;
import com.zalonstyles.app.zalon.Model.pricevariable;
import com.zalonstyles.app.zalon.Model.pricevariable1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KASHISH on 16-08-2016.
 */
public class Edit_Service extends AppCompatActivity {
    private RecyclerView horizontal_recycler_view;
    public static final String URL = "http://zalonstyle.in:8080/service/get_category_detail";
    public static final String URL1 = "http://zalonstyle.in:8080/service/set_category_detail";

    private Switch switch1;
    private Switch switch2;
    private Switch switch3;
    private EditText etsname;
    private TextView tvscategory;
    private EditText etdescription;
    private EditText editDuration;
    private EditText editDuration1;
    private EditText editPrice;
    private EditText maleprice;
    private EditText femaleprice;

    private NumberPicker np;
    private NumberPicker np1;
   private String idval="";
    private String sid ="";
    private String is_add = "0";

    private Context context;


    private ArrayList<EditServiceModel> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    RecyclerView mRecyclerView;
    TestAdapter mAdapter;
    List<pricevariable> pricelist;
    RecyclerView mRecyclerView1;
    TestAdapter1 mAdapter1;
    List<pricevariable1> pricelist1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicesedit);
        etsname = (EditText) findViewById(R.id.editsname);
        tvscategory = (TextView) findViewById(R.id.editscategory);
        etdescription = (EditText) findViewById(R.id.sdescription);
        editDuration = (EditText) findViewById(R.id.editDur);
        editDuration1 = (EditText) findViewById(R.id.editduration1);
        editPrice = (EditText) findViewById(R.id.editprice);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch3 = (Switch) findViewById(R.id.switch3);
        maleprice = (EditText) findViewById(R.id.editprice1);
        femaleprice = (EditText) findViewById(R.id.editprice2);
       np = (NumberPicker) findViewById(R.id.np);
         np1 = (NumberPicker) findViewById(R.id.np1);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        pricelist = new ArrayList<>();
        mAdapter = new TestAdapter(pricelist);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);


        mRecyclerView1 = (RecyclerView) findViewById(R.id.recycler_view1);
        pricelist1 = new ArrayList<>();
        mAdapter1 = new TestAdapter1(pricelist1);

        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView1.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView1.setAdapter(mAdapter1);
        mRecyclerView1.setHasFixedSize(true);

        final LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearlay1);
        final LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.linearlay2);
        final LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.linearlay3);
        final LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.linearlay4);
        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.GONE);
        linearLayout3.setVisibility(View.GONE);
        linearLayout4.setVisibility(View.GONE);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch1.isChecked() && switch2.isChecked()) {
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                } else if (!switch1.isChecked() && !switch2.isChecked()) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                } else if (switch1.isChecked() && !switch2.isChecked()) {
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                } else if (!switch1.isChecked() && switch2.isChecked()) {
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                }

            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch1.isChecked() && switch2.isChecked()) {
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                }
                if (switch1.isChecked() == false && switch2.isChecked() == false) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                }
                if (switch1.isChecked() && switch2.isChecked() == false) {
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                }
                if (switch1.isChecked() == false && switch2.isChecked()) {
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                }
            }
        });


        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        Bundle Data = getIntent().getExtras();


         idval = Data.getString("valueid");
          sid = Data.getString("Serviceid");

        Log.v("valid", idval);


        final JSONObject params = new JSONObject();
        try {
            params.put("service_id", sid);
            params.put("category_id", idval);
        } catch (JSONException e) {

        }
        try {
            params.put("is_add",is_add);
            params.put("access_token", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes1", response);

                        try {
                            JSONObject jobject = new JSONObject(response);
                            JSONArray payload = jobject.getJSONArray("staff");
                            Log.e("payloaddata", String.valueOf(payload));
                            JSONObject info = jobject.getJSONObject("info");
                            etsname.setText(info.getString("service_name"));
                            tvscategory.setText(info.getString("category_name"));
                            etdescription.setText(info.getString("description"));
                            String staffbased = String.valueOf(info.getString("staff_based"));
                            String Genderbased = String.valueOf(info.getString("gender_based"));

                            try {

                                JSONArray payload1 = jobject.getJSONArray("price");
                                Log.e("payloaddata1", String.valueOf(payload1));
                                for (int i = 0; i < payload1.length(); i++) {
                                    try {
                                        JSONObject obj = payload1.getJSONObject(i);
                                        pricevariable1 service = new pricevariable1();
                                        service.setName(obj.getString("caption"));

                                        service.setMaleprice((obj.getString("male_price")));
                                        service.setFemaleprice((obj.getString("female_price")));
                                        service.setDuration((obj.getString("duration")));
                                        pricelist1.add(service);
                                        Log.e("check2", String.valueOf(pricelist1.get(i)));


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



                            try {

                                JSONArray payload1 = jobject.getJSONArray("price");
                                Log.e("payloaddata1", String.valueOf(payload1));
                                for (int i = 0; i < payload1.length(); i++) {
                                    try {
                                        JSONObject obj = payload1.getJSONObject(i);
                                        pricevariable service = new pricevariable();
                                        service.setName(obj.getString("caption"));

                                        service.setPrice((obj.getString("common_price")));
                                        service.setDuration((obj.getString("duration")));


                                        pricelist.add(service);
                                        Log.e("check2", String.valueOf(pricelist.get(i)));


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                           // listAdapter.notifyDataSetChanged();

                            if (staffbased.equals("on")) {
                                switch1.setChecked(true);
                            } else {
                                switch1.setChecked(false);

                            }
                            if (Genderbased.equals("on")) {
                                switch2.setChecked(true);
                            } else {
                                switch2.setChecked(false);
                            }

                            if (switch1.isChecked() && switch2.isChecked()) {


                            }
                            if (!switch1.isChecked() && !switch2.isChecked()) {
                                editPrice.setText(info.getString("common_price"));
                                editDuration.setText(info.getString("duration"));
                                np.setValue(Integer.parseInt(info.getString("duration")));
                            }
                            if (switch1.isChecked() && !switch2.isChecked()) {

                            }
                            if (!switch1.isChecked() && switch2.isChecked()) {
                                maleprice.setText(info.getString("male_price"));
                                femaleprice.setText(info.getString("female_price"));
                                editDuration1.setText(info.getString("duration"));
                                np1.setValue(Integer.parseInt(info.getString("duration")));


                            }

                            for (int i = 0; i < payload.length(); i++) {
                                try {
                                    JSONObject obj = payload.getJSONObject(i);
                                    EditServiceModel service = new EditServiceModel();
                                    service.setcategotry_id(Integer.parseInt(obj.getString("staff_id")));
                                    service.setName(obj.getString("name"));
                                    service.setCategoryName(obj.getString("category"));
                                    horizontalList.add(service);
                                    Log.e("check2", String.valueOf(horizontalList.get(i)));


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        horizontalAdapter.notifyDataSetChanged();

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

                Log.v("updateUPVolleyParams6", params1.toString());

                return params1;

            }
        };


        requestQueue.add(stringRequest);

        if (switch1.isChecked() && switch2.isChecked()) {
            linearLayout4.setVisibility(View.VISIBLE);
        }
        if (switch1.isChecked() == false && switch2.isChecked() == false) {
            linearLayout1.setVisibility(View.VISIBLE);
        }
        if (switch1.isChecked() && switch2.isChecked() == false) {

            linearLayout3.setVisibility(View.VISIBLE);
        }
        if (switch1.isChecked() == false && switch2.isChecked()) {
            linearLayout2.setVisibility(View.VISIBLE);
        }


       // listAdapter = new priceArrayAdapter(this, R.layout.customeditservice1, pricelist);
        //lv1.setAdapter(listAdapter);

        //listAdapter1 = new priceArrayAdapter1(this, R.layout.customeditservice2, pricelist1);
       // lv2.setAdapter(listAdapter1);


        horizontal_recycler_view = (RecyclerView) findViewById(R.id.horizontal_recycler_view);

        horizontalList = new ArrayList<>();


        horizontalAdapter = new HorizontalAdapter(horizontalList);


//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        vertical_recycler_view.setLayoutManager(mLayoutManager);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(Edit_Service.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
        horizontal_recycler_view.setAdapter(horizontalAdapter);

        final String[] values = {"20", "25", "30", "35", "40", "45", "50", "55","60","65","70","75","80","85","90"};
        np.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        np.setMaxValue(values.length - 1);
        np.setDisplayedValues(values);
        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);


        //Set a value change listener for NumberPicker
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
                editDuration.setText(String.valueOf(values[newVal]));
            }
        });
        final String[] values1 = {"20", "25", "30", "35", "40", "45", "50", "55","60","65","70","75","80","85","90"};
        np1.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        np1.setMaxValue(values1.length - 1);
        np1.setDisplayedValues(values1);
        //Gets whether the selector wheel wraps when reaching the min/max value.
        np1.setWrapSelectorWheel(true);


        //Set a value change listener for NumberPicker
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
                editDuration1.setText(String.valueOf(values1[newVal]));
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SAVEPROFILE1:
                final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlay1);
                final LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearlay2);
                final LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.linearlay3);
                final LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.linearlay4);

                try {
                    if (linearLayout.getVisibility()==View.VISIBLE){
                        sendingdata();
                    }else if (linearLayout1.getVisibility()==View.VISIBLE){
                        sendingdata1();
                    }else if (linearLayout2.getVisibility()==View.VISIBLE){
                        //listAdapter.notifyDataSetChanged();
                        sendingdata2();
                    }else if (linearLayout3.getVisibility()==View.VISIBLE){

                        sendingdata3();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void sendingdata () throws JSONException {
        String servicename = etsname.getText().toString().trim();
        String servicecategory = tvscategory.getText().toString().trim();
        String description = etdescription.getText().toString().trim();
        String price = editPrice.getText().toString().trim();
        String numberpick = String.valueOf(np.getValue());
        String numberpicker = String.valueOf(np1.getValue());
        String ab = editDuration.getText().toString().trim();
        String genderbased ="off";
        String staffbased ="off";
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));

        final JSONObject params = new JSONObject();
        JSONObject param = null;
        param = new JSONObject();
        param.put("access_token", value);
        param.put("service_id",sid);
        param.put("category_id",idval);
        param.put("gender_based",genderbased);
        param.put("staff_based",staffbased);
        param.put("category_name",servicename);
        param.put("description",description);
        params.put("info",param);
        JSONObject param1 = null;
        param1 = new JSONObject();
        param1.put("common_price",price);
        param1.put("duration",ab);
        params.put("price",param1);
        JSONArray ja = new JSONArray();
        for (int i = 0; i < horizontalList.size(); i++)
        {
            EditServiceModel face = horizontalList.get(i);
            if (face.isChecked())
            {
                JSONObject jo = new JSONObject();
                try {
                    jo.put("category_name", face.getName());
                    jo.put("staff_id",face.getCategory_id());
                } catch (JSONException e) {
                    e.printStackTrace();
                }ja.put(jo);
    }
    }
       params.put("staff",ja);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest SR = new StringRequest(Request.Method.POST, URL1,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes6",response);



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
                Map<String, String> params2 = new HashMap<String, String>();

                params2.put("payload", params.toString());

                Log.v("updateUPVolleyParams2", params2.toString());

                return params2;

            }
        };


        requestQueue.add(SR);




    }
    private void sendingdata1 () throws JSONException {
        String servicename = etsname.getText().toString().trim();
        String servicecategory = tvscategory.getText().toString().trim();
        String description = etdescription.getText().toString().trim();
        String price = maleprice.getText().toString().trim();
        String price1 = femaleprice.getText().toString().trim();
        String ab = editDuration1.getText().toString().trim();
        String genderbased ="on";
        String staffbased ="off";
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));

        final JSONObject params = new JSONObject();
        JSONObject param = null;
        param = new JSONObject();
        param.put("access_token", value);
        param.put("service_id",sid);
        param.put("category_id",idval);
        param.put("gender_based",genderbased);
        param.put("staff_based",staffbased);
        param.put("category_name",servicename);
        param.put("description",description);
        params.put("info",param);
        JSONObject param1 = null;
        param1 = new JSONObject();
        param1.put("male_price",price);
        param1.put("female_price",price1);
        param1.put("duration",ab);
        params.put("price",param1);
        JSONArray ja = new JSONArray();
        for (int i = 0; i < horizontalList.size(); i++)
        {
            EditServiceModel face = horizontalList.get(i);
            if (face.isChecked())
            {
                JSONObject jo = new JSONObject();
                try {
                    jo.put("category_name", face.getName());
                    jo.put("staff_id",face.getCategory_id());
                } catch (JSONException e) {
                    e.printStackTrace();
                }ja.put(jo);
            }
        }
        params.put("staff",ja);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest SR = new StringRequest(Request.Method.POST, URL1,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes6",response);



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
                Map<String, String> params2 = new HashMap<String, String>();

                params2.put("payload", params.toString());

                Log.v("updateUPVolleyParams2", params2.toString());

                return params2;

            }
        };


        requestQueue.add(SR);




    }
    private void sendingdata2 () throws JSONException {
        String servicename = etsname.getText().toString().trim();
        String servicecategory = tvscategory.getText().toString().trim();
        String description = etdescription.getText().toString().trim();
        String price = maleprice.getText().toString().trim();
        String price1 = femaleprice.getText().toString().trim();
        String ab = editDuration1.getText().toString().trim();
        String genderbased ="off";
        String staffbased ="on";
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        //listAdapter.notifyDataSetChanged();

        final JSONObject params = new JSONObject();
        JSONObject param = null;
        param = new JSONObject();
        param.put("access_token", value);
        param.put("service_id",sid);
        param.put("category_id",idval);
        param.put("gender_based",genderbased);
        param.put("staff_based",staffbased);
        param.put("category_name",servicename);
        param.put("description",description);
        params.put("info",param);
        JSONObject param1 = null;
        JSONArray ja1 = new JSONArray();
        //listAdapter.notifyDataSetChanged();
        for (int i = 0; i < pricelist.size(); i++)
        {
            pricevariable face = pricelist.get(i);

                JSONObject jo = new JSONObject();
                try {
                    jo.put("category_name", face.getName());
                    jo.put("common_price",face.getPrice());
                    jo.put("duration",face.getDuration());
                } catch (JSONException e) {
                    e.printStackTrace();
                }ja1.put(jo);

        }
        params.put("price",ja1);

        JSONArray ja = new JSONArray();
        for (int i = 0; i < horizontalList.size(); i++)
        {
            EditServiceModel face = horizontalList.get(i);
            if (face.isChecked())
            {
                JSONObject jo = new JSONObject();
                try {
                    jo.put("category_name", face.getName());
                    jo.put("staff_id",face.getCategory_id());
                } catch (JSONException e) {
                    e.printStackTrace();
                }ja.put(jo);
            }
        }
        params.put("staff",ja);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest SR = new StringRequest(Request.Method.POST, URL1,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes6",response);



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
                Map<String, String> params2 = new HashMap<String, String>();

                params2.put("payload", params.toString());

                Log.v("updateUPVolleyParams2", params2.toString());

                return params2;

            }
        };


        requestQueue.add(SR);




    }

    private void sendingdata3 () throws JSONException {
        String servicename = etsname.getText().toString().trim();
        String servicecategory = tvscategory.getText().toString().trim();
        String description = etdescription.getText().toString().trim();
        String price = maleprice.getText().toString().trim();
        String price1 = femaleprice.getText().toString().trim();
        String ab = editDuration1.getText().toString().trim();
        String genderbased ="on";
        String staffbased ="on";
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));


        final JSONObject params = new JSONObject();
        JSONObject param = null;
        param = new JSONObject();
        param.put("access_token", value);
        param.put("service_id",sid);
        param.put("category_id",idval);
        param.put("gender_based",genderbased);
        param.put("staff_based",staffbased);
        param.put("category_name",servicename);
        param.put("description",description);
        params.put("info",param);
        JSONObject param1 = null;
        JSONArray ja1 = new JSONArray();
        for (int i = 0; i < pricelist1.size(); i++)
        {
            pricevariable1 face = pricelist1.get(i);

            JSONObject jo = new JSONObject();
            try {
                jo.put("category_name", face.getName());
                jo.put("male_price",face.getMaleprice());
                jo.put("female_price",face.getFemaleprice());
                jo.put("duration",face.getDuration());
            } catch (JSONException e) {
                e.printStackTrace();
            }ja1.put(jo);

        }
        params.put("price",ja1);

        JSONArray ja = new JSONArray();
        for (int i = 0; i < horizontalList.size(); i++)
        {
            EditServiceModel face = horizontalList.get(i);
            if (face.isChecked())
            {
                JSONObject jo = new JSONObject();
                try {
                    jo.put("category_name", face.getName());
                    jo.put("staff_id",face.getCategory_id());
                } catch (JSONException e) {
                    e.printStackTrace();
                }ja.put(jo);
            }
        }
        params.put("staff",ja);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest SR = new StringRequest(Request.Method.POST, URL1,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes6",response);



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
                Map<String, String> params2 = new HashMap<String, String>();

                params2.put("payload", params.toString());

                Log.v("updateUPVolleyParams2", params2.toString());

                return params2;

            }
        };


        requestQueue.add(SR);




    }





    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

        private List<EditServiceModel> stList;

        public HorizontalAdapter(List<EditServiceModel> students) {
            this.stList = students;

        }

        // Create new views
        @Override
        public HorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
            // create a new view
            View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.horizonal_list, null);

            // create ViewHolder

            ViewHolder viewHolder = new ViewHolder(itemLayoutView);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            final int pos = position;

            viewHolder.tvName.setText(stList.get(position).getName());


            viewHolder.chkSelected.setChecked(stList.get(position).isChecked());

            viewHolder.chkSelected.setTag(stList.get(position));


            viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    EditServiceModel contact = (EditServiceModel) cb.getTag();

                    contact.setChecked(cb.isChecked());
                    stList.get(pos).setChecked(cb.isChecked());

                    Toast.makeText(
                            v.getContext(),
                            "Clicked on Checkbox: " + cb.getText() + " is "
                                    + cb.isChecked(), Toast.LENGTH_LONG).show();
                }
            });

        }

        // Return the size arraylist
        @Override
        public int getItemCount() {
            return stList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView tvName;

            public CheckBox chkSelected;

            public EditServiceModel singlestudent;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);

                tvName = (TextView) itemLayoutView.findViewById(R.id.tvName);

                chkSelected = (CheckBox) itemLayoutView
                        .findViewById(R.id.chkSelected);

            }

        }

        // method to access in activity after updating selection
        public List<EditServiceModel> getStudentist() {
            return stList;
        }

    }

   /* private static class priceArrayAdapter extends ArrayAdapter<pricevariable> {

        private LayoutInflater inflater;
        private List<pricevariable> pricelist;
        private Context context;

        public priceArrayAdapter(Context context, int resourceId, List<pricevariable> pricelist) {
            super(context, R.layout.customeditservice1, pricelist);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.pricelist = pricelist;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // SERVICES to display
            pricevariable hair = (pricevariable) this.getItem(position);

            // The child views in each row.
            TextView editText;
            EditText editText1;
            EditText editText2;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.customeditservice1, null);

                // Find the child views.
                editText = (TextView) convertView
                        .findViewById(R.id.emptextview1);
                editText1 = (EditText) convertView.findViewById(R.id.emptextview2);
                editText2 = (EditText) convertView.findViewById(R.id.emptextview3);


                convertView.setTag(new Viewholder4(editText, editText1, editText2));


            }
            // Reuse existing row view
            else {
                // Because we use a ViewHolder, we avoid having to call
                // findViewById().
                Viewholder4 viewHolder = (Viewholder4) convertView
                        .getTag();
                editText = viewHolder.getEditText1();
                editText1 = viewHolder.getEditText2();
                editText2 = viewHolder.getEditText3();
            }


            // Display Service data
            editText.setText(hair.getName());
            editText1.setText(String.valueOf(hair.getPrice()));
            editText2.setText(String.valueOf(hair.getDuration()));


            return convertView;
        }

    }*/

   /* private static class priceArrayAdapter1 extends ArrayAdapter<pricevariable1> {

        private LayoutInflater inflater;
        private List<pricevariable1> pricelist1;
        private Context context;

        public priceArrayAdapter1(Context context, int resourceId, List<pricevariable1> pricelist1) {
            super(context, R.layout.customeditservice2, pricelist1);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.pricelist1 = pricelist1;
            this.context = context;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // SERVICES to display
            final pricevariable1 hair = (pricevariable1) this.getItem(position);

            // The child views in each row.
            TextView editText;
            final EditText editText1;
            EditText editText2;
            EditText editText3;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


            // Create a new row view
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.customeditservice2, null);

                // Find the child views.
                editText = (TextView) convertView
                        .findViewById(R.id.emptextview11);
                editText1 = (EditText) convertView.findViewById(R.id.emptextview12);
                editText2 = (EditText) convertView.findViewById(R.id.emptextview13);
                editText3 = (EditText) convertView.findViewById(R.id.emptextview14);

                convertView.setTag(new Viewholder5(editText, editText1, editText2, editText3));


            }
            // Reuse existing row view
            else {
                // Because we use a ViewHolder, we avoid having to call
                // findViewById().
                Viewholder5 viewHolder = (Viewholder5) convertView
                        .getTag();
                editText = viewHolder.getEditText1();
                editText1 = viewHolder.getEditText2();
                editText2 = viewHolder.getEditText3();
                editText3 = viewHolder.getEditText4();
editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus){
            String value = editText1.getText().toString();
            hair.setMaleprice((value));
            Log.v("valuess", String.valueOf(hair.getMaleprice()));
            notifyDataSetChanged();
    }else {
        }
    }
});
                editText1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        hair.setMaleprice((s.toString()) );
                        Log.v("valuesss", String.valueOf(hair.getMaleprice()));
                        Log.v("ppp1", String.valueOf(s));
                        notifyDataSetChanged();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.v("ppp2", String.valueOf(s));
                        hair.setMaleprice((s.toString()) );
                        Log.v("valuessss", String.valueOf(hair.getMaleprice()));

                        notifyDataSetChanged();
                    }
                });
            }


            // Display Service data
            editText.setText(hair.getName());
            editText1.setText(String.valueOf(hair.getMaleprice()));
            editText2.setText(String.valueOf(hair.getFemaleprice()));
            editText3.setText(String.valueOf(hair.getDuration()));


            return convertView;
        }

    }*/
    public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

        private List<pricevariable> pricelist;
       private String spinnerValue1;


        public  class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public EditText editText1;
            public Spinner editText2;
            public TextView editText;
            String[] spinnerItems1 = new String[]{"15","20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70","75","80","85","90","0"};



            public ViewHolder(View v) {
                super(v);
                editText = (TextView) v.
                        findViewById(R.id.emptextview1);
                editText1 = (EditText) v.findViewById(R.id.emptextview2);
                editText2 = (Spinner) v.findViewById(R.id.emptextview3);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.spinnerlayout, spinnerItems1);
                adapter1.setDropDownViewResource(R.layout.spinnerlayout);
                editText2.setAdapter(adapter1);



            }
        }

        public TestAdapter(List<pricevariable> pricelist2) {
            pricelist = pricelist2;
        }

        @Override
        public TestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

            Log.v("test-recyclerview", "onCreateViewHolder");

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.customeditservice1, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            holder.editText.setText(pricelist.get(position).getName());
            holder.editText1.setText(String.valueOf(pricelist.get(position).getPrice()));
            holder.editText2.setSelection(getIndex(holder.editText2, pricelist.get(position).getDuration()));
final int pos = position;
           


           holder.editText2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  String  spinnerValue = holder.editText2.getSelectedItem().toString();
                    Log.v("spinnerValue",spinnerValue);
                    pricelist.get(pos).setDuration((String.valueOf(spinnerValue)));



                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    spinnerValue1 ="empty";

                }
            });
            holder.editText1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                      pricelist.get(position).setPrice((String.valueOf(s))) ;
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        

        }

       

       @Override
        public int getItemCount() {
            return pricelist.size();
        }
    }

    private int getIndex(Spinner spinner, String myString) {
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        // Check for this when you set the position.
        return -1;
    }

    public class TestAdapter1 extends RecyclerView.Adapter<TestAdapter1.ViewHolder> {

        private List<pricevariable1> pricelist1;


        public  class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public EditText editText1;
            public EditText editText2;
            public TextView editText;
            public Spinner editText3;
            String[] spinnerItems1 = new String[]{"15","20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70","75","80","85","90","0"};


            public ViewHolder(View v) {
                super(v);
                editText = (TextView) v.
                        findViewById(R.id.emptextview11);
                editText1 = (EditText) v.findViewById(R.id.emptextview12);
                editText2 = (EditText) v.findViewById(R.id.emptextview13);
                editText3 = (Spinner) v.findViewById(R.id.emptextview14);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.spinnerlayout, spinnerItems1);
                adapter1.setDropDownViewResource(R.layout.spinnerlayout);
                editText3.setAdapter(adapter1);
            }
        }

        public TestAdapter1(List<pricevariable1> pricelist2) {
            pricelist1 = pricelist2;
        }

        @Override
        public TestAdapter1.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

            Log.v("test-recyclerview", "onCreateViewHolder");

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.customeditservice2, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.editText.setText(pricelist1.get(position).getName());
            holder.editText1.setText(String.valueOf(pricelist1.get(position).getMaleprice()));
            holder.editText2.setText(String.valueOf(pricelist1.get(position).getFemaleprice()));

            holder.editText3.setSelection(getIndex(holder.editText3, pricelist1.get(position).getDuration()));
            final int pos = position;



            holder.editText3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String  spinnerValue = holder.editText3.getSelectedItem().toString();
                    Log.v("spinnerValue",spinnerValue);
                    pricelist.get(pos).setDuration((String.valueOf(spinnerValue)));



                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }
            });



            holder.editText1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    pricelist1.get(position).setMaleprice((String.valueOf(s))); ;
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            holder.editText2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    pricelist1.get(position).setFemaleprice((String.valueOf(s))); ;

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }


        @Override
        public int getItemCount() {
            return pricelist1.size();
        }
    }


}

