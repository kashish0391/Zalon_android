package com.zalonstyles.app.zalon;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.zalonstyles.app.zalon.Model.Sinventory;
import com.zalonstyles.app.zalon.Model.ViewHolder2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KASHISH on 30-07-2016.
 */
public class SellableInventory extends AppCompatActivity {
    Spinner SPINNER;
    private Button ADD;

    EditText EDITTEXT1;
    String[] spinnerItems1 = new String[]{"Supplier"};
    Spinner SPINNER1;
    private Button ADD1;

    EditText EDITTEXT;
    String[] spinnerItems = new String[]{"Normal Category"};
    private EditText Name;
    private EditText Stock;
    private EditText SellingPrice;
    private EditText Price;
    private EditText RefilReminder;
    private Button buttonSubmit;
    private Button buttonDelete;
    private Button buttonDelete1;
    private String name;
    private String category;
    private String supplier;
    private String sellingprice;
    private String stock;
    private String price;
    private String refillreminder;
    private String spinnerValue;
    private String spinnerValue1;
    private ListView Selleblelist;
    public static final String SUBMIT_URL =" http://zalonstyle.in:8080/salon/add_inventory";
    public static final String URL = "http://zalonstyle.in:8080/salon/get_inventory";
    public static final String URL1 = "http://zalonstyle.in:8080/salon/delete_inventory";
    private List<Sinventory> SinventoryList = new ArrayList<>();
    private ArrayAdapter<Sinventory> listAdapter;



