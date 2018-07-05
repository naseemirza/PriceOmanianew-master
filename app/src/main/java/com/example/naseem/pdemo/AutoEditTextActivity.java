package com.example.naseem.pdemo;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.naseem.pdemo.CardDetailsPkg.CardDetails;
import com.example.naseem.pdemo.CategoryItems.ChildActivity;
import com.example.naseem.pdemo.CategoryItems.ParentActivity;
import com.example.naseem.pdemo.MoreSites.CardModel;
import com.example.naseem.pdemo.MoreSites.CustomAdapterSite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class AutoEditTextActivity extends Activity {

    //private RequestQueue mRequestQueue;
    ImageView imageview;
    ImageButton imageButton;
    ListView listView;
    CardView cardView1,cardView2,cardView3;
    ArrayAdapter<String > ItemAdapter = null;
    //ArrayAdapter<String> ItemAdapter1 = null;
    AutoCompleteTextView autoCompleteTextView;

    private String MORE_SITE_URL="http://ae.priceomania.com/assets/search/compare.json";

    private AutoTextAdapter mExampleAdapter;
    private ArrayList<AutoTextModel> mExampleList;
    private RequestQueue mRequestQueue;
    private RecyclerView sRecyclerview;
    String pid;

    EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_edit_text);



        //autoCompleteTextView= (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

        editTextSearch=(EditText)findViewById(R.id.autoCompleteTextView1);
//
//        editTextSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                //after the change calling the method and passing the search input
//                filter(editable.toString());
//            }
//        });



        imageview = (ImageView)findViewById(R.id.imageView1);
        imageview.setVisibility(View.GONE);

        cardView1 = (CardView) findViewById(R.id.cardcategory);
        cardView1.setVisibility(View.GONE);
        imageButton = (ImageButton) findViewById(R.id.bachbtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AutoEditTextActivity.this,MainActivity.class));
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

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://ae.priceomania.com/assets/search/compare.json",
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
                                        object.optString("store_count")));
                            }

                            Log.e("rootJsonArray",mExampleList.size()+"");

                            mExampleAdapter = new AutoTextAdapter(AutoEditTextActivity.this, mExampleList);
                            sRecyclerview.setAdapter(mExampleAdapter);
                            mExampleAdapter.notifyDataSetChanged();
                            sRecyclerview.setHasFixedSize(true);
                            sRecyclerview.setVisibility(View.GONE);
                            //filters

                            editTextSearch=(EditText)findViewById(R.id.autoCompleteTextView1);

                            editTextSearch.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                    imageview.setVisibility(View.VISIBLE);
                                    sRecyclerview.setVisibility(View.VISIBLE);

                                  // mExampleAdapter.getFilter().filter(s.toString());

                                    sRecyclerview.setAdapter(mExampleAdapter);

                                }

                                @Override
                                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                }

                                @Override
                                public void afterTextChanged(Editable editable) {
                                    //after the change calling the method and passing the search input
                                    filter(editable.toString());
                                }
                            });

                            imageview.setOnClickListener(new View.OnClickListener() {
//
                                @Override
                                public void onClick(View v) {

                                    editTextSearch.getText().clear();
                                    imageview.setVisibility(View.GONE);
                                    sRecyclerview.setVisibility(View.GONE);
                                    cardView1.setVisibility(View.GONE);
//                                    cardView2.setVisibility(View.GONE);
//                                    cardView3.setVisibility(View.GONE);


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
            if (s.getmName().contains(text)) {
                filterdNames.add(s);
            }
        }
        mExampleAdapter.filterList(filterdNames);
    }


//    private void parseJSON() {
////
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://ae.priceomania.com/assets/search/compare.json",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            Log.e("rootJsonArray",response);
//
//                            JSONArray rootJsonArray = new JSONArray(response);
//                            JSONObject json= null;
//                            final String[] str1 = new String[rootJsonArray.length()];
//
//                            Log.e("rootJsonArrayLength",rootJsonArray.length()+"");
//
//                            for (int i = 0; i < rootJsonArray.length(); i++) {
//                                json=rootJsonArray.getJSONObject(i);
//                                str1[i]=json.getString("modelName");
//
//
//                            }
//                            final AutoCompleteTextView text = (AutoCompleteTextView)
//                                    findViewById(R.id.autoCompleteTextView1);
////                            TextView textView=(TextView)findViewById(R.id.txt2);
////                            String textitem=text.getText().toString();
////                            textView.setText(textitem);
//
//                            final List<String> list = new ArrayList<String>();
//
//                            for(int i=0;i<str1.length;i++)
//                            {
//                                list.add(str1[i]);
//                            }
//
//                            Collections.sort(list);
//
//                            final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
//                                    (getApplicationContext(), R.layout.autocompletetext,R.id.textView, list);
//
//
//                            listView=(ListView)findViewById(R.id.list) ;
//                            listView.setVisibility(View.VISIBLE);
//                             text.setThreshold(1);
//                            text.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                imageview.setVisibility(View.VISIBLE);
//                listView.setVisibility(View.VISIBLE);
//                cardView1.setVisibility(View.VISIBLE);
////                cardView2.setVisibility(View.VISIBLE);
////                cardView3.setVisibility(View.VISIBLE);
//               dataAdapter.getFilter().filter(s.toString());
//                listView.setAdapter(dataAdapter);
//
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//                            imageview.setOnClickListener(new View.OnClickListener() {
//
//                                @Override
//                                public void onClick(View v) {
//
//                                    text.getText().clear();
//                                    imageview.setVisibility(View.GONE);
//                                    listView.setVisibility(View.GONE);
//                                    cardView1.setVisibility(View.GONE);
////                                    cardView2.setVisibility(View.GONE);
////                                    cardView3.setVisibility(View.GONE);
//
//
//                                }
//                            });
//
//                           // listView.setVisibility(View.GONE);
//                            //text.setAdapter(dataAdapter);
//
//                            //text.setThreshold(1);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                startActivity(new Intent(AutoEditTextActivity.this, CardDetails.class));
//            }
//        });
//
//
//
////                            text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////
////                                @Override
////                                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
////                                    // TODO Auto-generated method stub
////                                    Toast.makeText(getBaseContext(), list.get(arg2).toString(),
////                                            Toast.LENGTH_SHORT).show();
////                                }
////                            });
//
//
//
//                        } catch (JSONException e) {
//                            Log.e("Fail 3", e.toString());
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.e("TAg",error.getMessage());
//                    }
//                });
//
//        mRequestQueue = Volley.newRequestQueue(this);
//        mRequestQueue.add(stringRequest);
//    }



}