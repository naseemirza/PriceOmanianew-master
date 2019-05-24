package thinkbiz.solutions.tbs.com.DetailPage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

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

import thinkbiz.solutions.tbs.com.Adapter;
import thinkbiz.solutions.tbs.com.AllUrls;
import thinkbiz.solutions.tbs.com.App;
import thinkbiz.solutions.tbs.com.CardDetailsPkg.CardAdapter;
import thinkbiz.solutions.tbs.com.CardDetailsPkg.CardDetailsApp;
import thinkbiz.solutions.tbs.com.MainActivity;
import thinkbiz.solutions.tbs.com.R;

public class DetailPageActivity extends AppCompatActivity {

    WebView mywebview;
    ProgressDialog progressDialog;
    String slug,slug_suffix,pid;
    String Actname ;
    TextView textname;

    //Related products

    private Adapter mExampleAdapter1;
    private ArrayList<App> mExampleList1;
    private RequestQueue mRequestQueue1;
    private RecyclerView mRecyclerview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.backbar);
        View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
//                String actname="Profile";
//                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//                SharedPreferences.Editor edit = pref.edit();
//
//                edit.putString("Actvname",actname);
//                edit.apply();
//                Intent intent=new Intent(AccDetailsActivity.this, ProfileActivity.class);
//                startActivity(intent);
            }
        });

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Actname=pref.getString("name","");
        slug=pref.getString("slug","");
        //pid=pref.getString("usermessage","");
        slug_suffix=pref.getString("slug_suffix","");
        textname=(TextView)findViewById(R.id.textname);
        textname.setText(Actname);

        //Log.e("rootJsonArray",pid);


        mywebview = (WebView) findViewById(R.id.webView1);
        mywebview.setWebViewClient(new MyWebViewClient());
        String url= AllUrls.DETAIL_PAGE+slug+slug_suffix;
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mywebview.loadUrl(url);


        //Related product

        mExampleList1 = new ArrayList<>();
        mRequestQueue1 = Volley.newRequestQueue(this);

        mRecyclerview1=(RecyclerView)findViewById(R.id.recyclerviewsmlr);
        mRecyclerview1.setNestedScrollingEnabled(false);
        mRecyclerview1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mRecyclerview1.setHasFixedSize(true);

        parseJSON();

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //view.loadUrl(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressDialog = new ProgressDialog(DetailPageActivity.this);
            progressDialog.setMessage("Please wait ...");
            progressDialog.setProgressStyle(90);
            progressDialog.show();
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    private void parseJSON() {

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://ae.priceomania.com/mobileappwebservices/getrelatedproducts?"+slug+slug_suffix,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.INVISIBLE);

                        Log.e("rootJsonArray",response);
                        try {

                            JSONObject rootJsonArray = new JSONObject(response);
                            JSONArray subCategoryArray = rootJsonArray.getJSONArray("relatedproducts");

                            //Log.e("rootJsonArrayLength",rootJsonArray.length()+"");

                            for (int i = 0; i < subCategoryArray.length(); i++) {
                                JSONObject object = subCategoryArray.getJSONObject(i);

                                mExampleList1.add(new App(object.optString("id"),
                                        object.optString("product_image"),
                                        object.optString("model_name"),
                                        object.optString("currency_type"),
                                        object.optString("price"),
                                        object.optString("store_count"),
                                        object.optString("slug"),
                                        object.optString("slug_suffix")));
                            }

                            Log.e("rootJsonArray",mExampleList1.size()+"");

                            mExampleAdapter1 = new Adapter(DetailPageActivity.this, mExampleList1);
                            mRecyclerview1.setAdapter(mExampleAdapter1);
                            mExampleAdapter1.notifyDataSetChanged();
                            mRecyclerview1.setHasFixedSize(true);

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
                        Log.e("TAG", Log.getStackTraceString(error));
                    }
                });

        mRequestQueue1 = Volley.newRequestQueue(this);
        mRequestQueue1.add(stringRequest);
    }
}
