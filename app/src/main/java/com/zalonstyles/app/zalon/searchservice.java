package com.zalonstyles.app.zalon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zalonstyles.app.zalon.Model.servicesrch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KASHISH on 17-11-2016.
 */

public class searchservice extends AppCompatActivity {
    private ListView list;
    private ListViewAdapter adapter;
   private ArrayList<servicesrch> arraylist = new ArrayList<servicesrch>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchservice);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.75),(int)(height*.76));

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listviewsrchserv);
       final SearchView inKey = (SearchView) findViewById(R.id.srchserv);
        inKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inKey.setIconified(false);
            }
        });

        final JSONObject params = new JSONObject();
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        try {
            params.put("access_token", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://zalonstyle.in:8080/billing/getAllServiceProducts",
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes1",response);

                        try {
                            JSONObject jobject = new JSONObject(response);
                            JSONArray payload = jobject.getJSONArray("item");
                            Log.e("payloaddata", String.valueOf(payload));
                            for (int i = 0; i < payload.length(); i++) {
                                try{
                                    JSONObject obj = payload.getJSONObject(i);
                                    servicesrch service = new servicesrch();
                                    service.setName(obj.getString("item"));
                                    service.setId(obj.getString("id"));
                                    service.setType(obj.getString("type"));
                                    arraylist.add(service);
//                                    Log.e("check201", String.valueOf(arraylist.get(i)));






                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }adapter.notifyDataSetChanged();

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

//        for (int i = 0; i < rank.length; i++)
//        {
//            Customersrch wp = new WorldPopulation(rank[i], country[i],
//                    population[i]);
//            // Binds all strings into an array
//            arraylist.add(wp);
//        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        list.setTextFilterEnabled(true);


        inKey.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               ListViewAdapter ca = (ListViewAdapter) list.getAdapter();

                if (TextUtils.isEmpty(newText)) {
                    System.out.println("isEmpty");
                    //listview.clearTextFilter();
                    ca.getFilter().filter(null);
                } else {

                    ca.getFilter().filter(newText);
                    //listview.setFilterText(newText);

                }
//                if (newText != null && newText.length() > 0) {
//                    list.setFilterText(newText);
//                } else {
//                    list.clearTextFilter();
//                }
                return true;
            }
        });

    }


    // Locate the EditText in listview_main.xml
//        editsearch = (EditText) findViewById(R.id.search);
//
//        // Capture Text in EditText
////        editsearch.addTextChangedListener(new TextWatcher() {
////
////            @Override
////            public void afterTextChanged(Editable arg0) {
////                // TODO Auto-generated method stub
////                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
////                adapter.filter(text);
////
////            }
////
////            @Override
////            public void beforeTextChanged(CharSequence arg0, int arg1,
////                                          int arg2, int arg3) {
////                // TODO Auto-generated method stub
////            }
////
////            @Override
////            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
////                                      int arg3) {
////                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
////                adapter.filter(text);
////                // TODO Auto-generated method stub
////            }
////        });



    public class ListViewAdapter extends BaseAdapter implements Filterable {

        // Declare Variables
        Context mContext;
        LayoutInflater inflater;
        private List<servicesrch> customerlist,copyData = null;
        private ArrayList<servicesrch> arraylist;

        public ListViewAdapter(Context context, List<servicesrch> customerlist) {
            mContext = context;
            this.customerlist = customerlist;
            inflater = LayoutInflater.from(mContext);
            this.arraylist = new ArrayList<servicesrch>();
            this.arraylist.addAll(customerlist);
            copyData = customerlist;
        }



        public class ViewHolder {
            TextView name;

        }

        @Override
        public int getCount() {
            return customerlist.size();
        }

        @Override
        public servicesrch getItem(int position) {
            return customerlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View view, ViewGroup parent) {
            final ListViewAdapter.ViewHolder holder;
            if (view == null) {
                holder = new ListViewAdapter.ViewHolder();
                view = inflater.inflate(R.layout.customservsrch, null);
                // Locate the TextViews in listview_item.xml
                holder.name = (TextView) view.findViewById(R.id.rowTextView);
                view.setTag(holder);
            } else {
                holder = (ListViewAdapter.ViewHolder) view.getTag();
            }
            // Set the results into TextViews
            holder.name.setText(customerlist.get(position).getName());

            // Listen for ListView Item Click
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(mContext, Billing.class);
                    // Pass all data name
                    intent.putExtra("name",(customerlist.get(position).getName()));

                    intent.putExtra("id",(customerlist.get(position).getId()));

                    intent.putExtra("type",(customerlist.get(position).getType()));



                    // Start SingleItemView Class
                    setResult(3,intent);
                    finish();
                }
            });


            return view;
        }
        private Filter myFilter ;
        @Override
        public Filter getFilter() {
            if(null == myFilter) {
                myFilter = new ListViewAdapter.MyFilter() ;
            }
            return myFilter ;
        }

        class MyFilter extends Filter{

            protected FilterResults performFiltering(CharSequence constraint) {
                List<servicesrch> filterData = new ArrayList<servicesrch>() ;

                if(constraint != null && constraint.toString().trim().length() > 0) {
                    String key = constraint.toString().trim().toLowerCase() ;
                    for (servicesrch item : copyData) {
                        if(item.getName()
                                .toString().toLowerCase().indexOf(key) != -1 ) {
                            filterData.add(item) ;
                        }
                    }
                }
                else {
                    filterData = copyData ;
                }
                FilterResults results = new FilterResults() ;
                results.values = filterData ;
                results.count = filterData.size() ;
                return results ;
            }

            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {

                customerlist = (List<servicesrch>) results.values ;
                if(results.count > 0) {
                    notifyDataSetChanged() ;
                }
                else {
                    notifyDataSetInvalidated() ;
                }
            }

        } ;


        // Filter Class
//        public void filter(String charText) {
//            charText = charText.toLowerCase(Locale.getDefault());
//            customerlist.clear();
//            if (charText.length() == 0) {
//                customerlist.addAll(arraylist);
//            }
//            else
//            {
//                for (Customersrch wp : arraylist)
//                {
//                    if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText))
//                    {
//                        customerlist.add(wp);
//                    }
//                }
//            }
//            notifyDataSetChanged();
//        }

    }


}



