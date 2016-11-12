package com.zalonstyles.app.zalon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KASHISH on 09-08-2016.
 */
public class EditProfile extends AppCompatActivity {

    Spinner spinner;
    String[] spinnerItems = new String[]{"8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00"};
    Spinner spinner2;
    Spinner spinner4;
    Spinner spinner6;
    Spinner spinner8;
    Spinner spinner10;
    Spinner spinner12;
    Spinner spinner1;
    String[] spinnerItems1 = new String[]{"7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00"};
    Spinner spinner3;
    Spinner spinner5;
    Spinner spinner7;
    Spinner spinner9;
    Spinner spinner11;
    Spinner spinner13;
    private EditText Name;
    private EditText Address;
    private EditText Locality;
    private EditText Phone;
    private EditText City;
    private EditText Pin;
    private EditText Website;
    private EditText Email;
    private Button edittiming;
    private CheckBox Cashchkbox;
    private CheckBox Cardchkbox;
    private CheckBox Walletchkbox;
    private CheckBox Mondaychkbox;
    private CheckBox Tuesdaychkbox;
    private CheckBox Wednesdaychkbox;
    private CheckBox Thursdaychkbox;
    private CheckBox Fridaychkbox;
    private CheckBox Saturdaychkbox;
    private CheckBox Sundaychkbox;
    private String spinnerValue;
    private String spinnerValue2;
    private String spinnerValue4;
    private String spinnerValue6;
    private String spinnerValue8;
    private String spinnerValue10;
    private String spinnerValue12;
    private String spinnerValue1;
    private String spinnerValue3;
    private String spinnerValue5;
    private String spinnerValue7;
    private String spinnerValue9;
    private String spinnerValue11;
    private String spinnerValue13;
    private String name;
    private String mob;
    private String add;
    private String locality;
    private String city;
    private String pin;
    private String email;
    private String website;
    private String payments;
    public static final String URL =  "http://52.41.72.46:8080/salon/get_profile";
    public static final String SUBMIT_URL =  "http://52.41.72.46:8080/salon/update_profile";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        Name = (EditText) findViewById(R.id.editprofiletv1);
        Phone = (EditText) findViewById(R.id.editprofiletv2);
        Address = (EditText) findViewById(R.id.editprofiletv3);
        Locality = (EditText) findViewById(R.id.editprofiletv7);
        City = (EditText) findViewById(R.id.editprofiletv8);
        Pin = (EditText) findViewById(R.id.editprofiletv9);
        Email = (EditText) findViewById(R.id.editprofiletv6);
        Website = (EditText) findViewById(R.id.editprofiletv4);
        Cashchkbox = (CheckBox) findViewById(R.id.editchkbox);
        Cardchkbox = (CheckBox) findViewById(R.id.editchkbox1);
        Walletchkbox = (CheckBox) findViewById(R.id.editchkbox2);
        Mondaychkbox = (CheckBox) findViewById(R.id.editchkbox3);
        Tuesdaychkbox = (CheckBox) findViewById(R.id.editchkbox4);
        Wednesdaychkbox = (CheckBox) findViewById(R.id.editchkbox5);
        Thursdaychkbox = (CheckBox) findViewById(R.id.editchkbox6);
        Fridaychkbox = (CheckBox) findViewById(R.id.editchkbox7);
        Saturdaychkbox = (CheckBox) findViewById(R.id.editchkbox8);
        Sundaychkbox = (CheckBox) findViewById(R.id.editchkbox9);
        edittiming = (Button) findViewById(R.id.buttontiming);
       Phone.setRawInputType(Configuration.KEYBOARD_QWERTY);
        Pin.setRawInputType(Configuration.KEYBOARD_12KEY);
        Name.setRawInputType(Configuration.KEYBOARD_NOKEYS);