    String GETTEXT;
    List<String> stringlist;
    ArrayAdapter<String> arrayadapter;
    String GETTEXT1;
    List<String> stringlist1;
    ArrayAdapter<String> arrayadapter1;
    private static String invetory_id = "sellable";
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellable_inventory);
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        Selleblelist = (ListView)findViewById(R.id.listviewsinventory);
        Name = (EditText)  findViewById(R.id.Sellableinventoryed);
        SellingPrice = (EditText)  findViewById(R.id.Sellableinventoryed2);
        Price = (EditText) findViewById(R.id.Sellableinventoryed1);
        RefilReminder = (EditText)findViewById(R.id.Sellableinventoryed4);

        Stock = (EditText)  findViewById(R.id.Sellableinventoryed3);
        buttonSubmit = (Button)findViewById(R.id.SinventorySubmit);
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


        final JSONObject params = new JSONObject();
        try {
            params.put("inventory_type",invetory_id);
            params.put("access_token", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listAdapter = new sinventoryArrayAdapter(this,R.layout.customsinventory, SinventoryList);
        Selleblelist.setAdapter(listAdapter);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        Log.v("updateUPVolleyRes11",response);

                        try {
                            JSONObject jobject = new JSONObject(response);
                            JSONArray payload = jobject.getJSONArray("inventory");
                            Log.e("payloaddata", String.valueOf(payload));
                            for (int i = 0; i < payload.length(); i++) {
                                try{
                                    JSONObject obj = payload.getJSONObject(i);
                                    Sinventory service = new Sinventory();


                                    service.setName(obj.getString("item_name"));
                                    service.setStock(obj.getString("stock"));
                                    service.setPrice(obj.getString("selling_price"));
                                    service.setCategory(obj.getString("category"));
                                    SinventoryList.add(service);
                                    Log.e("check2", String.valueOf(SinventoryList.get(i)));







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





        SPINNER = (Spinner)findViewById(R.id.spinner);
        ADD = (Button)findViewById(R.id.spinneradd);
        EDITTEXT = (EditText)findViewById(R.id.Sellableinventoryed5);
        buttonDelete =(Button) findViewById(R.id.sellebal_delete);
        stringlist = new ArrayList<>(Arrays.asList(spinnerItems));

        arrayadapter = new ArrayAdapter<String>(SellableInventory.this,R.layout.textviewspinner,stringlist );

        arrayadapter.setDropDownViewResource(R.layout.textviewspinner);

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
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myData = SPINNER.getSelectedItem().toString();
                int position = arrayadapter.getPosition(myData);
                arrayadapter.remove(myData);
                arrayadapter.notifyDataSetChanged();
            }
        });

        ADD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                GETTEXT = EDITTEXT.getText().toString();

                stringlist.add(GETTEXT);
                EDITTEXT.setText("");

                arrayadapter.notifyDataSetChanged();


                Toast.makeText(SellableInventory.this, "Item Added", Toast.LENGTH_LONG).show();
            }
        });
        SPINNER1 = (Spinner)findViewById(R.id.spinner1);
        ADD1 = (Button)findViewById(R.id.spinneradd1);
        EDITTEXT1 = (EditText)findViewById(R.id.Sellableinventoryed6);
        buttonDelete1 =(Button) findViewById(R.id.sellebal_delete1);
        stringlist1 = new ArrayList<>(Arrays.asList(spinnerItems1));

        arrayadapter1 = new ArrayAdapter<String>(SellableInventory.this,R.layout.textviewspinner1,stringlist1 );

        arrayadapter1.setDropDownViewResource(R.layout.textviewspinner1);

        SPINNER1.setAdapter(arrayadapter1);
        SPINNER1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue1 = SPINNER1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerValue1 ="Seller";

            }
        });
        buttonDelete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myData = SPINNER1.getSelectedItem().toString();
                int position = arrayadapter1.getPosition(myData);
                arrayadapter1.remove(myData);
                arrayadapter1.notifyDataSetChanged();
            }
        });

        ADD1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                GETTEXT1 = EDITTEXT1.getText().toString();

                stringlist1.add(GETTEXT1);
                EDITTEXT1.setText("");

                arrayadapter1.notifyDataSetChanged();


                Toast.makeText(SellableInventory.this, "Item Added", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Submit() throws JSONException {
        name = Name.getText().toString().trim();
        sellingprice = SellingPrice.getText().toString().trim();
        stock = Stock.getText().toString().trim();
        category = spinnerValue;
        supplier = spinnerValue1;
        refillreminder = RefilReminder.getText().toString().trim();
        price = Price.getText().toString().trim();
        String inventory_type_id = "sellable";
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value = (mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        SinventoryList.clear();
        Selleblelist.setAdapter(null);
        listAdapter.notifyDataSetChanged();

       /* Sinventory empobj = new Sinventory();
        empobj.setName(name);
        empobj.setCategory(category);
        empobj.setPrice(sellingprice);
        empobj.setStock(stock);
        SinventoryList.add(empobj);*/

        final JSONObject params = new JSONObject();
        params.put("access_token", value);
        params.put("inventory_type",inventory_type_id);
        JSONObject param = null;
        param = new JSONObject();
        param.put("item_name", name);
        param.put("supplier",supplier);
        param.put("category", category);
        param.put("inventory_type",inventory_type_id);
        param.put("selling_price",sellingprice);
        param.put("stock",stock);
        param.put("riffle_reminder",refillreminder);
        param.put("purchase_price",price);
        params.put("inventory", param);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, SUBMIT_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.v("updateUPVolley", response.toString());
                        try {

                            JSONObject jobject = new JSONObject(response);
                            JSONArray payload = jobject.getJSONArray("data");
                            Log.e("payloaddata", String.valueOf(payload));
                            for (int i = 0; i < payload.length(); i++) {
                                try{

                                    JSONObject obj = payload.getJSONObject(i);
                                    Sinventory service = new Sinventory();


                                    service.setName(obj.getString("item_name"));
                                    service.setStock(obj.getString("stock"));
                                    service.setPrice(obj.getString("selling_price"));
                                    service.setCategory(obj.getString("category"));
                                    SinventoryList.add(service);
                                    Log.e("check2", String.valueOf(SinventoryList.get(i)));






                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }listAdapter.notifyDataSetChanged();




                        try {
                            if (new JSONObject(response).getString("status").equals("success")) {



                                Name.setText("");
                                Stock.setText("");
                                Price.setText("");
                                RefilReminder.setText("");
                                SellingPrice.setText("");


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

                Log.v("sellableParams", params1.toString());

                return params1;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        listAdapter = new sinventoryArrayAdapter(this,R.layout.customsinventory, SinventoryList);
        Selleblelist.setAdapter(listAdapter);
       // listAdapter.notifyDataSetChanged();


       Selleblelist
                .setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View item,
                                            int position, long id)
                    {
                        Sinventory employee1 = listAdapter.getItem(position);
                        Log.e("CHECKADAPTOR", String.valueOf(employee1));
                        employee1.toggleClicked();
                        ViewHolder2 viewHolder =(ViewHolder2) item
                                .getTag();
                        viewHolder.getButton().setSelected(employee1.isClicked());
                    }
                });










    }



    private static class sinventoryArrayAdapter extends ArrayAdapter<Sinventory>
    {

        private LayoutInflater inflater;
        private List<Sinventory> sinventorylist;
        private Context context;

        public sinventoryArrayAdapter(Context context, int resourceId, List<Sinventory> sinventorylist)
        {
            super(context, R.layout.customsinventory, sinventorylist);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from((Context) context);
            this.sinventorylist = sinventorylist;
            this.context = context;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            // SERVICES to display
            final Sinventory sinventory = (Sinventory) this.getItem(position);

            // The child views in each row.
            TextView textView1;
            TextView textView2;
            TextView textView3;
            TextView textView4;
            Button button;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // Create a new row view
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.customsinventory,null );

                // Find the child views.
                textView1 = (TextView) convertView
                        .findViewById(R.id.scategory);
                textView2 = (TextView) convertView.findViewById(R.id.sname);
                textView3 = (TextView) convertView.findViewById(R.id.sstock);
                textView4 = (TextView) convertView.findViewById(R.id.sprice);
                button = (Button) convertView.findViewById(R.id.buttonDelete1);

                // Optimization: Tag the row with it's child views, so we don't
                // have to
                // call findViewById() later when we reuse the row.
                convertView.setTag(new ViewHolder2(textView1, textView2,textView3,textView4,button));




                // If CheckBox is toggled, update the planet it is tagged with.

            }
            // Reuse existing row view
            else
            {
                // Because we use a ViewHolder, we avoid having to call
                // findViewById().
                ViewHolder2 viewHolder = (ViewHolder2) convertView
                        .getTag();
                textView1 = viewHolder.getTextView();
                textView2 = viewHolder.getTextview1();
                textView3 = viewHolder.getTextview2();
                textView4 = viewHolder.getTextView3();
                button = viewHolder.getButton();
                button.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        Button cb = (Button) v;
                        Sinventory sinventory = (Sinventory) cb.getTag();
                        sinventory.setClicked(cb.isSelected());
                    }
                });
            }



            // Display Service data
            textView1.setText(sinventory.getCategory());
            textView2.setText(sinventory.getName());
            textView3.setText(sinventory.getStock());
            textView4.setText(sinventory.getPrice());
            button.setTag(sinventory);
            button.setSelected(sinventory.isClicked());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {


                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        //Yes button clicked
                                        Button cb = (Button) v;
                                        Sinventory inventory = (Sinventory) cb.getTag();
                                        inventory.setClicked(cb.isSelected());


                                        String name = sinventorylist.get(position).getName();
                                        String category = sinventorylist.get(position).getCategory();
                                        String price = sinventorylist.get(position).getPrice();
                                        String stock = sinventorylist.get(position).getStock();

                                        sinventorylist.remove(position);
                                        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getContext());
                                        String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
                                        Log.e("accesslog",value);

                    final JSONObject params = new JSONObject();

                    try {
                        params.put("item_name",name);
                        params.put("access_token", value);
                        params.put("category",category);
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

                                        break;
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        // No button clicked //
                                        // do nothing
                                        break; } } };
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure?") .setPositiveButton("Yes", dialogClickListener) .setNegativeButton("No", dialogClickListener).show();






                }

            });

            return convertView;
        }

    }
}