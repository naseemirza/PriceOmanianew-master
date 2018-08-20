package thinkbiz.solutions.tbs.com.CategoryItems;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

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

public class GridActivity extends AppCompatActivity {

    private GridAdapter mExampleAdapter;
    private ArrayList<GridModel> mExampleList;
    private RequestQueue mRequestQueue;
    private RecyclerView sRecyclerview;

    String pid;
    private String Prdname;

    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);



        relativeLayout=(RelativeLayout)findViewById(R.id.reltvlayout);

        SharedPreferences pref = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        pid = pref.getString("cid", "");
        Prdname=pref.getString("pname","");

        getSupportActionBar().setTitle(Prdname);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.e("responce",pid);



        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        sRecyclerview=(RecyclerView)findViewById(R.id.recyclerview5);
        sRecyclerview.setNestedScrollingEnabled(false);

        sRecyclerview.setLayoutManager(new GridLayoutManager(this,2));

        sRecyclerview.setHasFixedSize(true);
        parseJSON();

    }

    private void parseJSON() {

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AllUrls.GRID_CATEG+pid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.INVISIBLE);

                        Log.e("responce",response );


                        try {

                            JSONObject rootJsonObject = new JSONObject(response);

                            JSONArray subCategoryArray = rootJsonObject.getJSONArray("category_data");
                            //Log.e("subCategoryArray", subCategoryArray.length() + "");

                            for (int i = 0; i < subCategoryArray.length(); i++) {
                                JSONObject object = subCategoryArray.getJSONObject(i);

                                mExampleList.add(new GridModel(object.optString("id"),
                                        object.optString("modelno"),
                                        object.optString("product_image"),
                                        object.optString("currency_type"),
                                        object.optString("price"),
                                        object.optString("store_count")));

                            }

                                Log.e("rootJsonArray", String.valueOf(mExampleList));

                                mExampleAdapter = new GridAdapter(GridActivity.this, mExampleList);

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

        mRequestQueue = Volley.newRequestQueue(GridActivity.this);
        mRequestQueue.add(stringRequest);
    }

}
