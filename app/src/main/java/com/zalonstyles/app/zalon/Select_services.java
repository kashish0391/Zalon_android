package com.zalonstyles.app.zalon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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
 * Created by KASHISH on 14-09-2016.
 */
public class Select_services extends AppCompatActivity implements View.OnClickListener{
    private Button hair;
    private Button face;
    private Button body;
    private Button massage;
    private Button hair_removal;
    private  Button nail;
    private Button addservice;
    private  Button allservice;
    private Button selectallservice;
    private ListView listView;
    private int service_id=1;
    private List<Services> servicelist = new ArrayList<>();
    private Context context;
    private ArrayAdapter<Services> listAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_services);
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.75),(int)(height*.76));
//        hair = (Button) findViewById(R.id.selctservicehair);
//        face = (Button) findViewById(R.id.selctserviceface);
//        body = (Button) findViewById(R.id.selctservicebody);
//        massage = (Button) findViewById(R.id.selctservicemassage);
//        nail = (Button) findViewById(R.id.selctservicenail);
//        hair_removal = (Button) findViewById(R.id.selctservicehairremoval);
        addservice = (Button) findViewById(R.id.selctaddservicebtn);
//        allservice = (Button) findViewById(R.id.selctserviceall);
        selectallservice = (Button)findViewById(R.id.selctallservicebtn) ;
        listView = (ListView) findViewById(R.id.selctlistviewservice);
//        hair.setOnClickListener(this);
//        face.setOnClickListener(this);
//        body.setOnClickListener(this);
//        massage.setOnClickListener(this);
//        nail.setOnClickListener(this);
//        hair_removal.setOnClickListener(this);
       addservice.setOnClickListener(this);
