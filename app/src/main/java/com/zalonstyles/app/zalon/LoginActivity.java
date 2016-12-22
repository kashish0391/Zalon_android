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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String LOGIN_URL = "http://zalonstyle.in:8080/salon/login";
    TextView create;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private SharedPreferences preferences;
    private Button buttonLogin;
    private Context context;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        create = (TextView) findViewById(R.id.create);
        editTextUsername = (EditText) findViewById(R.id.editTextusername);
        editTextPassword = (EditText) findViewById(R.id.editTextpassword);


        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(this);
    }

    private void userLogin() throws JSONException {
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        final JSONObject params = new JSONObject();
        params.put("username", username);
        params.put("password", password);
        StringRequest stringRequest = new StringRequest(Method.POST, LOGIN_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jobject = new JSONObject(response);
                            Log.e("Response1111111..", jobject.toString());
                            Log.v("updateUPVolleyRes", response);
                            String strSuccess = jobject.getString("status");
                            Log.v("statusresponse", strSuccess);
                            JSONObject payload = jobject.getJSONObject("payload");
                            String accesstoken = payload.getString("access_token");
                            Log.v("atoken", accesstoken);
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("AppConstant.AUTH_TOKEN", accesstoken);
                            editor.commit();


                            if (new JSONObject(response).getString("status").equals("success")) {
                                Intent intent = new Intent(LoginActivity.this, Services_activity.class);
                                startActivity(intent);

                                Toast.makeText(getApplicationContext(), " Successful", Toast.LENGTH_SHORT).show();
                                Log.v("statusresponse", strSuccess);


                            } else {
                                Toast.makeText(getApplicationContext(), " WrongPassword", Toast.LENGTH_SHORT).show();

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

                Log.v("updateUPVolleyParams", params1.toString());

                return params1;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    public void forgotpassword(View view) {
        Intent intent = new Intent(LoginActivity.this, forgotPassword.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        try {
            userLogin();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

