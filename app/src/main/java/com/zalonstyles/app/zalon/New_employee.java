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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zalonstyles.app.zalon.Model.ViewHolder;
import com.zalonstyles.app.zalon.Model.editemp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KASHISH on 03-09-2016.
 */
public class New_employee extends AppCompatActivity {
    private EditText Title;
    private EditText Mobile;
    private EditText Email;
    private EditText Name;
    private Spinner spinner;
    private ArrayList<String> students;
    private String   id;
    private RadioButton r1;
    private RadioButton r2;
    private   ArrayAdapter<String> arrayadapter;
    private ImageView imageview;
    private List<editemp> emplist = new ArrayList<>();
    private Context context;
    private ArrayAdapter<editemp> listAdapter;
    private String spinnerValue;


    private static String url="http://zalonstyle.in:8080/get_salon_categories";
    private static String URL="http://zalonstyle.in:8080/add_new_staff";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_employee);
        ListView listview = (ListView) findViewById(R.id.newemplv);
        imageview = (ImageView) findViewById(R.id.newempiv);
        Title = (EditText)  findViewById(R.id.newempet);
        Mobile = (EditText)  findViewById(R.id.newempet1);
        Email= (EditText)  findViewById(R.id.newempet2);
        Name = (EditText)  findViewById(R.id.newemp);
        spinner = (Spinner) findViewById(R.id.newempspinner);
        students = new ArrayList<String>();

        r1 = (RadioButton) findViewById(R.id.radiobtn0) ;
        r2 = (RadioButton) findViewById(R.id.radiobtn01);
//       if(r1.isChecked()){
//           r2.setChecked(false);
//       }else {
//           r2.setChecked(true);
//           r1.setChecked(false);
//       }
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r2.setChecked(false);
                r1.setChecked(true);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r1.setChecked(false);
                r2.setChecked(true);
            }
        });
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        final JSONObject params = new JSONObject();

        try {
            params.put("access_token", value);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes1",response);

                        try {
                            JSONObject jobject = new JSONObject(response);
                            JSONObject payload = jobject.getJSONObject("staff");
                            JSONArray payload1 = payload.getJSONArray("categories");
                            JSONArray payload2 = payload.getJSONArray("services");
                            Log.e("payloaddata2", String.valueOf(payload2));

                            Log.e("payloaddata1", String.valueOf(payload1));
                            for (int i = 0; i < payload1.length(); i++) {
                                try{
                                    JSONObject obj = payload1.getJSONObject(i);
                                    students.add(obj.getString("category"));
                                    Log.v("logdata",students.get(i));

                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            for (int i = 0; i < payload2.length(); i++) {
                                try{
                                    JSONObject obj = payload2.getJSONObject(i);
                                    editemp service = new editemp();
                                    service.setCategoryid((obj.getString("category_id")));
                                    service.setName(obj.getString("category_name"));

                                    emplist.add(service);
                                    Log.e("check2", String.valueOf(emplist.get(i)));






                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (payload.getString("gender").equals("male")){
                                r1.setChecked(true);
                                imageview.setImageResource(R.drawable. male);
                            }else {
                                r2.setChecked(true);
                                imageview.setImageResource(R.drawable.female);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }listAdapter.notifyDataSetChanged();
                        spinner.setAdapter(arrayadapter=new ArrayAdapter<String>(New_employee.this, android.R.layout.simple_spinner_dropdown_item, students));
                        arrayadapter.setDropDownViewResource(R.layout.textviewspinner);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                spinnerValue = spinner.getSelectedItem().toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                spinnerValue ="";

                            }
                        });
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
        listAdapter = new editempArrayAdapter(this,R.layout.customeditemp, emplist);

        listview.setAdapter(listAdapter);


        listview
                .setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View item,
                                            int position, long id)
                    {
                        editemp nailservice = listAdapter.getItem(position);
                        Log.e("CHECKADAPTOR", String.valueOf(nailservice));
                        nailservice.toggleChecked();
                        ViewHolder viewHolder =(ViewHolder) item
                                .getTag();
                        viewHolder.getCheckBox().setChecked(nailservice.isChecked());
                    }
                });






    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SAVEPROFILE:
                try {
                    sendingdata();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }
    private void sendingdata () throws JSONException {
        String name = Name.getText().toString().trim();
        String title = Title.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String mob = Mobile.getText().toString().trim();


        String gender;

        JSONArray ja = new JSONArray();
        for (int i = 0; i < listAdapter.getCount(); i++)
        {
            editemp massage = listAdapter.getItem(i);
            if (massage.isChecked())
            {
                JSONObject jo = new JSONObject();
                try {
                    jo.put("category_name", massage.getName());
                    jo.put("category_id",massage.getCategoryid());
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                ja.put(jo);



            }
        }
        if (r1.isChecked()){
            gender = "male";
        }else{
            gender = "female";
        }


        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));

        final JSONObject params = new JSONObject();
        params.put("access_token", value);
        params.put("staff_id",id);
        params.put("category",ja);
        params.put("name", name);
        params.put("email",email);
        params.put("mobile", mob);
        params.put("email",email);
        params.put("title",title);
        params.put("category_name",spinnerValue);
        params.put("gender",gender);




        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.v("logresponse", response.toString());
                        Intent intent = new Intent(New_employee.this,Emp_Management.class);
                        startActivity(intent);





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

                Log.v("sellableParams", params1.toString());

                return params1;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private static class editempArrayAdapter extends ArrayAdapter<editemp>
    {

        private LayoutInflater inflater;
        private List<editemp> naillist;
        private Context context;

        public editempArrayAdapter(Context context, int resourceId, List<editemp> naillist)
        {
            super(context, R.layout.customeditemp, naillist);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.naillist = naillist;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // SERVICES to display
            editemp nail = (editemp) this.getItem(position);

            // The child views in each row.
            CheckBox checkBox;
            TextView textView;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.customeditemp,null );

                // Find the child views.
                textView = (TextView) convertView
                        .findViewById(R.id.tveditemp);
                checkBox = (CheckBox) convertView.findViewById(R.id.ckeditemp);

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
                        editemp nail = (editemp) cb.getTag();
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

            // Display Service data

                checkBox.setChecked(nail.isChecked());
                textView.setText(nail.getName());

            return convertView;
        }

    }

}

