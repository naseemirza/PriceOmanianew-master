package com.example.naseem.pdemo.CategoryItems;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

public class Sub_ChildActivity extends AppCompatActivity {

    private static final String JSON_URL = "http://ae.priceomania.com/mobileappwebservices/getchildcategory?catId=14";
    ListView childListView;
    private String subCategoryId;
    public static List<Child> childList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__child);

        getSupportActionBar().setTitle("Sub_Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        childListView = (ListView) findViewById(R.id.listview1);
        childList = new ArrayList<>();
        Intent intent = getIntent();
        if (intent.hasExtra("category_id")) {
            subCategoryId = intent.getStringExtra("category_id");
        } else {
            Toast.makeText(this, "Sorry Data Not Available", Toast.LENGTH_SHORT).show();
        }

        //Log.e("subId", subCategoryId);
        loadItemList();
    }

    private void loadItemList() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://ae.priceomania.com/mobileappwebservices/getchildcategory?catId=" + subCategoryId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("response", response);

                        try {
                            JSONObject rootJsonObject = new JSONObject(response);
                            String id = rootJsonObject.getString("category_id");
                            Log.e("Id", id);

                            JSONArray subCategoryArray = rootJsonObject.getJSONArray("child_category");
                            Log.e("subCategoryArray", subCategoryArray.length() + "");

                            for (int i = 0; i < subCategoryArray.length(); i++) {

                                JSONObject detailsObject = subCategoryArray.getJSONObject(i);
                                childList.add(new Child(detailsObject.optString("category_id"),
                                        detailsObject.optString("category_name"),
                                        detailsObject.optString("category_image")));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                       // Log.e("subId", String.valueOf(childList));

                        if (childList.size() == 0) {
                            startActivity(new Intent(Sub_ChildActivity.this,GridActivity.class));

                        }
                        else {
                            final ChildAdapter adapter = new ChildAdapter(childList, getApplicationContext());
                            childListView.setAdapter(adapter);
                        }


                       // final ChildAdapter adapter = new ChildAdapter(childList, getApplicationContext());
                       // childListView.setAdapter(adapter);


                        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                               // if (childList.size() == 0) {
                                    startActivity(new Intent(Sub_ChildActivity.this,GridActivity.class));

//                                }
//                               else {
//                                    final ChildAdapter adapter = new ChildAdapter(childList, getApplicationContext());
//                                childListView.setAdapter(adapter);
//                            }
                            }
                        });
                        //childListView.setEmptyView(findViewById(R.id.empty));


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