//        allservice.setOnClickListener(this);
        selectallservice.setOnClickListener(this);
        listAdapter = new bodyArrayAdapter(this,R.layout.customlist_body, servicelist);
        final int haslineup = 1;
        servicelist.clear();
        listView.setAdapter(null);
        listAdapter.notifyDataSetChanged();




        final JSONObject params = new JSONObject();

        try {
            params.put("access_token", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://52.41.72.46:8080/campaign/services",
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes1",response);

                        try {
                            JSONObject jobject = new JSONObject(response);
                            JSONArray payload = jobject.getJSONArray("services");
                            Log.e("payloaddata", String.valueOf(payload));
                            for (int i = 0; i < payload.length(); i++) {
                                try{
                                    JSONObject obj = payload.getJSONObject(i);
                                    Services service = new Services();
                                    service.setcategotry_id(Integer.parseInt(obj.getString("category_id")));

                                    service.setName(obj.getString("category_name"));
                                    service.setStatus(obj.getString("status"));
                                    if(obj.getString("status").equals("true")){
                                        service.setChecked(true);
                                    }

                                    servicelist.add(service);


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



        listAdapter = new bodyArrayAdapter(this,R.layout.customservice_main, servicelist);

        listView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Services nailservice = listAdapter.getItem(position);
                Log.e("CHECKADAPTOR", String.valueOf(nailservice));
                nailservice.toggleChecked();
                ViewHolder viewHolder =(ViewHolder) view
                        .getTag();
                viewHolder.getCheckBox().setChecked(nailservice.isChecked());
            }
        });


        addservice.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View view)
            {
                JSONArray ja = new JSONArray();
                for (int i = 0; i < listAdapter.getCount(); i++)
                {
                    Services nail = listAdapter.getItem(i);
                    if (nail.isChecked())
                    {
                        JSONObject jo = new JSONObject();
                        try {
                            jo.put("category_name", nail.getName());
                            jo.put("category_id",nail.getCategory_id());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        ja.put(jo);



                    }
                }
                final JSONObject params = new JSONObject();
                try {
                    params.put("info",ja);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    params.put("access_token", value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest SR = new StringRequest(Request.Method.POST, "http://52.41.72.46:8080/campaign/createTempData",
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
                Intent intent = new Intent(Select_services.this,MarketingMain.class);
                startActivity(intent);


            }
        });
    }






    @Override
    public void onClick(View v) {
        switch (v.getId()) {

//            case R.id.selctservicehair:
//                //listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);
//
//                service_id=2;
//                servicenetworking(2,"http://52.41.72.46:8080/campaign/services");
//                break;
//            case R.id.selctservicebody:
//                service_id=1;
//                // listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);
//
//                // servicelist.clear();
//                // listView.setAdapter(null);
//                // listAdapter.notifyDataSetChanged();
//                servicenetworking(1,"http://52.41.72.46:8080/campaign/services");
//                break;
//            case R.id.selctservicenail:
//                service_id=3;
//                //listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);
//                //servicelist.clear();
//                // listView.setAdapter(null);
//                //listAdapter.notifyDataSetChanged();
//                servicenetworking(3,"http://52.41.72.46:8080/campaign/services");
//
//                break;
//            case R.id.selctservicemassage:
//                service_id=6;
//                //listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);
//                //servicelist.clear();
//                // listView.setAdapter(null);
//                // listAdapter.notifyDataSetChanged();
//                servicenetworking(6,"http://52.41.72.46:8080/campaign/services");
//                break;
//            case R.id.selctservicehairremoval:
//                service_id=5;
//                //listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);
//
//                //servicelist.clear();
//                // listView.setAdapter(null);
//                // listAdapter.notifyDataSetChanged();
//                servicenetworking(5,"http://52.41.72.46:8080/campaign/services");
//                break;
//            case R.id.selctserviceface:
//                service_id=4;
//                //listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);
//
//                //servicelist.clear();
//                //listView.setAdapter(null);
//                // listAdapter.notifyDataSetChanged();
//                servicenetworking(4,"http://52.41.72.46:8080/campaign/services");
//                break;
//            case R.id.selctaddservicebtn:
//
//
//                Intent intent = new Intent(Select_services.this,MarketingMain.class);
//                startActivity(intent);
//                break;
//            case R.id.selctserviceall:
//                listAdapter = new bodyArrayAdapter(this,R.layout.customlist_body, servicelist);
//
//        servicelist.clear();
//        listView.setAdapter(null);
//        listAdapter.notifyDataSetChanged();
//                service_id=7;
//                servicenetworking(7,"http://52.41.72.46:8080/campaign/services");
//                break;
            case R.id.selctallservicebtn:

                for ( int i=0; i < listAdapter.getCount(); i++) {

                   Services bodyservice = listAdapter.getItem(i);
                    bodyservice.setChecked(true);
                  listView.setItemChecked(i, true);
                   listAdapter.notifyDataSetChanged();

                }

                break;
        }
        

    }



    private static class bodyArrayAdapter extends ArrayAdapter<Services>
    {

        private LayoutInflater inflater;
        private List<Services> naillist;
        private Context context;

        public bodyArrayAdapter(Context context, int resourceId, List<Services> naillist)
        {
            super(context, R.layout.customlist_body, naillist);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.naillist = naillist;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // SERVICES to display
            Services nail = (Services) this.getItem(position);

            // The child views in each row.
            CheckBox checkBox;
            TextView textView;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.customlist_nail,null );

                // Find the child views.
                textView = (TextView) convertView
                        .findViewById(R.id.nailtextview);
                checkBox = (CheckBox) convertView.findViewById(R.id.checkBox2);

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
                        Services nail = (Services) cb.getTag();
                        nail.setChecked(cb.isChecked());
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
            checkBox.setTag(nail);
            if(nail.getStatus().equals("true")){
                checkBox.setChecked(true);
                textView.setText(nail.getName());



            }else {
                checkBox.setChecked(nail.isChecked());
                textView.setText(nail.getName());}

//            checkBox.setChecked(nail.isChecked());
//            textView.setText(nail.getName());
            if(String.valueOf(nail.getCategory_id()).equals("1111")){
                textView.setTextColor(Color.BLACK);
                textView.setText(nail.getName());
//                checkBox.setVisibility(View.GONE);
                int color = Color.argb( 200, 255, 64, 64 );

            }else  {
                textView.setText(nail.getName());
                textView.setTextColor(Color.BLUE);

            }

            // Display Service data

            return convertView;
        }

    }

}
