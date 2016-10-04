package com.zalonstyles.app.zalon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import com.zalonstyles.app.zalon.Model.ProfileModel;
import com.zalonstyles.app.zalon.Model.ProfileModel1;
import com.zalonstyles.app.zalon.Model.ViewHolder3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KASHISH on 30-07-2016.
 */
public class Profile extends AppCompatActivity {
    private TextView ProfileName;
    private TextView ProfileMob;
    private TextView ProfileAdd;
    private TextView ProfileWebsite;
    private TextView ProfileEmail;
    private TextView ProfilePayment;
    private String profilename;
    private String profilemob;
    private String profileadd;
    private String profileemail;
    private String profilewebsite;
    private String profilepayments;
    private ListView profilelist;
    private List<ProfileModel1> profileList = new ArrayList<>();
    private ArrayAdapter<ProfileModel1> listAdapter;
    public static final String URL =  "http://zalonstyle.in:8080/salon/get_profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        ProfileName = (TextView) findViewById(R.id.profiletv1);
        ProfileMob = (TextView) findViewById(R.id.profiletv2);
        ProfileAdd = (TextView) findViewById(R.id.profiletv3);
        ProfileWebsite = (TextView) findViewById(R.id.profiletv4);
        ProfilePayment = (TextView) findViewById(R.id.profiletv5);
        ProfileEmail = (TextView)findViewById(R.id.profiletv6);
        profilelist =(ListView)findViewById(R.id.profilelv);

        final JSONObject params = new JSONObject();
        try {
            String  value1 ="0";
            params.put("access_token", value);
            params.put("is_edit",value1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes11",response);
                        ProfileModel profileModel = new ProfileModel();

                       try {
                            JSONObject jobject = new JSONObject(response);
                           JSONObject obj = jobject.getJSONObject("information");

                           profileModel.setProfilemodel(obj.getString("name"));
                           profileModel.setEmail(obj.getString("email"));
                           profileModel.setMobile(obj.getString("mobile"));
                           profileModel.setWebsite(obj.getString("website"));
                           profileModel.setLogo(obj.getString("logo"));
                           profileModel.setAddress(obj.getString("address"));
                           profileModel.setProfilemodel(obj.getString("payment_methods"));

                           ProfileName.setText(obj.getString("name"));
                          ProfileEmail.setText(obj.getString("email"));
                           ProfilePayment.setText(obj.getString("payment_methods"));
                           ProfileWebsite.setText(obj.getString("website"));
                           ProfileAdd.setText(obj.getString("address"));
                           ProfileMob.setText(obj.getString("mobile"));
                           JSONArray jsonArray= jobject.getJSONArray("timing");
                           for (int i =0;i<jsonArray.length();i++){
                               try{
                                   ProfileModel1 profileModel1 = new ProfileModel1();
                                   JSONObject obj1 = jsonArray.getJSONObject(i);
                                   profileModel1.setDay(obj1.getString("day"));
                                   profileModel1.setTime(obj1.getString("timing"));

                                   profileList.add(profileModel1);
                                   Log.e("check21", String.valueOf(profileList.get(i)));






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
        listAdapter = new ProfleArrayAdapter(this,R.layout.customprofilelist, profileList);

        profilelist.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.EDITPROFILE:
                Intent intent = new Intent(Profile.this,EditProfile.class);
                startActivity(intent);

                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }




    private static class ProfleArrayAdapter extends ArrayAdapter<ProfileModel1>
    {

        private LayoutInflater inflater;
        private List<ProfileModel1> profilelist;
        private Context context;

        public ProfleArrayAdapter(Context context, int resourceId, List<ProfileModel1> profilelist)
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
            ProfileModel1 face = (ProfileModel1) this.getItem(position);

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
            textView1.setText(face.getTime());
            textView.setText(face.getDay());

            return convertView;
        }

    }

}
