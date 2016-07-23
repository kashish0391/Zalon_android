package com.zalonstyles.app.zalon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zalonstyles.app.zalon.Model.Services;
import com.zalonstyles.app.zalon.Model.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KASHISH on 19-07-2016.
 */
public  class popup_hr_removal extends AppCompatActivity {
    public static final String URL =  "http://52.41.72.46:8080/service/get_category";
    public static final String URL1 = " http://52.41.72.46:8080/service/create_service_lineup";
    public final String serviceid = "5";
    private ListView mainListView;
    private Button check;
    private Button back;
    private List<Services> hairremovallist = new ArrayList<>();
    private Context context;
    private ArrayAdapter<Services> listAdapter;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_hair_removal);
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.75),(int)(height*.76));
        Log.e("accesslog",value);
        mainListView = (ListView) findViewById(R.id.listView6);
        check = (Button) findViewById(R.id.button6);
        back = (Button)findViewById(R.id.buttonback6);

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

                        try {
                            JSONObject jobject = new JSONObject(response);
                            JSONArray payload = jobject.getJSONArray("payload");
                            Log.e("payloaddata", String.valueOf(payload));
                            for (int i = 0; i < payload.length(); i++) {
                                try{
                                    JSONObject obj = payload.getJSONObject(i);
                                    Services service = new Services();
                                    service.setcategotry_id(Integer.parseInt(obj.getString("id")));

                                    service.setName(obj.getString("name"));
                                    hairremovallist.add(service);
                                    Log.e("check2", String.valueOf(hairremovallist.get(i)));






                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
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



        listAdapter = new hairremovalArrayAdapter(this,R.layout.customhair_removal, hairremovallist);

        mainListView.setAdapter(listAdapter);


        mainListView
                .setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View item,
                                            int position, long id)
                    {
                        Services bodyservice = listAdapter.getItem(position);
                        Log.e("CHECKADAPTOR", String.valueOf(bodyservice));
                        bodyservice.toggleChecked();
                        ViewHolder viewHolder =(ViewHolder) item
                                .getTag();
                        viewHolder.getCheckBox().setChecked(bodyservice.isChecked());
                    }
                });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(popup_hr_removal.this,popup_hair.class);
                startActivity(intent);

            }
        });






        check.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View view)
            {
                JSONArray ja = new JSONArray();
                for (int i = 0; i < listAdapter.getCount(); i++)
                {
                    Services hairremoval = listAdapter.getItem(i);
                    if (hairremoval.isChecked())
                    {
                        JSONObject jo = new JSONObject();
                        try {
                            jo.put("category_name", hairremoval.getName());
                            jo.put("category_id",hairremoval.getCategory_id());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        ja.put(jo);



                    }
                }
                final JSONObject params = new JSONObject();
                try {
                    params.put("categories",ja);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                Intent intent = new Intent(popup_hr_removal.this,add_employee.class);
                startActivity(intent);


            }
        });


    }
    private static class hairremovalArrayAdapter extends ArrayAdapter<Services>
    {

        private LayoutInflater inflater;
        private List<Services>bodylist;
        private Context context;

        public hairremovalArrayAdapter(Context context, int resourceId, List<Services> hairremovallist)
        {
            super(context, R.layout.customhair_removal, hairremovallist);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.bodylist = hairremovallist;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // SERVICES to display
            Services hairremoval = (Services) this.getItem(position);

            // The child views in each row.
            CheckBox checkBox;
            TextView textView;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.customhair_removal,null );

                // Find the child views.
                textView = (TextView) convertView
                        .findViewById(R.id.hairremovaltextview);
                checkBox = (CheckBox) convertView.findViewById(R.id.checkBox6);

                // Optimization: Tag the row with it's child views, so we don't
                // have to
                // call findViewById() later when we reuse the row.
                convertView.setTag(new ViewHolder(textView, checkBox));

                // If CheckBox is toggled, update the planet it is tagged with.
                checkBox.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        CheckBox cb = (CheckBox) v;
                        Services massage = (Services) cb.getTag();
                        massage.setChecked(cb.isChecked());
                    }
                });
            }
            // Reuse existing row view
            else
            {
                // Because we use a ViewHolder, we avoid having to call
                // findViewById().
                ViewHolder viewHolder = (ViewHolder) convertView
                        .getTag();
                checkBox = viewHolder.getCheckBox();
                textView = viewHolder.getTextView();
            }

            // Tag the CheckBox with the service it is displaying, so that we can
            // access the service in onClick() when the CheckBox is toggled.
            checkBox.setTag(hairremoval);

            // Display Service data
            checkBox.setChecked(hairremoval.isChecked());
            textView.setText(hairremoval.getName());

            return convertView;
        }

    }

}