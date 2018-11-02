package thinkbiz.solutions.tbs.com.CategoryItems;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class Sub_ChildActivity extends AppCompatActivity {

    private String subCategoryId1;

    private SubChildAdapter mExampleAdapter;
    private ArrayList<SubChild> mExampleList;
    private RequestQueue mRequestQueue;
    private RecyclerView sRecyclerview;
    private String Prdname;
    TextView textname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__child);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.backbar);
        View view =getSupportActionBar().getCustomView();


        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        subCategoryId1=pref.getString("cid","");
        Prdname=pref.getString("pname","");

        textname=(TextView)findViewById(R.id.textname);
        textname.setText(Prdname);

        Log.e("responce",subCategoryId1);

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AllUrls.CHILD_CATEG+subCategoryId1,
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

                                mExampleList.add(new SubChild(object.optString("id"),
                                        object.optString("category_name"),
                                        object.optString("category_image"),
                                        object.optString("child_category_type")));
                            }

                            Log.e("rootJsonArray", String.valueOf(mExampleList));

                            mExampleAdapter = new SubChildAdapter(Sub_ChildActivity.this, mExampleList);
                            sRecyclerview.setAdapter(mExampleAdapter);
                            mExampleAdapter.notifyDataSetChanged();
                            sRecyclerview.setHasFixedSize(true);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (mExampleList.size()==0){
                            startActivity(new Intent(Sub_ChildActivity.this,GridActivity.class));
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

        mRequestQueue = Volley.newRequestQueue(Sub_ChildActivity.this);
        mRequestQueue.add(stringRequest);
    }
}

