package com.zalonstyles.app.zalon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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

/**
 * Created by KASHISH on 30-07-2016.
 */
public class Change_Password  extends AppCompatActivity {
    public static final String LOGIN_URL = "http://zalonstyle.in:8080/salon/change_pwd";
    private EditText oldpass;
    private EditText newpass;
    private EditText confirmpass;
    private Button btn;
    private String passString;
    private String newpassString;
    private String confirmpssString;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        oldpass = (EditText) findViewById(R.id.CHANGEtemp);
        newpass = (EditText) findViewById(R.id.CHANGEtemp1);
        confirmpass = (EditText) findViewById(R.id.CHANGEtemp2);
        btn = (Button) findViewById(R.id.CHANGEsignin2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( newpass.getText().toString().equals( confirmpass.getText().toString())) {
                    //password and confirm passwords equal.go to next step
                    try {
                        passChange();
                        Intent intent = new Intent(Change_Password.this,LoginActivity.class);
                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    //passwords not matching.please try again
                    Toast.makeText(getApplicationContext(),"Password MisMatch",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void passChange() throws JSONException {
        newpassString = newpass.getText().toString().trim();
        passString =oldpass.getText().toString().trim();
        confirmpssString = confirmpass.getText().toString().trim();
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        final JSONObject params = new JSONObject();
        params.put("old_pwd",passString);
        params.put("new_pwd",newpassString);
        params.put("access_token", value);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("up1",response);

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


}
