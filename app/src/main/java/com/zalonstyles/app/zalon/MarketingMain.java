package com.zalonstyles.app.zalon;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
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
import com.zalonstyles.app.zalon.Model.custommarket;
import com.zalonstyles.app.zalon.Model.viewHolder1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KASHISH on 27-07-2016.
 */
public class MarketingMain extends AppCompatActivity {
    private Button servicebtn;
    String[] spinnerItems = new String[]{"Last Visited", "First Visited", "Monthly Spending", "Gender", "Monthly Visits", "New Customer", "No Visits", "Birthday", "Spending Reduced", "Service Consumed"};
    String[] spinnerItems1 = new String[]{"% Disc", "cash Disc", "Loyality", "Bogo", "Btgo"};
    String[] spinnerItems2 = new String[]{"usage", "1", "2", "3", "4", "Multiple"};

    private Spinner rules;
    private Spinner factor;
    private Spinner value1;
    private Spinner usage;
    private EditText val;
    private Spinner distype;
    private Button validity;
    private Button add;
    private EditText minvalue;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private TextView dateView1;

    private int year, month, day;
    private String spinnerValue1;
    private String spinnerValue = "empty";
    private String spinnerValue2;
    private String spinnerValue3;
    private String spinnerValue4;
    private Button cont;
    private CheckBox mon;
    private CheckBox tue;
    private CheckBox wed;
    private CheckBox thur;
    private CheckBox fri;
    private CheckBox sat;
    private CheckBox sun;
    private RadioButton r1;
    private RadioButton r2;
    private Date maxDate;
    private Date minDate;
    private String dateInString;


    private ListView listView;
    private List<custommarket> marketList = new ArrayList<>();
    private ArrayAdapter<custommarket> listAdapter;
    private ArrayAdapter<String> arrayadapter1;
    private ArrayAdapter<String> arrayadapter2;
    private ArrayAdapter<String> arrayadapter3;
    private ArrayAdapter<String> arrayadapter4;

    private EditText campaign;

