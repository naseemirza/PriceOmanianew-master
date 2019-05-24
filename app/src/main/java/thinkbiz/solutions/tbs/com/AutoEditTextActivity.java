package thinkbiz.solutions.tbs.com;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import thinkbiz.solutions.tbs.com.CategoryItems.GridActivity;
import thinkbiz.solutions.tbs.com.DetailPage.DetailPageActivity;


public class AutoEditTextActivity extends Activity {

    ImageView imageview,imageViewsrch;
    ImageButton imageButton;
    CardView cardView1;
    private AutoTextAdapter mExampleAdapter;
    private ArrayList<AutoTextModel> mExampleList;
    private RequestQueue mRequestQueue;
    private RecyclerView sRecyclerview;
    String pid;
    AutoCompleteTextView editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_edit_text);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editTextSearch=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);

        imageview = (ImageView)findViewById(R.id.imageView1);
        imageview.setVisibility(View.GONE);
        imageViewsrch = (ImageView)findViewById(R.id.imageViewsrch);
        imageViewsrch.setVisibility(View.GONE);

        cardView1 = (CardView) findViewById(R.id.cardcategory);
        cardView1.setVisibility(View.GONE);
        imageButton = (ImageButton) findViewById(R.id.bachbtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(AutoEditTextActivity.this,MainActivity.class));
            }
        });


        mExampleList = new ArrayList<>();
        sRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        sRecyclerview.setNestedScrollingEnabled(false);
        sRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRequestQueue = Volley.newRequestQueue(this);
        sRecyclerview.setHasFixedSize(true);

        parseJSON();

    }

    private void parseJSON() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AllUrls.AUTO_COMPL_TEXT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("rootJsonArray",response);
                            JSONArray rootJsonArray = new JSONArray(response);

                            Log.e("rootJsonArrayLength",rootJsonArray.length()+"");

                            for (int i = 0; i < rootJsonArray.length(); i++) {
                                JSONObject object = rootJsonArray.getJSONObject(i);

                                mExampleList.add(new AutoTextModel(object.optString("modelID"),
                                        object.optString("productImage"),
                                        object.optString("modelName"),
                                        object.optString("currency"),
                                        object.optString("price"),
                                        object.optString("store_count"),
                                        object.optString("slug"),
                                        object.optString("slug_suffix")));
                            }

                            Log.e("rootJsonArray",mExampleList.size()+"");

                            mExampleAdapter = new AutoTextAdapter(AutoEditTextActivity.this, mExampleList);
                            sRecyclerview.setAdapter(mExampleAdapter);
                            mExampleAdapter.notifyDataSetChanged();
                            sRecyclerview.setHasFixedSize(true);
                            sRecyclerview.setVisibility(View.GONE);

                            //filters
                            editTextSearch=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);

                            editTextSearch.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                    imageview.setVisibility(View.VISIBLE);
                                    imageViewsrch.setVisibility(View.VISIBLE);
                                    sRecyclerview.setVisibility(View.VISIBLE);
                                    sRecyclerview.setAdapter(mExampleAdapter);
                                }

                                @Override
                                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                    if (charSequence.toString().trim().length()==0){
                                        editTextSearch.getText().clear();
                                        imageview.setVisibility(View.GONE);
                                        imageViewsrch.setVisibility(View.GONE);
                                        sRecyclerview.setVisibility(View.GONE);
                                        cardView1.setVisibility(View.GONE);
                                    }
                                    else {
                                        imageview.setVisibility(View.VISIBLE);
                                        imageViewsrch.setVisibility(View.VISIBLE);
                                        sRecyclerview.setVisibility(View.VISIBLE);
                                        // mExampleAdapter.getFilter().filter(s.toString());
                                        sRecyclerview.setAdapter(mExampleAdapter);
                                    }

                                }

                                @Override
                                public void afterTextChanged(Editable editable) {
                                    filter(editable.toString());

                                }
                            });

                            imageViewsrch.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String search=editTextSearch.getText().toString();

                                    SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor edit = pref.edit();
                                    edit.putString("keyword", search);
                                    edit.apply();

                                    Intent intent = new Intent(AutoEditTextActivity.this, KeyWordSearchActivity.class);
                                    startActivity(intent);

                                }
                            });

                            imageview.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    editTextSearch.getText().clear();
                                    imageview.setVisibility(View.GONE);
                                    imageViewsrch.setVisibility(View.GONE);
                                    sRecyclerview.setVisibility(View.GONE);
                                    cardView1.setVisibility(View.GONE);
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("TAg",error.getMessage());
                    }
                });

        mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueue.add(stringRequest);
    }

    private void filter(String text) {
        ArrayList<AutoTextModel> filterdNames = new ArrayList<>();

        for (AutoTextModel s : mExampleList) {

            //s.toLowerCase().contains(text.toLowerCase())
            //s.getmName().contains(text)
            if (s.getmName().toLowerCase().contains(text.toLowerCase())) {
                filterdNames.add(s);
            }

        }
        mExampleAdapter.filterList(filterdNames);
    }

}