package com.zalonstyles.app.zalon;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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


        spinner = (Spinner) findViewById(R.id.editspinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner2 = (Spinner) findViewById(R.id.editspinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner4 = (Spinner) findViewById(R.id.editspinner4);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner6 = (Spinner) findViewById(R.id.editspinner6);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);
        spinner8 = (Spinner) findViewById(R.id.editspinner8);
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter8);
        spinner10 = (Spinner) findViewById(R.id.editspinner10);
        ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner10.setAdapter(adapter10);
        spinner12 = (Spinner) findViewById(R.id.editspinner12);
        ArrayAdapter<String> adapter12 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner12.setAdapter(adapter12);
        spinner1 = (Spinner) findViewById(R.id.editspinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner3 = (Spinner) findViewById(R.id.editspinner3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner5 = (Spinner) findViewById(R.id.editspinner5);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
        spinner7 = (Spinner) findViewById(R.id.editspinner7);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);
        spinner9 = (Spinner) findViewById(R.id.editspinner9);
        ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner9.setAdapter(adapter9);
        spinner11 = (Spinner) findViewById(R.id.editspinner11);
        ArrayAdapter<String> adapter11 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner11.setAdapter(adapter11);
        spinner13 = (Spinner) findViewById(R.id.editspinner13);
        ArrayAdapter<String> adapter13 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner13.setAdapter(adapter13);

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

                       /* try {
                            JSONObject jobject = new JSONObject(response);
                            JSONObject obj = jobject.getJSONObject("information");

                            profileModel.setProfilemodel(obj.getString("name"));
                            profileModel.setEmail(obj.getString("email"));
                            profileModel.setMobile(obj.getString("mobile"));
                            profileModel.setWebsite(obj.getString("website"));
                            profileModel.setLogo(obj.getString("logo"));
                            profileModel.setAddress(obj.getString("address"));
                            profileModel.setProfilemodel(obj.getString("payment_methods"));

                            ProfileName.setText(obj.getString("name"));
                            ProfileEmail.setText(obj.getString("email"));
                            ProfilePayment.setText(obj.getString("payment_methods"));
                            ProfileWebsite.setText(obj.getString("website"));
                            ProfileAdd.setText(obj.getString("address"));
                            ProfileMob.setText(obj.getString("mobile"));
                            JSONArray jsonArray= jobject.getJSONArray("timing");
                            for (int i =0;i<jsonArray.length();i++){
                                try{
                                    ProfileModel1 profileModel1 = new ProfileModel1();
                                    JSONObject obj1 = jsonArray.getJSONObject(i);
                                    profileModel1.setDay(obj1.getString("day"));
                                    profileModel1.setTime(obj1.getString("timing"));

                                    profileList.add(profileModel1);
                                    Log.e("check21", String.valueOf(profileList.get(i)));






                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/

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

