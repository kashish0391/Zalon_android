package com.zalonstyles.app.zalon;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zalonstyles.app.zalon.Model.ViewHolder3;
import com.zalonstyles.app.zalon.Model.campaign;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Campaign_status extends AppCompatActivity{
    public static final String URL =  "http://zalonstyle.in:8080/campaign/getAllCampaigns";

    private ListView listView;
    private List<campaign> list = new ArrayList<>();
    private Context context;
    private ArrayAdapter<campaign> listAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.campaign_status);
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.75),(int)(height*.76));
        Log.e("accesslog",value);
        listView = (ListView) findViewById(R.id.campaignlistView);

        final JSONObject params = new JSONObject();

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
                            JSONArray payload = jobject.getJSONArray("campaign");
                            Log.e("payloaddata", String.valueOf(payload));
                            for (int i = 0; i < payload.length(); i++) {
                                try{
                                    JSONObject obj = payload.getJSONObject(i);
                                    campaign service = new campaign();
                                    service.setName(obj.getString("campaign_name"));

                                    service.setStatus(obj.getString("is_active"));
                                    list.add(service);
                                    Log.e("check42", String.valueOf(list.get(i)));






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
        listAdapter = new ProfleArrayAdapter(this,R.layout.customlist_massage, list);

        listView.setAdapter(listAdapter);


        listView
                .setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View item,
                                            int position, long id)
                    {
                        campaign massageservice = listAdapter.getItem(position);
                        Log.e("CHECKADAPTOR", String.valueOf(massageservice));

                    }
                });


    }
    private static class ProfleArrayAdapter extends ArrayAdapter<campaign>
    {

        private LayoutInflater inflater;
        private List<campaign> profilelist;
        private Context context;

        public ProfleArrayAdapter(Context context, int resourceId, List<campaign> profilelist)
        {
            super(context, R.layout.customprofilelist, profilelist);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.profilelist = profilelist;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // SERVICES to display
            campaign face = (campaign) this.getItem(position);

            // The child views in each row.
            TextView textView1;
            TextView textView;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.customprofilelist,null );

                // Find the child views.
                textView = (TextView) convertView
                        .findViewById(R.id.profiletextview);
                textView1 = (TextView) convertView.findViewById(R.id.profiletextview1);

                // Optimization: Tag the row with it's child views, so we don't
                // have to
                // call findViewById() later when we reuse the row.
                convertView.setTag(new ViewHolder3(textView, textView1));}

            else
            {
                // Because we use a ViewHolder, we avoid having to call
                // findViewById().
                ViewHolder3 viewHolder = (ViewHolder3) convertView
                        .getTag();
                textView1 = viewHolder.getTextview1();
                textView = viewHolder.getTextView();
            }




            // Display Service data
            textView1.setText(face.getStatus());
            textView.setText(face.getName());

            return convertView;
        }

    }
}
