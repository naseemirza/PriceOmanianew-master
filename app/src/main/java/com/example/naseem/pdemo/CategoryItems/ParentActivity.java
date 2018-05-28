package com.example.naseem.pdemo.CategoryItems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class ParentActivity extends AppCompatActivity {

    private static final String JSON_URL = "http://ae.priceomania.com/mobileappwebservices/getparentcategory";
    ListView listView;
    List<Parent> parentList;

    int parentID;
    public static List<String> childs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listview1);
        parentList = new ArrayList<>();


        loadItemList();



    }

    private void loadItemList() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://ae.priceomania.com/mobileappwebservices/getparentcategory",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parentList.clear();


                        try {
                            //JSONObject obj = new JSONObject(response);

                            Log.e("rootJsonArray",response);
                            JSONArray rootJsonArray = new JSONArray(response);

                            Log.e("rootJsonArrayLength",rootJsonArray.length()+"");

                            for (int i = 0; i < rootJsonArray.length(); i++) {
                                JSONObject object = rootJsonArray.getJSONObject(i);
                                parentList.add(new Parent(object.optString("category_id"),
                                        object.optString("category_name"),
                                        object.optString("category_image")));
                            }

                            Log.e("rootJsonArray",parentList.size()+"");


                            ParentAdapter adapter = new ParentAdapter(parentList, getApplicationContext());
                            listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                //String cat_id= parentList.get(position).toString();
                                Parent itemId=(Parent) parent.getItemAtPosition(position);
                                 String categoryId=itemId.getId();
                                Intent intent = new Intent(ParentActivity.this, ChildActivity.class);
                                //String cat_id = ((TextView) view.findViewById(R.id.parent_id)).getText().toString();
                                Log.e("subId",categoryId);
                                intent.putExtra("category_id",categoryId );
                                startActivity(intent);
                            }
                        });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /*try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray parentArray = obj.getJSONArray("products");

                            for (int i = 0; i < parentArray.length(); i++) {
                                JSONObject heroObject = parentArray.getJSONObject(i);

                                Parent parent = new Parent(heroObject.getString("category_id"),
                                        heroObject.getString("category_name"),

                                        heroObject.getString("category_image"));

                                parentList.add(parent);
                            }*/



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("TAg",error.getMessage());
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
