package com.zalonstyles.app.zalon;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zalonstyles.app.zalon.Model.EditServiceModel;
import com.zalonstyles.app.zalon.Model.Viewholder4;
import com.zalonstyles.app.zalon.Model.Viewholder5;
import com.zalonstyles.app.zalon.Model.pricevariable;
import com.zalonstyles.app.zalon.Model.pricevariable1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KASHISH on 16-08-2016.
 */
public class Edit_Service extends AppCompatActivity {
    private RecyclerView horizontal_recycler_view;
    public static final String URL = "http://52.41.72.46:8080/service/get_category_detail";
    private Switch switch1;
    private Switch switch2;
    private Switch switch3;
    private EditText etsname;
    private TextView tvscategory;
    private EditText etdescription;
    private EditText editDuration;
    private EditText editDuration1;
    private EditText editPrice;
    private EditText maleprice;
    private EditText femaleprice;
    private ListView lv1;
    private ListView lv2;
    private List<pricevariable> pricelist = new ArrayList<>();
    private List<pricevariable1> pricelist1 = new ArrayList<>();
    private Context context;
    private ArrayAdapter<pricevariable> listAdapter;
    private ArrayAdapter<pricevariable1> listAdapter1;

    private ArrayList<EditServiceModel> horizontalList;
    private HorizontalAdapter horizontalAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicesedit);
        etsname = (EditText) findViewById(R.id.editsname);
        tvscategory = (TextView) findViewById(R.id.editscategory);
        etdescription = (EditText) findViewById(R.id.sdescription);
        editDuration = (EditText) findViewById(R.id.editDur);
        editDuration1 = (EditText) findViewById(R.id.editduration1);
        editPrice = (EditText) findViewById(R.id.editprice);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch3 = (Switch) findViewById(R.id.switch3);
        maleprice = (EditText) findViewById(R.id.editprice1);
        femaleprice = (EditText) findViewById(R.id.editprice2);
        final NumberPicker np = (NumberPicker) findViewById(R.id.np);

        lv1 = (ListView) findViewById(R.id.lv1);
        lv2 = (ListView) findViewById(R.id.lv2);

        final LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearlay1);
        final LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.linearlay2);
        final LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.linearlay3);
        final LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.linearlay4);
        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.GONE);
        linearLayout3.setVisibility(View.GONE);
        linearLayout4.setVisibility(View.GONE);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch1.isChecked() && switch2.isChecked()) {
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                } else if (!switch1.isChecked() && !switch2.isChecked()) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                } else if (switch1.isChecked() && !switch2.isChecked()) {
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                } else if (!switch1.isChecked() && switch2.isChecked()) {
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                }

            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch1.isChecked() && switch2.isChecked()) {
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                }
                if (switch1.isChecked() == false && switch2.isChecked() == false) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                }
                if (switch1.isChecked() && switch2.isChecked() == false) {
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                }
                if (switch1.isChecked() == false && switch2.isChecked()) {
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                }
            }
        });


        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        Bundle Data = getIntent().getExtras();
        String myVal = Data.getString("value");
        String idval = Data.getString("valueid");
        String sid = Data.getString("Serviceid");
        Log.v("val", myVal);
        Log.v("valid", idval);


        final JSONObject params = new JSONObject();
        try {
            params.put("service_id", sid);
            params.put("category_id", idval);
        } catch (JSONException e) {

        }
        try {
            params.put("access_token", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes1", response);

                        try {
                            JSONObject jobject = new JSONObject(response);
                            JSONArray payload = jobject.getJSONArray("staff");
                            Log.e("payloaddata", String.valueOf(payload));
                            JSONObject info = jobject.getJSONObject("info");
                            etsname.setText(info.getString("service_name"));
                            tvscategory.setText(info.getString("category_name"));
                            etdescription.setText(info.getString("description"));
                            String staffbased = String.valueOf(info.getString("staff_based"));
                            String Genderbased = String.valueOf(info.getString("gender_based"));

                            try {

                                JSONArray payload1 = jobject.getJSONArray("price");
                                Log.e("payloaddata1", String.valueOf(payload1));
                                for (int i = 0; i < payload1.length(); i++) {
                                    try {
                                        JSONObject obj = payload1.getJSONObject(i);
                                        pricevariable1 service = new pricevariable1();
                                        service.setName(obj.getString("caption"));

                                        service.setMaleprice(Integer.parseInt(obj.getString("male_price")));
                                        service.setFemaleprice(Integer.parseInt(obj.getString("female_price")));
                                        service.setDuration(Integer.parseInt(obj.getString("duration")));
                                        pricelist1.add(service);
                                        Log.e("check2", String.valueOf(pricelist1.get(i)));


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            listAdapter1.notifyDataSetChanged();


                            try {

                                JSONArray payload1 = jobject.getJSONArray("price");
                                Log.e("payloaddata1", String.valueOf(payload1));
                                for (int i = 0; i < payload1.length(); i++) {
                                    try {
                                        JSONObject obj = payload1.getJSONObject(i);
                                        pricevariable service = new pricevariable();
                                        service.setName(obj.getString("caption"));

                                        service.setPrice(Integer.parseInt(obj.getString("common_price")));
                                        service.setDuration(Integer.parseInt(obj.getString("duration")));


                                        pricelist.add(service);
                                        Log.e("check2", String.valueOf(pricelist.get(i)));


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            listAdapter.notifyDataSetChanged();

                            if (staffbased.equals("on")) {
                                switch1.setChecked(true);
                            } else {
                                switch1.setChecked(false);

                            }
                            if (Genderbased.equals("on")) {
                                switch2.setChecked(true);
                            } else {
                                switch2.setChecked(false);
                            }

                            if (switch1.isChecked() && switch2.isChecked()) {


                            }
                            if (!switch1.isChecked() && !switch2.isChecked()) {
                                editPrice.setText(info.getString("common_price"));
                                editDuration.setText(info.getString("duration"));
                                np.setValue(Integer.parseInt(info.getString("duration")));
                            }
                            if (switch1.isChecked() && !switch2.isChecked()) {
                                // pricelist.clear();
                                //lv1.setAdapter(null);
                                //listAdapter.notifyDataSetChanged();

                               /* try {

                                    JSONArray payload1 = jobject.getJSONArray("price");
                                    Log.e("payloaddata1", String.valueOf(payload1));
                                    for (int i = 0; i < payload1.length(); i++) {
                                        try{
                                            JSONObject obj = payload1.getJSONObject(i);
                                            pricevariable service = new pricevariable();
                                            service.setName(obj.getString("caption"));
                                            service.setPrice(Integer.parseInt(obj.getString("common_price")));
                                            service.setDuration(Integer.parseInt(obj.getString("duration")));
                                            pricelist.add(service);
                                            Log.e("check2", String.valueOf(pricelist.get(i)));






                                        }catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }listAdapter.notifyDataSetChanged();
                            */
                            }
                            if (!switch1.isChecked() && switch2.isChecked()) {
                                maleprice.setText(info.getString("male_price"));
                                femaleprice.setText(info.getString("female_price"));
                                editDuration1.setText(info.getString("duration"));

                            }

                            for (int i = 0; i < payload.length(); i++) {
                                try {
                                    JSONObject obj = payload.getJSONObject(i);
                                    EditServiceModel service = new EditServiceModel();
                                    service.setcategotry_id(Integer.parseInt(obj.getString("staff_id")));
                                    service.setName(obj.getString("name"));
                                    service.setCategoryName(obj.getString("category"));
                                    horizontalList.add(service);
                                    Log.e("check2", String.valueOf(horizontalList.get(i)));


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        horizontalAdapter.notifyDataSetChanged();

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

        if (switch1.isChecked() && switch2.isChecked()) {
            linearLayout4.setVisibility(View.VISIBLE);
        }
        if (switch1.isChecked() == false && switch2.isChecked() == false) {
            linearLayout1.setVisibility(View.VISIBLE);
        }
        if (switch1.isChecked() && switch2.isChecked() == false) {

            linearLayout3.setVisibility(View.VISIBLE);
        }
        if (switch1.isChecked() == false && switch2.isChecked()) {
            linearLayout2.setVisibility(View.VISIBLE);
        }


        listAdapter = new priceArrayAdapter(this, R.layout.customeditservice1, pricelist);
        lv1.setAdapter(listAdapter);

        listAdapter1 = new priceArrayAdapter1(this, R.layout.customeditservice2, pricelist1);
        lv2.setAdapter(listAdapter1);


        horizontal_recycler_view = (RecyclerView) findViewById(R.id.horizontal_recycler_view);

        horizontalList = new ArrayList<>();


        horizontalAdapter = new HorizontalAdapter(horizontalList);


//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        vertical_recycler_view.setLayoutManager(mLayoutManager);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(Edit_Service.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
        horizontal_recycler_view.setAdapter(horizontalAdapter);

        final String[] values = {"20", "25", "30", "35", "40", "45", "50", "55"};
        np.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        np.setMaxValue(values.length - 1);
        np.setDisplayedValues(values);
        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);


        //Set a value change listener for NumberPicker
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
                editDuration.setText(String.valueOf(values[newVal]));
            }
        });

    }


    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

        private List<EditServiceModel> stList;

        public HorizontalAdapter(List<EditServiceModel> students) {
            this.stList = students;

        }

        // Create new views
        @Override
        public HorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
            // create a new view
            View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.horizonal_list, null);

            // create ViewHolder

            ViewHolder viewHolder = new ViewHolder(itemLayoutView);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            final int pos = position;

            viewHolder.tvName.setText(stList.get(position).getName());


            viewHolder.chkSelected.setChecked(stList.get(position).isChecked());

            viewHolder.chkSelected.setTag(stList.get(position));


            viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    EditServiceModel contact = (EditServiceModel) cb.getTag();

                    contact.setChecked(cb.isChecked());
                    stList.get(pos).setChecked(cb.isChecked());

                    Toast.makeText(
                            v.getContext(),
                            "Clicked on Checkbox: " + cb.getText() + " is "
                                    + cb.isChecked(), Toast.LENGTH_LONG).show();
                }
            });

        }

        // Return the size arraylist
        @Override
        public int getItemCount() {
            return stList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView tvName;

            public CheckBox chkSelected;

            public EditServiceModel singlestudent;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);

                tvName = (TextView) itemLayoutView.findViewById(R.id.tvName);

                chkSelected = (CheckBox) itemLayoutView
                        .findViewById(R.id.chkSelected);

            }

        }

        // method to access in activity after updating selection
        public List<EditServiceModel> getStudentist() {
            return stList;
        }

    }

    private static class priceArrayAdapter extends ArrayAdapter<pricevariable> {

        private LayoutInflater inflater;
        private List<pricevariable> pricelist;
        private Context context;

        public priceArrayAdapter(Context context, int resourceId, List<pricevariable> pricelist) {
            super(context, R.layout.customeditservice1, pricelist);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.pricelist = pricelist;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // SERVICES to display
            pricevariable hair = (pricevariable) this.getItem(position);

            // The child views in each row.
            TextView editText;
            EditText editText1;
            EditText editText2;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.customeditservice1, null);

                // Find the child views.
                editText = (TextView) convertView
                        .findViewById(R.id.emptextview1);
                editText1 = (EditText) convertView.findViewById(R.id.emptextview2);
                editText2 = (EditText) convertView.findViewById(R.id.emptextview3);


                convertView.setTag(new Viewholder4(editText, editText1, editText2));


            }
            // Reuse existing row view
            else {
                // Because we use a ViewHolder, we avoid having to call
                // findViewById().
                Viewholder4 viewHolder = (Viewholder4) convertView
                        .getTag();
                editText = viewHolder.getEditText1();
                editText1 = viewHolder.getEditText2();
                editText2 = viewHolder.getEditText3();
            }


            // Display Service data
            editText.setText(hair.getName());
            editText1.setText(String.valueOf(hair.getPrice()));
            editText2.setText(String.valueOf(hair.getDuration()));


            return convertView;
        }

    }

    private static class priceArrayAdapter1 extends ArrayAdapter<pricevariable1> {

        private LayoutInflater inflater;
        private List<pricevariable1> pricelist1;
        private Context context;

        public priceArrayAdapter1(Context context, int resourceId, List<pricevariable1> pricelist1) {
            super(context, R.layout.customeditservice2, pricelist1);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.pricelist1 = pricelist1;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // SERVICES to display
            pricevariable1 hair = (pricevariable1) this.getItem(position);

            // The child views in each row.
            TextView editText;
            EditText editText1;
            EditText editText2;
            EditText editText3;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.customeditservice2, null);

                // Find the child views.
                editText = (TextView) convertView
                        .findViewById(R.id.emptextview11);
                editText1 = (EditText) convertView.findViewById(R.id.emptextview12);
                editText2 = (EditText) convertView.findViewById(R.id.emptextview13);
                editText3 = (EditText) convertView.findViewById(R.id.emptextview14);


                convertView.setTag(new Viewholder5(editText, editText1, editText2, editText3));


            }
            // Reuse existing row view
            else {
                // Because we use a ViewHolder, we avoid having to call
                // findViewById().
                Viewholder5 viewHolder = (Viewholder5) convertView
                        .getTag();
                editText = viewHolder.getEditText1();
                editText1 = viewHolder.getEditText2();
                editText2 = viewHolder.getEditText3();
                editText3 = viewHolder.getEditText4();

            }


            // Display Service data
            editText.setText(hair.getName());
            editText1.setText(String.valueOf(hair.getMaleprice()));
            editText2.setText(String.valueOf(hair.getFemaleprice()));
            editText3.setText(String.valueOf(hair.getDuration()));


            return convertView;
        }

    }


}

