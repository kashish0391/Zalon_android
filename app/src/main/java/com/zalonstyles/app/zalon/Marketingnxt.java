package com.zalonstyles.app.zalon;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

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
 * Created by KASHISH on 19-10-2016.
 */

public class Marketingnxt extends AppCompatActivity{
    private EditText editText;
    private TextView txt;
    private TextView strt;
    private TextView end;
    private TextView campaign;
    private TextView target;
    private  String userId ;
    private final TextWatcher mTextEditorWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            txt.setText(String.valueOf(s.length()+" Characters"));

        }

        public void afterTextChanged(Editable s) {
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketingnext);
        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            userId = extras.getString("valueid");
        }
        editText = (EditText)findViewById(R.id.nxtmsg);
        target =(TextView) findViewById(R.id.nxttarget);
        txt = (TextView) findViewById(R.id.nxtid);
        strt = (TextView)findViewById(R.id.nxtmarketingstrt) ;
        end = (TextView) findViewById(R.id.nxtbtnmarketing);
        campaign = (TextView) findViewById(R.id.nxtcampname);
        editText.addTextChangedListener(mTextEditorWatcher);
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        Log.e("accesslog",value);
        final JSONObject params = new JSONObject();
        try {
            params.put("campaign_id",userId);
            params.put("access_token", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/campaign/sendCampaignData",
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes",response);

                        try {
                            JSONObject jobject = new JSONObject(response);
                            JSONObject payload = jobject.getJSONObject("data");
                            Log.e("payloaddata", String.valueOf(payload));



                                    editText.setText(payload.getString("message"));
                            strt.setText(payload.getString("start_date"));
                                    end.setText(payload.getString("end_date"));
                            campaign.setText(payload.getString("campaign_name"));
                            target.setText(payload.getString("target_customers"));

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;
    }    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SAVEPROFILE1:
                SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
                Log.e("accesslog",value);
                final JSONObject params = new JSONObject();
                try {
                    params.put("campaign_id",userId);
                    params.put("access_token", value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/campaign/sendToConfirm",
                        new Response.Listener<String>(){

                            @Override
                            public void onResponse(String response) {

                                Log.v("updateUPVolleyRes",response);


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

                        Log.v("campnxt", params1.toString());

                        return params1;

                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

