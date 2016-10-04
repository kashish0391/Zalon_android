package com.zalonstyles.app.zalon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class changePassword extends AppCompatActivity implements View.OnClickListener {
    public static final String LOGIN_URL = "http://zalonstyle.in:8080/salon/reset_pwd/";
    private EditText pass;
    private EditText newpass;
    private EditText confirmpass;
    private Button btn;
    private String passString;
    private String newpassString;
    private String confirmpssString;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
       pass = (EditText) findViewById(R.id.temp);
        newpass = (EditText) findViewById(R.id.temp1);
        confirmpass = (EditText) findViewById(R.id.temp2);
        btn = (Button) findViewById(R.id.signin2);
        btn.setOnClickListener(this);
    }

    private void passChange() throws JSONException {
        newpassString = newpass.getText().toString().trim();
        passString =pass.getText().toString().trim();
        confirmpssString = confirmpass.getText().toString().trim();
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        final JSONObject params = new JSONObject();
        params.put("temp_password",passString);
        params.put("password",newpassString);
        params.put("access_token", value);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes",response);

                        try {
                            if (new JSONObject(response).getJSONObject("payload").getString("auth_status").equals("success")) {

                                Toast.makeText(getApplicationContext()," PasswordChanged", Toast.LENGTH_LONG).show();
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

                Log.v("updateUPVolleyParams", params1.toString());

                return params1;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onClick(View v) {
        if ( newpass.getText().toString().equals( confirmpass.getText().toString())) {
            //password and confirm passwords equal.go to next step
            try {
                passChange();
                Intent intent = new Intent(changePassword.this,LoginActivity.class);
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            //passwords not matching.please try again
            Toast.makeText(getApplicationContext(),"Password MisMatch",Toast.LENGTH_LONG).show();
        }
    }}


    /**
     * Created by KASHISH on 15-07-2016.
     */
