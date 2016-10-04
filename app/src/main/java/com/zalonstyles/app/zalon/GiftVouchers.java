package com.zalonstyles.app.zalon;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

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
 * Created by KASHISH on 30-07-2016.
 */
public class GiftVouchers extends AppCompatActivity {
    private RadioButton r1;
    private RadioButton r2;
    private EditText pt;
    private EditText rs;
    private EditText redem;
    private Button bt;
    private String status;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_vouchers);
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));




        r1 = (RadioButton)findViewById(R.id.rad1);
        r2 = (RadioButton)findViewById(R.id.rad2);
        pt = (EditText)findViewById(R.id.rs);
        rs =(EditText)findViewById(R.id.valpoints);
        redem = (EditText)findViewById(R.id.redemp);
        bt =(Button)findViewById(R.id.btnn);
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

        final JSONObject params = new JSONObject();

        try {
            params.put("access_token", value);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/campaign/getLoyaltyPoints",
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes1",response);

                        try {
                            JSONObject jobject = new JSONObject(response);
                            JSONObject obj = jobject.getJSONObject("loyalty_points");
                            Log.e("payloaddata1", String.valueOf(obj));
                            try{
                                if(obj.getString("status").equals("on")){
                                    r1.setChecked(true);
                                }else {
                                    r2.setChecked(true);
                                }
                                rs.setText(obj.getString("value_of_one_point"));
                                pt.setText(obj.getString("rs100"));
                                redem.setText(obj.getString("min_points"));
                            }catch (JSONException e) {
                                e.printStackTrace();
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

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1.isChecked()) {
                    status = "on";
                } else {
                    status = "off";
                }

                String date = pt.getText().toString();
                String minval = rs.getText().toString();
                String edittxt=redem.getText().toString();
                SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
                final JSONObject params = new JSONObject();

                try {
                    params.put("rs100",date);
                    params.put("access_token", value);
                    params.put("value_of_one_point",minval);
                    params.put("min_points",edittxt);

                    params.put("status",status);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/campaign/setLoyaltyPoints",
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Log.v("logresponse", response.toString());


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

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });





    }
}
