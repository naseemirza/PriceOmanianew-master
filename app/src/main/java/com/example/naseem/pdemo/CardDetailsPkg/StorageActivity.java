package com.example.naseem.pdemo.CardDetailsPkg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.naseem.pdemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StorageActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    ListView childListView;
    String ustring;

    public static List<StorageModel> storageList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

         getSupportActionBar().setTitle("Filters");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Options"));

        childListView = (ListView) findViewById(R.id.list_viewstrg);
        storageList = new ArrayList<>();


        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        //SharedPreferences example=getSharedPreferences("prod_id",0);
        ustring=pref.getString("usermessage","");


        Log.e("responce",ustring);

        loadItemList();



       // tabLayout = (TabLayout) findViewById(R.id.tabs);
        //tabLayout.addTab(tabLayout.newTab().setText("Storage"));

//        StorageAdapter adapter = new StorageAdapter(this, Itemlist);
//        ListView lv = (ListView) findViewById(R.id.list_viewstrg);
//        lv.setAdapter(adapter);
//        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View view,
//                                    int position, long arg3) {
//                TextView tv=(TextView)findViewById(R.id.textViewstorage);
//                String name = arg0.getItemAtPosition(position).toString();
//                Intent intent=new Intent();
//                intent.putExtra("storage",name);
//                setResult(3,intent);
//                finish();
//
//                LinearLayout item_view = (LinearLayout) view;
//                final RadioButton itemcheck = (RadioButton)
//                        item_view.findViewById(R.id.rbuttons);
//
//
//
//                if (itemcheck.isChecked()) {
//                    itemcheck.setChecked(true);
//
//                } else {
//                    itemcheck.setChecked(true);
//                }
//
//                itemcheck.setChecked(true);
//
//            }
//
//        });


    }


    private void loadItemList() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://ae.priceomania.com/mobileappwebservices/getStorage?pid=" +ustring ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("response", response);

                        try {
                            JSONObject rootJsonObject = new JSONObject(response);
                            //String  id = rootJsonObject.getString("category_id");
                            //Log.e("Id", id);

                            JSONArray subCategoryArray = rootJsonObject.getJSONArray("storage");
                            Log.e("subCategoryArray", subCategoryArray.length() + "");

                            for (int i = 0; i < subCategoryArray.length(); i++) {

                                JSONObject detailsObject = subCategoryArray.getJSONObject(i);
                                storageList.add(new StorageModel(
                                        detailsObject.optString("storage")
                                ));


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        StorageAdapter adapter = new StorageAdapter(storageList, getApplicationContext());
                        childListView.setAdapter(adapter);

                        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> arg0, View view,
                                                    int position, long arg3) {

                                TextView tv=(TextView)view.findViewById(R.id.textViewstorage);
                                String tag=tv.getText().toString();

                                Log.e("Id", tag);

//                                SharedPreferences pref = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//                                SharedPreferences.Editor edit = pref.edit();
//
//                                Intent intent=new Intent();
//                                edit.putString("storag",tag);
//                                setResult(3,intent);
//                                finish();

                                Intent intent=new Intent();
                                intent.putExtra("storage",tag);
                                setResult(3,intent);
                                finish();

                                LinearLayout item_view = (LinearLayout) view;
                                final RadioButton itemcheck = (RadioButton)
                                        item_view.findViewById(R.id.rbuttons);



                                if (itemcheck.isChecked()) {
                                    itemcheck.setChecked(true);

                                } else {
                                    itemcheck.setChecked(true);
                                }

                                itemcheck.setChecked(true);

                            }

                        });

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        return true;
    }
}
