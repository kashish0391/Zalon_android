package com.zalonstyles.app.zalon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zalonstyles.app.zalon.Model.ServicesMainmodel;
import com.zalonstyles.app.zalon.Model.viewHolderService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KASHISH on 27-07-2016.
 */
public class ServicesMain extends AppCompatActivity implements View.OnClickListener {
    private Button hair;
    private Button face;
    private Button body;
    private Button massage;
    private Button hair_removal;
    private  Button nail;
    private Button addservice;
    private ListView listView;
    private int service_id=1;
    private List<ServicesMainmodel> servicelist = new ArrayList<>();
    private Context context;
    private ArrayAdapter<ServicesMainmodel> listAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_main);
        hair = (Button) findViewById(R.id.servicehair);
        face = (Button) findViewById(R.id.serviceface);
        body = (Button) findViewById(R.id.servicebody);
        massage = (Button) findViewById(R.id.servicemassage);
        nail = (Button) findViewById(R.id.servicenail);
        hair_removal = (Button) findViewById(R.id.servicehairremoval);
        addservice = (Button) findViewById(R.id.addservicebtn);
        listView = (ListView) findViewById(R.id.listviewservice);
        hair.setOnClickListener(this);
        face.setOnClickListener(this);
        body.setOnClickListener(this);
        massage.setOnClickListener(this);
        nail.setOnClickListener(this);
        hair_removal.setOnClickListener(this);
        addservice.setOnClickListener(this);
        servicenetworking(1,"http://zalonstyle.in:8080/service/get_service_lineup");

    }

    public  void servicenetworking(int serviceid,String URL){
        listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);
        final int haslineup = 1;
        service_id = serviceid;
        servicelist.clear();
         listView.setAdapter(null);
        listAdapter.notifyDataSetChanged();



        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        final JSONObject params = new JSONObject();
        try {
            params.put("service_id",serviceid);
            params.put("has_lineup",haslineup);
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
                                    ServicesMainmodel service = new ServicesMainmodel();
                                    service.setcategotry_id(Integer.parseInt(obj.getString("category_id")));
                                    service.setStatus(obj.getString("is_active"));
                                    service.setService_id(obj.getString("service_id"));
                                    service.setName(obj.getString("category_name"));

                                    servicelist.add(service);
                                    Log.e("check22", String.valueOf(servicelist.get(i)));

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



        listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);

       listView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ServicesMainmodel service = listAdapter.getItem(position);
                Intent intent= new Intent(ServicesMain.this,Edit_Service.class);
                String value=  service.getName().toString();
                String Valueid = String.valueOf(service.getCategotry_id());
                String sid = service.getService_id();
                intent.putExtra("valueid", Valueid);
                intent.putExtra("Serviceid",sid);
                startActivity(intent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.service:
                Intent intent = new Intent(ServicesMain.this,ServicesMain.class);
                startActivity(intent);

                return true;

            case R.id.bill:
                Intent intent1 = new Intent(ServicesMain.this,BillingMain.class);
                startActivity(intent1);
                return true;
            case R.id.mar:
                Intent intent2 = new Intent(ServicesMain.this,MarketingMain.class);
                startActivity(intent2);
                return true;
            case R.id.set:
                Intent intent3 = new Intent(ServicesMain.this,SettingsMain.class);
                startActivity(intent3);
                return true;
            case R.id.rep:
                Intent intent4 = new Intent(ServicesMain.this,ReportMain.class);
                startActivity(intent4);
                return true;
            case R.id.cal:
                Intent intent5 = new Intent(ServicesMain.this,CalendarMain.class);
                startActivity(intent5);
                return true;
            case R.id.acccounting:
                Intent intent6 = new Intent(ServicesMain.this,AccountingMain.class);
                startActivity(intent6);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.servicehair:
                //listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);

                service_id=2;
                servicenetworking(2,"http://zalonstyle.in:8080/service/get_service_lineup");
                break;
            case R.id.servicebody:
                service_id=1;
               // listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);

               // servicelist.clear();
               // listView.setAdapter(null);
               // listAdapter.notifyDataSetChanged();
                servicenetworking(1,"http://zalonstyle.in:8080/service/get_service_lineup");
                break;
            case R.id.servicenail:
                service_id=3;
                //listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);
                //servicelist.clear();
               // listView.setAdapter(null);
                //listAdapter.notifyDataSetChanged();
                servicenetworking(3,"http://zalonstyle.in:8080/service/get_service_lineup");

                break;
            case R.id.servicemassage:
                service_id=6;
                //listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);
                //servicelist.clear();
               // listView.setAdapter(null);
               // listAdapter.notifyDataSetChanged();
                servicenetworking(6,"http://zalonstyle.in6:8080/service/get_service_lineup");
                break;
            case R.id.servicehairremoval:
                service_id=5;
                //listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);

                //servicelist.clear();
               // listView.setAdapter(null);
               // listAdapter.notifyDataSetChanged();
                servicenetworking(5,"http://zalonstyle.in:8080/service/get_service_lineup");
                break;
            case R.id.serviceface:
                service_id=4;
                //listAdapter = new serviceArrayAdapter(this,R.layout.customservice_main, servicelist);

                //servicelist.clear();
                //listView.setAdapter(null);
               // listAdapter.notifyDataSetChanged();
                servicenetworking(4,"http://zalonstyle.in:8080/service/get_service_lineup");
                break;
            case R.id.addservicebtn:
                Intent intent = new Intent(ServicesMain.this,New_Service.class);
                     intent.putExtra("service_id",service_id);
                startActivity(intent);


                break;


        }

    }
    private static class serviceArrayAdapter extends ArrayAdapter<ServicesMainmodel>
    {

        private LayoutInflater inflater;
        private List<ServicesMainmodel>servicelist;
        private Context context;
    public serviceArrayAdapter(Context context, int resourceId, List<ServicesMainmodel> servicelist)
    {
        super(context, R.layout.customservice_main, servicelist);
        // Cache the LayoutInflate to avoid asking for a new one each time.
        inflater = LayoutInflater.from((Context) context);
        this.servicelist = servicelist;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // SERVICES to display
        ServicesMainmodel face = (ServicesMainmodel) this.getItem(position);

        // The child views in each row.
        TextView textView;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // Create a new row view
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.customservice_main, null);

            // Find the child views.
            textView = (TextView) convertView
                    .findViewById(R.id.servicetext);


            convertView.setTag(new viewHolderService(textView));
        }else {


            // Reuse existing row view
            // Because we use a ViewHolder, we avoid having to call
            // findViewById().
            viewHolderService viewHolder = (viewHolderService) convertView
                    .getTag();
            textView = viewHolder.getTextView();

        }

        textView.setText(face.getName());

        return convertView;
    }

}

}