    private ArrayList<String> marketspin;
    private ArrayList<String> marketspin1;
    private int myear;
    private int mmonth;
    private int mday;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketing_main);
        mon = (CheckBox) findViewById(R.id.editchkbox);
        tue = (CheckBox) findViewById(R.id.editchkbox1);
        wed = (CheckBox) findViewById(R.id.editchkbox2);
        thur = (CheckBox) findViewById(R.id.editchkbox3);
        fri = (CheckBox) findViewById(R.id.editchkbox4);
        sat = (CheckBox) findViewById(R.id.editchkbox5);
        sun = (CheckBox) findViewById(R.id.editchkbox6);
        r1 = (RadioButton) findViewById(R.id.rad1);
        r2 = (RadioButton) findViewById(R.id.rad2);
        minvalue = (EditText) findViewById(R.id.minBillValue);
        datePicker = new DatePicker(this);


        servicebtn = (Button) findViewById(R.id.btnservice);
        validity = (Button) findViewById(R.id.btnmarketing);
        Button start = (Button) findViewById(R.id.btnmarketingstrt);
        rules = (Spinner) findViewById(R.id.spinny1);
        listView = (ListView) findViewById(R.id.listViewmarketing);
        add = (Button) findViewById(R.id.marketadd);
        campaign = (EditText) findViewById(R.id.campname);
        dateView = (TextView) findViewById(R.id.marketingvalidity);
        dateView1 = (TextView) findViewById(R.id.marketingstrt);
        final Calendar c = Calendar.getInstance();
        myear = c.get(Calendar.YEAR);
        mmonth = c.get(Calendar.MONTH);
        mday = c.get(Calendar.DAY_OF_MONTH);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        distype = (Spinner) findViewById(R.id.spinny4);
        factor = (Spinner) findViewById(R.id.spinny2);
        value1 = (Spinner) findViewById(R.id.spinny3);
        val = (EditText) findViewById(R.id.maredit);
        usage = (Spinner) findViewById(R.id.spinny6);
        cont = (Button) findViewById(R.id.marbtnsv);
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
        marketspin = new ArrayList<String>();
        marketspin1 = new ArrayList<String>();
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        listAdapter = new marketArrayAdapter(this, R.layout.customsinventory, marketList);
        listView.setAdapter(listAdapter);
        dateView1.setText(new StringBuilder().append(year).append("-")
                .append(month+1).append("-").append(day));
        Helper.setListViewHeightBasedOnItems(listView);
        showDate(year, month + 1, day);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rules.setAdapter(adapter);
        rules.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue = rules.getSelectedItem().toString();
                factor.setAdapter(arrayadapter2 = new ArrayAdapter<String>(MarketingMain.this, android.R.layout.simple_spinner_item, marketspin));
                factor.setAdapter(null);
                value1.setAdapter(arrayadapter3 = new ArrayAdapter<String>(MarketingMain.this, android.R.layout.simple_spinner_item, marketspin1));
                value1.setAdapter(null);

                marketspin.clear();
                factor.setAdapter(null);
                arrayadapter2.notifyDataSetChanged();
                marketspin1.clear();
                value1.setAdapter(null);
                arrayadapter3.notifyDataSetChanged();

                SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));


                final JSONObject params = new JSONObject();
                try {
                    params.put("rule", spinnerValue);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    params.put("access_token", value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/campaign/getCampaignData",
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {

                                Log.v("updateUPVolleyRes1", response);
                                JSONObject jobject = null;
                                try {
                                    jobject = new JSONObject(response);

                                    JSONArray payload = jobject.getJSONArray("factors");
                                    for (int i = 0; i < payload.length(); i++) {
                                        try {
                                            JSONObject obj = payload.getJSONObject(i);
                                            marketspin.add(obj.getString("factor"));
                                            Log.v("logdata11", marketspin.get(i));

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    JSONArray payload1 = jobject.getJSONArray("values");
                                    for (int i = 0; i < payload1.length(); i++) {
                                        try {
                                            JSONObject obj = payload1.getJSONObject(i);
                                            marketspin1.add(obj.getString("value"));
                                            Log.v("logdata11", marketspin1.get(i));

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                factor.setAdapter(arrayadapter2 = new ArrayAdapter<String>(MarketingMain.this, android.R.layout.simple_spinner_item, marketspin));
                                arrayadapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                factor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        spinnerValue2 = factor.getSelectedItem().toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        spinnerValue2 = "";

                                    }
                                });
                                value1.setAdapter(arrayadapter3 = new ArrayAdapter<String>(MarketingMain.this, android.R.layout.simple_spinner_item, marketspin1));
                                arrayadapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                value1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        spinnerValue3 = value1.getSelectedItem().toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        spinnerValue3 = "";

                                    }
                                });

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

                        Log.v("updateUPVolleyParams6", params1.toString());

                        return params1;

                    }
                };


                requestQueue.add(stringRequest);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue = "empty";

            }
        });
        servicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketingMain.this, Select_services.class);
                startActivity(intent);
            }
        });

        distype.setAdapter(arrayadapter1 = new ArrayAdapter<String>(MarketingMain.this, android.R.layout.simple_spinner_item, spinnerItems1));
        arrayadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        distype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue1 = distype.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue1 = "";

            }
        });
        usage.setAdapter(arrayadapter4 = new ArrayAdapter<String>(MarketingMain.this, android.R.layout.simple_spinner_item, spinnerItems2));
        arrayadapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        usage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue4 = usage.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue4 = "";

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.setListViewHeightBasedOnItems(listView);
                custommarket cmark = new custommarket();
                cmark.setRules(spinnerValue);
                cmark.setFlist(spinnerValue2);
                cmark.setValue(spinnerValue3);
                marketList.add(cmark);
                listAdapter.notifyDataSetChanged();


            }
        });
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        final JSONObject params = new JSONObject();
        try {
            params.put("rule", spinnerValue);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            params.put("access_token", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/campaign/getCampaignData",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes1", response);
                        JSONObject jobject = null;
                        try {
                            jobject = new JSONObject(response);

//                            Log.v("etxt",String.valueOf(payload));
                            campaign.setText(jobject.getString("date"));


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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params1 = new HashMap<String, String>();

                params1.put("payload", params.toString());

                Log.v("updateUPVolleyParams6", params1.toString());

                return params1;

            }
        };


        requestQueue.add(stringRequest);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date strDate = null;
                try {
                    strDate = sdf.parse(dateView.getText().toString());
                    if (System.currentTimeMillis() > strDate.getTime()) {
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String monday;
                String tuesday;
                String Wed;
                String thursday;
                String friday;
                String saturday;
                String sunday;
                String add_rule;
                String date = dateView.getText().toString();
                String minval = minvalue.getText().toString();
                String edittxt = campaign.getText().toString();
                String val1 = val.getText().toString();
                JSONArray ja = new JSONArray();
                for (int i = 0; i < listAdapter.getCount(); i++) {
                    custommarket massage = listAdapter.getItem(i);

                    JSONObject jo = new JSONObject();
                    try {
                        jo.put("rule", massage.getRules());
                        jo.put("factor", massage.getFlist());
                        jo.put("value", massage.getValue());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    ja.put(jo);


                }


                if (mon.isChecked()) {
                    monday = "1";
                } else {
                    monday = "0";
                }
                if (tue.isChecked()) {
                    tuesday = "1";
                } else {
                    tuesday = "0";
                }
                if (wed.isChecked()) {
                    Wed = "1";
                } else {
                    Wed = "0";
                }
                if (thur.isChecked()) {
                    thursday = "1";
                } else {
                    thursday = "0";
                }
                if (fri.isChecked()) {
                    friday = "1";
                } else {
                    friday = "0";
                }
                if (sat.isChecked()) {
                    saturday = "1";
                } else {
                    saturday = "0";
                }
                if (sun.isChecked()) {
                    sunday = "1";
                } else {
                    sunday = "0";
                }
                if (r1.isChecked()) {
                    add_rule = "all";
                } else {
                    add_rule = "any";
                }
                SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
                final JSONObject params = new JSONObject();

                try {
                    params.put("date", date);
                    params.put("access_token", value);
                    params.put("rule_list", ja);
                    params.put("discount_type", spinnerValue1);
                    params.put("disount_value", val1);
                    params.put("campaign_name", edittxt);
                    params.put("add_rule", add_rule);
                    params.put("usage", spinnerValue4);
                    params.put("min_bill", minval);
                    JSONArray paramweek = new JSONArray();
                    paramweek.put(monday);
                    paramweek.put(tuesday);
                    paramweek.put(Wed);
                    paramweek.put(thursday);
                    paramweek.put(friday);
                    paramweek.put(saturday);
                    paramweek.put(sunday);
                    params.put("days_applicable", paramweek);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/campaign/setCampaignData",
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Log.v("logresponse", response.toString());


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

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);


            }
        });

         dateInString = dateView1.getText().toString().trim();
        Log.v("date1111", dateInString);


    }

    private void showDate(int year, int month, int day) {


    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }
    @SuppressWarnings("deprecation")
    public void setDate1(View view) {
        showDialog(9999);

    }
    @SuppressWarnings("deprecation")

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub

           if (id==999){
                // set date picker as current date
                DatePickerDialog _date =   new DatePickerDialog(this, myDateListener, myear,mmonth,
                        mday)

                {


                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        if (year < myear)
                            view.updateDate(myear, mmonth, mday);

                        if (monthOfYear < mmonth && year == myear)
                            view.updateDate(myear, mmonth, mday);

                        if (dayOfMonth < mday && year == myear && monthOfYear == mmonth)
                            view.updateDate(myear, mmonth, mday);


                        view.setMinDate(System.currentTimeMillis() - 1000);

                    }
                };
                _date.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                return _date;


        }else if(id==9999){
               DatePickerDialog _date =   new DatePickerDialog(this, myDateListener1, myear,mmonth,
                       mday)

               {


                   @Override
                   public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                   {
                       if (year < myear){
                           view.updateDate(myear, mmonth, mday);}

                       if (monthOfYear < mmonth && year == myear){
                           view.updateDate(myear, mmonth, mday);}

                       if (dayOfMonth < mday && year == myear && monthOfYear == mmonth){
                           view.updateDate(myear, mmonth, mday);}


                       view.setMinDate(System.currentTimeMillis() - 1000);

                   }
               };
               _date.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

               return _date;
        }
        return null;

