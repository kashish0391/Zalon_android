package com.zalonstyles.app.zalon;

/**
 * Created by KASHISH on 19-07-2016.
 */

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

public  class popup_face extends AppCompatActivity {
    public static final String URL =  "http://52.41.72.46:8080/service/get_category";
    public static final String URL1 = " http://52.41.72.46:8080/service/create_service_lineup";
    public final String serviceid = "4";
    private ListView mainListView;
    private Button check;
    private Button back;
    private List<Services> facelist = new ArrayList<>();
    private Context context;
    private ArrayAdapter<Services> listAdapter;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_face);
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.75),(int)(height*.76));
        Log.e("accesslog",value);
        mainListView = (ListView) findViewById(R.id.listView5);
        check = (Button) findViewById(R.id.button5);
        back = (Button)findViewById(R.id.buttonback5);

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
                                    facelist.add(service);
                                    Log.e("check2", String.valueOf(facelist.get(i)));






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
        listAdapter = new faceArrayAdapter(this,R.layout.customlist_face, facelist);

        mainListView.setAdapter(listAdapter);


        mainListView
                .setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View item,
                                            int position, long id)
                    {
                        Services faceservice = listAdapter.getItem(position);
                        Log.e("CHECKADAPTOR", String.valueOf(faceservice));
                        faceservice.toggleChecked();
                        ViewHolder viewHolder =(ViewHolder) item
                                .getTag();
                        viewHolder.getCheckBox().setChecked(faceservice.isChecked());
                    }
                });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(popup_face.this,popup_hair.class);
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
                    Services face = listAdapter.getItem(i);
                    if (face.isChecked())
                    {
                        JSONObject jo = new JSONObject();
                        try {
                            jo.put("category_name", face.getName());
                            jo.put("category_id",face.getCategory_id());
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
                Intent intent = new Intent(popup_face.this,popup_massage.class);
                startActivity(intent);


            }
        });


    }
    private static class faceArrayAdapter extends ArrayAdapter<Services>
    {

        private LayoutInflater inflater;
        private List<Services> facelist;
        private Context context;

        public faceArrayAdapter(Context context, int resourceId, List<Services> facelist)
        {
            super(context, R.layout.customlist_face, facelist);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.facelist = facelist;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // SERVICES to display
            Services face = (Services) this.getItem(position);

            // The child views in each row.
            CheckBox checkBox;
            TextView textView;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.customlist_face,null );

                // Find the child views.
                textView = (TextView) convertView
                        .findViewById(R.id.facetextview);
                checkBox = (CheckBox) convertView.findViewById(R.id.checkBox5);

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
                        Services hair = (Services) cb.getTag();
                        hair.setChecked(cb.isChecked());
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
            checkBox.setTag(face);

            // Display Service data
            checkBox.setChecked(face.isChecked());
            textView.setText(face.getName());

            return convertView;
        }

    }

}






