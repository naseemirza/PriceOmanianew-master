package thinkbiz.solutions.tbs.com.CategoryItems;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import thinkbiz.solutions.tbs.com.AllUrls;
import thinkbiz.solutions.tbs.com.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChildActivity extends AppCompatActivity {

    private static final String JSON_URL = "http://ae.priceomania.com/mobileappwebservices/getchildcategory?catId=14";
    private String subCategoryId;

    private ChildAdapter mExampleAdapter;
    private ArrayList<Child> mExampleList;
    private RequestQueue mRequestQueue;
    private RecyclerView sRecyclerview;

    private String Prdname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        subCategoryId=pref.getString("pid","");
        Prdname=pref.getString("pname","");

        getSupportActionBar().setTitle(Prdname);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Log.e("responce",subCategoryId);

        mExampleList = new ArrayList<>();
        sRecyclerview=(RecyclerView)findViewById(R.id.plist);
        sRecyclerview.setNestedScrollingEnabled(false);
        sRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRequestQueue = Volley.newRequestQueue(this);
        sRecyclerview.setHasFixedSize(true);

        parseJSON1();
    }

    private void parseJSON1() {

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AllUrls.CHILD_CATEG+subCategoryId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.INVISIBLE);

                        Log.e("responce",response );


                        try {

                            JSONObject rootJsonObject = new JSONObject(response);

                            JSONArray subCategoryArray = rootJsonObject.getJSONArray("child_category");
                            //Log.e("subCategoryArray", subCategoryArray.length() + "");

                            for (int i = 0; i < subCategoryArray.length(); i++) {
                                JSONObject object = subCategoryArray.getJSONObject(i);

                                mExampleList.add(new Child(object.optString("category_id"),
                                        object.optString("category_name"),
                                        object.optString("category_image"),
                                        object.optString("child_category_type")));
                            }

                            Log.e("rootJsonArray", String.valueOf(mExampleList));

                            mExampleAdapter = new ChildAdapter(ChildActivity.this, mExampleList);
                            sRecyclerview.setAdapter(mExampleAdapter);
                            mExampleAdapter.notifyDataSetChanged();
                            sRecyclerview.setHasFixedSize(true);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        //Log.e("TAg",error.getMessage());
                    }
                });

        mRequestQueue = Volley.newRequestQueue(ChildActivity.this);
        mRequestQueue.add(stringRequest);
    }
}