//        if (id == 999) {
//
//            return new DatePickerDialog(this, myDateListener, year, month, day);
//
//        }
//        return null;
    }

    public void setMaxDate(Date date){
        maxDate = date;
    }

    public void setMinDate(Date date){
        minDate = date;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {

//

        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            myear = arg1;
            mmonth = arg2;
            mday = arg3;

            // set selected date into textview
            dateView1.setText(new StringBuilder().append(myear).append("-")
                    .append(mmonth).append("-").append(mday));
          arg0.setMinDate(System.currentTimeMillis() - 1000);
//            Calendar cal = Calendar.getInstance();
//            Calendar c = Calendar.getInstance();
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//            //get current date time with Date()
//            Date date = new Date();
//            System.out.println(dateFormat.format(date));
//          cal.set(arg1, arg2+1, arg3);
//            Date currentDate = cal.getTime();
////
////            final Calendar resetCal = cal;
////            if(!minDate.before(currentDate) ){
////                cal.setTime(minDate);
////                arg0.updateDate(resetCal.get(Calendar.YEAR), resetCal.get(Calendar.MONTH), resetCal.get(Calendar.DAY_OF_MONTH));
////
////            }else if(maxDate.before(currentDate)){
////                cal.setTime(maxDate);
////                arg0.updateDate(resetCal.get(Calendar.YEAR), resetCal.get(Calendar.MONTH), resetCal.get(Calendar.DAY_OF_MONTH));
////            }
//
//            // TODO Auto-generated method stub
//            // arg1 = year
//            // arg2 = month
//            // arg3 = day
//
//            if (currentDate.after(date)||currentDate.equals(date)){
//                showDate(arg1,arg2+1,arg3);
//            }else {
//                arg0.updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
//            }

        }
    };
    private DatePickerDialog.OnDateSetListener myDateListener1 = new DatePickerDialog.OnDateSetListener() {

//

        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            myear = arg1;
            mmonth = arg2+1;
            mday = arg3;

            // set selected date into textview

            arg0.setMinDate(System.currentTimeMillis() - 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");

            try {

                Date date1 = sdf.parse(dateInString);
                Log.v("date11", String.valueOf(date1));
                dateView.setText(new StringBuilder().append(myear).append("-")
                        .append(mmonth).append("-").append(mday));

            } catch (ParseException e) {
                e.printStackTrace();
            }

//            Calendar cal = Calendar.getInstance();
//            Calendar c = Calendar.getInstance();
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//            //get current date time with Date()
//            Date date = new Date();
//            System.out.println(dateFormat.format(date));
//          cal.set(arg1, arg2+1, arg3);
//            Date currentDate = cal.getTime();
////
////            final Calendar resetCal = cal;
////            if(!minDate.before(currentDate) ){
////                cal.setTime(minDate);
////                arg0.updateDate(resetCal.get(Calendar.YEAR), resetCal.get(Calendar.MONTH), resetCal.get(Calendar.DAY_OF_MONTH));
////
////            }else if(maxDate.before(currentDate)){
////                cal.setTime(maxDate);
////                arg0.updateDate(resetCal.get(Calendar.YEAR), resetCal.get(Calendar.MONTH), resetCal.get(Calendar.DAY_OF_MONTH));
////            }
//
//            // TODO Auto-generated method stub
//            // arg1 = year
//            // arg2 = month
//            // arg3 = day
//
//            if (currentDate.after(date)||currentDate.equals(date)){
//                showDate(arg1,arg2+1,arg3);
//            }else {
//                arg0.updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
//            }

        }
    };





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.service:
                Intent intent = new Intent(MarketingMain.this, ServicesMain.class);
                startActivity(intent);

                return true;

            case R.id.bill:
                Intent intent1 = new Intent(MarketingMain.this, BillingMain.class);
                startActivity(intent1);
                return true;
            case R.id.mar:
                Intent intent2 = new Intent(MarketingMain.this, MarketingMain.class);
                startActivity(intent2);
                return true;
            case R.id.set:
                Intent intent3 = new Intent(MarketingMain.this, SettingsMain.class);
                startActivity(intent3);
                return true;
            case R.id.rep:
                Intent intent4 = new Intent(MarketingMain.this, ReportMain.class);
                startActivity(intent4);
                return true;
            case R.id.cal:
                Intent intent5 = new Intent(MarketingMain.this, CalendarMain.class);
                startActivity(intent5);
                return true;
            case R.id.acccounting:
                Intent intent6 = new Intent(MarketingMain.this, AccountingMain.class);
                startActivity(intent6);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private static class marketArrayAdapter extends ArrayAdapter<custommarket> {

        private LayoutInflater inflater;
        private List<custommarket> emplist;
        private Context context;

        public marketArrayAdapter(Context context, int resourceId, List<custommarket> emplist) {
            super(context, R.layout.custommarketing, emplist);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.emplist = emplist;
            this.context = context;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // SERVICES to display
            custommarket emp = (custommarket) this.getItem(position);

            // The child views in each row.
            TextView textView1;
            TextView textView2;
            TextView textView3;
            Button button;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.custommarketing, null);

                // Find the child views.
                textView1 = (TextView) convertView
                        .findViewById(R.id.mscategory);
                textView2 = (TextView) convertView.findViewById(R.id.msname);
                textView3 = (TextView) convertView.findViewById(R.id.msstock);


                button = (Button) convertView.findViewById(R.id.mbuttonDelete1);

                // Optimization: Tag the row with it's child views, so we don't
                // have to
                // call findViewById() later when we reuse the row.
                convertView.setTag(new viewHolder1(textView1, textView2, button, textView3));


                // If CheckBox is toggled, update the planet it is tagged with.

            }
            // Reuse existing row view
            else {
                // Because we use a ViewHolder, we avoid having to call
                // findViewById().
                viewHolder1 viewHolder = (viewHolder1) convertView
                        .getTag();
                textView1 = viewHolder.getTextView();
                textView2 = viewHolder.getTextview1();
                textView3 = viewHolder.getTextView3();
                button = viewHolder.getButton();
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Button cb = (Button) v;
                        custommarket massage = (custommarket) cb.getTag();
                        massage.setClicked(cb.isSelected());
                    }
                });
            }


            // Display Service data
            textView1.setText(emp.getRules());
            textView2.setText(emp.getFlist());
            textView3.setText(emp.getValue());
            button.setTag(emp);
            button.setSelected(emp.isClicked());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button cb = (Button) v;
                    custommarket massage = (custommarket) cb.getTag();
                    massage.setClicked(cb.isSelected());


                    String name = emplist.get(position).getRules();
                    String number = emplist.get(position).getFlist();

                    emplist.remove(position);