        spinner = (Spinner) findViewById(R.id.editspinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue ="empty";

            }
        });
        spinner2 = (Spinner) findViewById(R.id.editspinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue2 = spinner2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue2 ="empty";

            }
        });
        spinner4 = (Spinner) findViewById(R.id.editspinner4);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue4 = spinner4.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue4 ="empty";

            }
        });
        spinner6 = (Spinner) findViewById(R.id.editspinner6);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue6 = spinner6.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue6 ="empty";

            }
        });
        spinner8 = (Spinner) findViewById(R.id.editspinner8);
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter8);
        spinner8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue8 = spinner8.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue8 ="empty";

            }
        });
        spinner10 = (Spinner) findViewById(R.id.editspinner10);
        ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner10.setAdapter(adapter10);
        spinner10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue10 = spinner10.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue10 ="empty";

            }
        });
        spinner12 = (Spinner) findViewById(R.id.editspinner12);
        ArrayAdapter<String> adapter12 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner12.setAdapter(adapter12);
        spinner12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue12 = spinner12.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue12 ="empty";

            }
        });
        spinner1 = (Spinner) findViewById(R.id.editspinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue1 = spinner1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue1 ="empty";

            }
        });
        spinner3 = (Spinner) findViewById(R.id.editspinner3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue3 = spinner3.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue3 ="empty";

            }
        });
        spinner5 = (Spinner) findViewById(R.id.editspinner5);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue5 = spinner5.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue5 ="empty";

            }
        });
        spinner7 = (Spinner) findViewById(R.id.editspinner7);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);
        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue7 = spinner7.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue7 ="empty";

            }
        });
        spinner9 = (Spinner) findViewById(R.id.editspinner9);
        ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner9.setAdapter(adapter9);
        spinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue9 = spinner9.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue9 ="empty";

            }
        });
        spinner11 = (Spinner) findViewById(R.id.editspinner11);
        ArrayAdapter<String> adapter11 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner11.setAdapter(adapter11);
        spinner11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue11 = spinner11.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue11 ="empty";

            }
        });
        spinner13 = (Spinner) findViewById(R.id.editspinner13);
        ArrayAdapter<String> adapter13 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner13.setAdapter(adapter13);
        spinner13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue13 = spinner13.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue13 ="empty";

            }
        });
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layouttiming);
        linearLayout.setVisibility(View.GONE);
        edittiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
                final LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.layoutbutton);
                linearLayout1.setVisibility(View.GONE);

            }
        });

        final JSONObject params = new JSONObject();
        try {
            String  value1 ="1";
            params.put("access_token", value);
            params.put("is_edit",value1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes11",response);

                        try {
                            JSONObject jobject = new JSONObject(response);
                            JSONObject obj = jobject.getJSONObject("information");
                            Name.setText(obj.getString("name"));
                            Email.setText(obj.getString("email"));
                            Website.setText(obj.getString("website"));
                            Address.setText(obj.getString("address"));
                            Phone.setText(obj.getString("mobile"));
                            Locality.setText(obj.getString("locality"));
                            City.setText(obj.getString("city"));
                            Pin.setText(obj.getString("pin"));
                            JSONObject obj1 = obj.getJSONObject("payment_methods");
                            String cash = String.valueOf(obj1.getString("cash"));
                            String card = String.valueOf(obj1.getString("card"));
                            String wallet = String.valueOf(obj1.getString("wallet"));
                            Log.v("cash111",cash);
                           if (cash.equals("true")) {
                               Cashchkbox.setChecked(true);
                           }else {
                               Cashchkbox.setChecked(false);

                           }
                            if (wallet.equals("true")) {
                                Walletchkbox.setChecked(true);
                            }else {
                                Walletchkbox.setChecked(false);

                            }
                            if (card.equals("true")) {
                                Cardchkbox.setChecked(true);
                            }else {
                                Cardchkbox.setChecked(false);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SAVEPROFILE:
                final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layouttiming);

                try {
if (linearLayout.getVisibility()==View.VISIBLE){
sendingdatatiming();
}else {
    sendingdata();
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
    name = Name.getText().toString().trim();
    mob = Phone.getText().toString().trim();
    add = Address.getText().toString().trim();
    locality = Locality.getText().toString().trim();
    pin = Pin.getText().toString().trim();
    city = City.getText().toString().trim();
    website = Website.getText().toString().trim();
    email = Email.getText().toString().trim();
    name = Name.getText().toString().trim();
    String cashchk;
    String cardchk;
    String walletchk;
    String isedittime ="0";
    if (Cashchkbox.isChecked()){
        cashchk = "1";
    }else{
        cashchk = "0";
    }
    if (Cardchkbox.isChecked()){
      cardchk = "2";
    }else{
      cardchk = "0";
    }
    if (Walletchkbox.isChecked()){
        walletchk = "3";
    }else{
      walletchk = "0";
    }

    SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));

    final JSONObject params = new JSONObject();
    params.put("access_token", value);
    params.put("is_edit_time",isedittime);

    JSONObject param = null;
    param = new JSONObject();
    param.put("name", name);
    param.put("email",email);
    param.put("mobile", mob);
    param.put("website",website);
    params.put("information", param);
    JSONObject param1 = null;
    param1 = new JSONObject();
    param1.put("address", add);
    param1.put("locality",locality);
    param1.put("city", city);
    param1.put("pin",pin);
    params.put("address", param1);
    JSONObject param2 = null;
    param2 = new JSONObject();
    param2.put("cash", cashchk);
    param2.put("card",cardchk);
    param2.put("wallet",walletchk);
    params.put("payment_method", param2);


    StringRequest stringRequest = new StringRequest(Request.Method.POST, SUBMIT_URL,
            new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.v("logresponse", response.toString());
                    Intent intent = new Intent(EditProfile.this,Profile.class);
                    startActivity(intent);





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

    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);


}
    private void sendingdatatiming () throws JSONException {
        name = Name.getText().toString().trim();
        mob = Phone.getText().toString().trim();
        add = Address.getText().toString().trim();
        locality = Locality.getText().toString().trim();
        pin = Pin.getText().toString().trim();
        city = City.getText().toString().trim();
        website = Website.getText().toString().trim();
        email = Email.getText().toString().trim();
        name = Name.getText().toString().trim();
        String cashchk;
        String cardchk;
        String walletchk;
        String Mondaychk;
        String Tuesdayhk;
        String Wednesdaychk;
        String Thursdaychk;
        String Fridaychk;
        String Saturdaychk;
        String Sundaychk;
        String isedit_time = "1";
        if (Cashchkbox.isChecked()) {
            cashchk = "1";
        } else {
            cashchk = "0";
        }
        if (Cardchkbox.isChecked()) {
            cardchk = "2";
        } else {
            cardchk = "0";
        }
        if (Walletchkbox.isChecked()) {
            walletchk = "3";
        } else {
            walletchk = "0";
        }
        if (Mondaychkbox.isChecked()) {
            Mondaychk = "open";
        } else {
            Mondaychk = "close";
        }
        if (Tuesdaychkbox.isChecked()) {
            Tuesdayhk = "open";
        } else {
            Tuesdayhk = "close";
        }
        if (Wednesdaychkbox.isChecked()) {
            Wednesdaychk = "open";
        } else {
            Wednesdaychk = "close";
        }
        if (Thursdaychkbox.isChecked()) {
            Thursdaychk = "open";
        } else {
            Thursdaychk = "close";
        }
        if (Fridaychkbox.isChecked()) {
            Fridaychk = "open";
        } else {
            Fridaychk = "close";
        }
        if (Saturdaychkbox.isChecked()) {
            Saturdaychk = "open";
        } else {
            Saturdaychk = "close";
        }
        if (Sundaychkbox.isChecked()) {
            Sundaychk = "open";
        } else {
            Sundaychk = "close";
        }
        Log.v("mondayvalue",Mondaychk);

        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));

        final JSONObject params = new JSONObject();
        params.put("access_token", value);
        params.put("is_edit_time",isedit_time);

        JSONObject param = null;
        param = new JSONObject();
        param.put("name", name);
        param.put("email", email);
        param.put("mobile", mob);
        param.put("website", website);
        params.put("information", param);
        JSONObject param1 = null;
        param1 = new JSONObject();
        param1.put("address", add);
        param1.put("locality", locality);
        param1.put("city", city);
        param1.put("pin", pin);
        params.put("address", param1);
        JSONObject param2 = null;
        param2 = new JSONObject();
        param2.put("cash", cashchk);
        param2.put("card", cardchk);
        param2.put("wallet", walletchk);
        params.put("payment_method", param2);
        JSONObject parammonday = new JSONObject();
        parammonday.put("status",Mondaychk);
        parammonday.put("openat",spinnerValue);
        parammonday.put("closeat",spinnerValue1);
        JSONObject paramtuesdayday = new JSONObject();
        paramtuesdayday.put("status",Tuesdayhk);
        paramtuesdayday.put("openat",spinnerValue2);
        paramtuesdayday.put("closeat",spinnerValue3);
        JSONObject paramwednesday = new JSONObject();
        paramwednesday.put("status",Wednesdaychk);
        paramwednesday.put("openat",spinnerValue4);
        paramwednesday.put("closeat",spinnerValue5);
        JSONObject paramthursday = new JSONObject();
        paramthursday.put("status",Thursdaychk);
        paramthursday.put("openat",spinnerValue6);
        paramthursday.put("closeat",spinnerValue7);
        JSONObject paramfriday = new JSONObject();
        paramfriday.put("status",Fridaychk);
        paramfriday.put("openat",spinnerValue8);
        paramfriday.put("closeat",spinnerValue9);
        JSONObject paramsaturday = new JSONObject();
        paramsaturday.put("status",Saturdaychk);
        paramsaturday.put("openat",spinnerValue10);
        paramsaturday.put("closeat",spinnerValue11);
        JSONObject paramsunday = new JSONObject();
        paramsunday.put("status",Sundaychk);
        paramsunday.put("openat",spinnerValue12);
        paramsunday.put("closeat",spinnerValue13);
        JSONArray paramweek = new JSONArray();
        paramweek.put(parammonday);
        paramweek.put(paramtuesdayday);
        paramweek.put(paramwednesday);
        paramweek.put(paramthursday);
        paramweek.put(paramfriday);
        paramweek.put(paramsaturday);
        paramweek.put(paramsunday);
        params.put("timings",paramweek);







        StringRequest stringRequest = new StringRequest(Request.Method.POST, SUBMIT_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.v("logresponse", response.toString());
                        Intent intent = new Intent(EditProfile.this, Profile.class);
                        startActivity(intent);


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

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    }

