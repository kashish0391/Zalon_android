package com.zalonstyles.app.zalon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zalonstyles.app.zalon.Model.viewHolder1;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KASHISH on 20-07-2016.
 */
public class add_employee extends AppCompatActivity {

    private EditText EmployeeName;
    private EditText EmolyeeNo;
    private Button buttonSubmit;
    private Button check;
    private String empname;
    private String empmobileno;
    private EditText category;
    private ListView emplist;
    Spinner SPINNER;
    String GETTEXT;
    private String spinnerValue;

    List<String> stringlist;
    ArrayAdapter<String> arrayadapter;

    private Button addcategory;
    String[] spinnerItems = new String[]{"Junior Stylist","Senior Stylist","Art Director"};
    private List<emplyeeModel> employeeList = new ArrayList<>();
    private ArrayAdapter<emplyeeModel> listAdapter;


    public static final String SUBMIT_URL = "http://52.41.72.46:8080/salon/add_staff";
  public static final String URL1 = " http://52.41.72.46:8080/salon/delete_staff";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee);
        check = (Button) findViewById(R.id.buttoncontinue);
        category = (EditText) findViewById(R.id.editTextempcat);
        emplist = (ListView)findViewById(R.id.listviewemp);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.85),(int)(height*.76));
        EmployeeName = (EditText) findViewById(R.id.editTextempname);
        EmolyeeNo = (EditText) findViewById(R.id.editTextmobieno);
        addcategory = (Button) findViewById(R.id.buttonspinnercat);
        buttonSubmit = (Button)findViewById(R.id.buttonsubmit);
        SPINNER = (Spinner) findViewById(R.id.spinneraddemp);
        stringlist = new ArrayList<>(Arrays.asList(spinnerItems));

        arrayadapter = new ArrayAdapter<String>(add_employee.this,R.layout.textaddempspinner,stringlist );

        arrayadapter.setDropDownViewResource(R.layout.textaddempspinner);

        SPINNER.setAdapter(arrayadapter);
        SPINNER.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue = SPINNER.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue ="Normal Category";

            }
        });
        addcategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                GETTEXT = category.getText().toString();

                stringlist.add(GETTEXT);
                category.setText("");

                arrayadapter.notifyDataSetChanged();


                Toast.makeText(add_employee.this, "Item Added", Toast.LENGTH_LONG).show();
            }
        });


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Submit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        check.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(add_employee.this,CalendarMain.class);
                startActivity(intent);


            }
        });

    }
    private void Submit() throws JSONException {
        empname = EmployeeName.getText().toString().trim();
        empmobileno = EmolyeeNo.getText().toString().trim();
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        emplyeeModel empobj = new emplyeeModel();
        empobj.setName(empname);
        empobj.setNumber(empmobileno);
        empobj.setCategory(spinnerValue);
        employeeList.add(empobj);
        final JSONObject params = new JSONObject();
        params.put("access_token", value);
        JSONObject param = null;
        param = new JSONObject();
        param.put("name", empname);
        param.put("category",spinnerValue);
        param.put("mobile", empmobileno);
        params.put("staff_data", param);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SUBMIT_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {


                        try {
                            if (new JSONObject(response).getString("status").equals("success")) {


                                Toast.makeText(getApplicationContext(), " Successfully added", Toast.LENGTH_LONG).show();
                                EmolyeeNo.setText("");
                                EmployeeName.setText("");


                            } else {
                                Toast.makeText(getApplicationContext(), " USERNAME ALREADY EXISTS", Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
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

                Log.v("EMPParams", params1.toString());

                return params1;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        listAdapter = new employeeArrayAdapter(this,R.layout.custom_employee, employeeList);
        emplist.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();


       emplist
                .setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View item,
                                            int position, long id)
                    {
                        emplyeeModel employee1 = listAdapter.getItem(position);
                        Log.e("CHECKADAPTOR", String.valueOf(employee1));
                        employee1.toggleClicked();
                        viewHolder1 viewHolder =(viewHolder1) item
                                .getTag();
                        viewHolder.getButton().setSelected(employee1.isClicked());
                    }
                });










    }

    private static class employeeArrayAdapter extends ArrayAdapter<emplyeeModel>
    {

        private LayoutInflater inflater;
        private List<emplyeeModel> emplist;
        private Context context;

        public employeeArrayAdapter(Context context, int resourceId, List<emplyeeModel> emplist)
        {
            super(context, R.layout.custom_employee, emplist);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.emplist = emplist;
            this.context = context;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            // SERVICES to display
            emplyeeModel emp = (emplyeeModel) this.getItem(position);

            // The child views in each row.
            TextView textView1;
            TextView textView2;
            TextView textView3;
            Button button;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.custom_employee,null );

                // Find the child views.
                textView1 = (TextView) convertView
                        .findViewById(R.id.emptextview1);
                textView2 = (TextView) convertView.findViewById(R.id.emptextview2);
                textView3 = (TextView) convertView.findViewById(R.id.emptextview3);


                button = (Button) convertView.findViewById(R.id.buttonDelete);

                // Optimization: Tag the row with it's child views, so we don't
                // have to
                // call findViewById() later when we reuse the row.
                convertView.setTag(new viewHolder1(textView1, textView2,button,textView3));




                // If CheckBox is toggled, update the planet it is tagged with.

            }
            // Reuse existing row view
            else
            {
                // Because we use a ViewHolder, we avoid having to call
                // findViewById().
                viewHolder1 viewHolder = (viewHolder1) convertView
                        .getTag();
               textView1 = viewHolder.getTextView();
               textView2 = viewHolder.getTextview1();
                textView3 = viewHolder.getTextView3();
               button = viewHolder.getButton();
                button.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        Button cb = (Button) v;
                        emplyeeModel massage = (emplyeeModel) cb.getTag();
                        massage.setClicked(cb.isSelected());
                    }
                });
            }



            // Display Service data
            textView1.setText(emp.getName());
            textView2.setText(emp.getNumber());
            textView3.setText(emp.getCategory());
            button.setTag(emp);
            button.setSelected(emp.isClicked());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button cb = (Button) v;
                    emplyeeModel massage = (emplyeeModel) cb.getTag();
                    massage.setClicked(cb.isSelected());


                    String name = emplist.get(position).getName();
                    String number = emplist.get(position).getNumber();

                    emplist.remove(position);
                    SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getContext());
                    String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
                    Log.e("accesslog",value);
                    final JSONObject params = new JSONObject();

                    try {
                        params.put("name",name);
                        params.put("access_token", value);
                        params.put("mobile",number);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,URL1,
                            new Response.Listener<String>(){

                                @Override
                                public void onResponse(String response) {

                                    Log.v("vinresponse",response);



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

                            Log.v("xxx", params1.toString());

                            return params1;

                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(stringRequest);
                    notifyDataSetChanged();


                }
            });

            return convertView;
        }

    }

}
