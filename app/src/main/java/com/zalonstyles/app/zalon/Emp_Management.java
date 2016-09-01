package com.zalonstyles.app.zalon;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zalonstyles.app.zalon.Model.empmodel;
import com.zalonstyles.app.zalon.Model.viewholderemp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KASHISH on 01-09-2016.
 */
public class Emp_Management extends AppCompatActivity {

    private ListView lv;
    private List<empmodel> emplist = new ArrayList<>();
    private Context context;
    private ArrayAdapter<empmodel> listAdapter;
    public static final String URL =  "http://52.41.72.46:8080/get_all_staff";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emp_management);
        lv = (ListView) findViewById(R.id.emplv);
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        final String staffid = "0";


        final JSONObject params = new JSONObject();

        try {
            params.put("access_token", value);
            params.put("staff_id",staffid);

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
                            JSONArray payload = jobject.getJSONArray("staff");
                            Log.e("payloaddata", String.valueOf(payload));
                            for (int i = 0; i < payload.length(); i++) {
                                try{
                                    JSONObject obj = payload.getJSONObject(i);
                                    empmodel service = new empmodel();
                                    service.setName(obj.getString("name"));
                                    service.setCategory(obj.getString("category"));
                                    service.setId(obj.getString("staff_id"));
                                    emplist.add(service);
                                    Log.e("check2", String.valueOf(emplist.get(i).getName()));
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
        listAdapter = new empArrayAdapter(this,R.layout.customemp_mangement, emplist);

        lv.setAdapter(listAdapter);
    }

    private static class empArrayAdapter extends ArrayAdapter<empmodel>
    {

        private LayoutInflater inflater;
        private List<empmodel> emplist;
        private Context context;
        public empArrayAdapter(Context context, int resourceId, List<empmodel> emplist)
        {
            super(context, R.layout.customemp_mangement, emplist);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.emplist = emplist;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // SERVICES to display
            empmodel face = (empmodel) this.getItem(position);

            // The child views in each row.
            TextView textView;
            TextView textView1;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.customemp_mangement, null);

                // Find the child views.
                textView = (TextView) convertView
                        .findViewById(R.id.customemptv);
                textView1 = (TextView) convertView
                        .findViewById(R.id.customemptv1);


                convertView.setTag(new viewholderemp(textView,textView1));
            }else {


                // Reuse existing row view
                // Because we use a ViewHolder, we avoid having to call
                // findViewById().
                viewholderemp viewHolder = (viewholderemp) convertView
                        .getTag();
                textView = viewHolder.getTextView();
                textView1 = viewHolder.getTextView1();

            }

            textView.setText(face.getName());
            textView1.setText(face.getCategory());

            return convertView;
        }

    }


}
