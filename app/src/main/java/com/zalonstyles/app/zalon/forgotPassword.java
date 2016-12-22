package com.zalonstyles.app.zalon;

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

/**
 * Created by KASHISH on 08-07-2016.
 */
public class forgotPassword extends AppCompatActivity implements View.OnClickListener {
private EditText username;
    public  static final String DEFAULT = "N/A";
    public static final String LOGIN_URL = "http://zalonstyle.in:8080/salon/forgot_pwd/";

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);
        btn =(Button)findViewById(R.id.signin1);
        username =(EditText)findViewById(R.id.fusername);
        btn.setOnClickListener(this);}

    public void changePassword() throws JSONException {
        Intent intent = new Intent(forgotPassword.this, changePassword.class);
        startActivity(intent);
        String username1= username.getText().toString().trim();
        //SharedPreferences preferences=getSharedPreferences("AccessToken", Context.MODE_PRIVATE);

        //String AccessKey = preferences.getString("AppConstant.AUTH_TOKEN","N/A");
         SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        Log.e("accesslog",value);
        final JSONObject params = new JSONObject();
        params.put("username",username1);
        params.put("access_token", value);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes",response);

                        try {
                            if (new JSONObject(response).getJSONObject("payload").getString("auth_status").equals("success")) {

                                Toast.makeText(getApplicationContext(), " Successfull", Toast.LENGTH_LONG).show();
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
        try {
            changePassword();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}