//                    SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getContext());
//                    String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
//                    Log.e("accesslog",value);
//                    final JSONObject params = new JSONObject();
//
//                    try {
//                        params.put("name",name);
//                        params.put("access_token", value);
//                        params.put("mobile",number);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    StringRequest stringRequest = new StringRequest(Request.Method.POST,URL1,
//                            new Response.Listener<String>(){
//
//                                @Override
//                                public void onResponse(String response) {
//
//                                    Log.v("vinresponse",response);
//
//
//
//                                }
//
//                            }
//                            , new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.v("updateUPVolleyErr", error.toString());
//
//                        }
//                    }){
//                        @Override
//                        protected Map<String, String> getParams() throws AuthFailureError {
//                            Map<String, String> params1 = new HashMap<String, String>();
//
//                            params1.put("payload", params.toString());
//
//                            Log.v("xxx", params1.toString());
//
//                            return params1;
//
//                        }
//                    };
//
//                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//                    requestQueue.add(stringRequest);
                    notifyDataSetChanged();


                }
            });

            return convertView;
        }

    }

    public static class Helper {
        //        public static void getListViewSize(ListView myListView) {
//            ListAdapter myListAdapter = myListView.getAdapter();
//            if (myListAdapter == null) {
//                //do nothing return null
//                return;
//            }
//            //set listAdapter in loop for getting final size
//            int totalHeight = 0;
//            for (int size = 0; size < myListAdapter.getCount(); size++) {
//                View listItem = myListAdapter.getView(size, null, myListView);
//                listItem.measure(0, 0);
//                totalHeight += listItem.getMeasuredHeight();
//            }
//            //setting listview item in adapter
//            ViewGroup.LayoutParams params = myListView.getLayoutParams();
//            params.height = totalHeight + (myListView.getDividerHeight() * (myListAdapter.getCount() - 1));
//            myListView.setLayoutParams(params);
//            // print height of adapter on log
//            Log.i("height of listItem:", String.valueOf(totalHeight));
//        }
        public static boolean setListViewHeightBasedOnItems(ListView listView) {

            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter != null) {

                int numberOfItems = listAdapter.getCount();

                // Get total height of all items.
                int totalItemsHeight = 0;
                for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                    View item = listAdapter.getView(itemPos, null, listView);
                    item.measure(0, 0);
                    totalItemsHeight += item.getMeasuredHeight();
                }

                // Get total height of all item dividers.
                int totalDividersHeight = listView.getDividerHeight() *
                        (numberOfItems - 1);

                // Set list height.
                ViewGroup.LayoutParams params = listView.getLayoutParams();
                params.height = totalItemsHeight + totalDividersHeight;
                listView.setLayoutParams(params);
                listView.requestLayout();

                return true;

            } else {
                return false;
            }
        }


    }

}