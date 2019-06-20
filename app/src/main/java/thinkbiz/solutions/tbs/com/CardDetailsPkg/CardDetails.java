package thinkbiz.solutions.tbs.com.CardDetailsPkg;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import thinkbiz.solutions.tbs.com.AllUrls;
import thinkbiz.solutions.tbs.com.MoreOptionsPkg.OptionsActivity;
import thinkbiz.solutions.tbs.com.Options.MoreOptionActivity;
import thinkbiz.solutions.tbs.com.PriceChartActivity;
import thinkbiz.solutions.tbs.com.R;

import thinkbiz.solutions.tbs.com.MoreSites.CardModel;
import thinkbiz.solutions.tbs.com.MoreSites.CustomAdapterSite;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;

public class CardDetails extends AppCompatActivity {

    PlaceholderFragment.SectionsPagerAdapter mSectionsPagerAdapter;

    ViewPager mViewPager;
    TextView PrdTextName;
    String prdname;
    String Tcount;

   public String ustring;
    public TextView textViewname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        mSectionsPagerAdapter = new PlaceholderFragment.SectionsPagerAdapter(getSupportFragmentManager());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PrdTextName=(TextView)findViewById(R.id.prdnametext);
       // textViewcurncy = (TextView)findViewById(R.id.crncytype);
       // textViewprice = (TextView) findViewById(R.id.pricetext1);
       // imageView = (ImageView) findViewById(R.id.cardimg);

        SharedPreferences pref = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


        prdname = pref.getString("name", "");
        ustring = pref.getString("usermessage", "");


//        Log.e("pid",ustring);
//        Log.e("pid",prdname);
//        Log.e("pid",mcrncy);
//        Log.e("pid",mimage);
//        Log.e("pid",mprice);



          PrdTextName.setText(prdname);
//        Glide.with(this)
//                .load(mimage)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .fitCenter()
//                .into(imageView);
////
//         // textViewname.setText(mname);
//        textViewprice.setText(mprice);
//        textViewcurncy.setText(mcrncy);



       // SharedPreferences pref = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

//        ustring = pref.getString("usermessage", "");
//        mname = pref.getString("name", "");
//        mcrncy = pref.getString("currency", "");
//        mprice = pref.getString("price", "");
//        mimage = pref.getString("cardimage", "");

        Tcount=pref.getString("totalcount","");

         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         mSectionsPagerAdapter = new PlaceholderFragment.SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabTextColors(R.color.colorBlack, R.color.colorBlack);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        private RecyclerView mRecyclerview;
        private RecyclerView recyclerView;
        //LinearLayout sliderDotspanel;
        private int dotscount;
        LinearLayout dialogbutton;
        String image_url;
        private ImageView[] dots;
        //ViewPager viewPager;
        public TextView textViewname, textViewcurncy, textViewprice, textViewcount;
        public TextView textViewclr, textViewstrg, textViewntwk;
        public ImageView imageView;

        LinearLayout mLinearLayoutDetalis;
        LinearLayout mLinearLayoutMore;
        LinearLayout mLinearLayoutLess;



        String color,strg,netwk;

        // more sites

        private String MORE_SITE_URL="https://ae.priceomania.com/mobileappwebservices/getCompareProductData?pid=";
        private CustomAdapterSite mExampleAdapter;
        private ArrayList<CardModel> mExampleList;
        private RequestQueue mRequestQueue;
        private RecyclerView sRecyclerview;



        //similar products

        private CardAdapter mExampleAdapter1;
        private ArrayList<CardDetailsApp> mExampleList1;
        private RequestQueue mRequestQueue1;
        private RecyclerView mRecyclerview1;

       //most related prd
        private CardAdapter mExampleAdapter2;
        private ArrayList<CardDetailsApp> mExampleList2;
        private RequestQueue mRequestQueue2;
        private RecyclerView mRecyclerview2;

        //product faqs
        ExpandableListView expandableListView;
        ExpandableListAdapter expandableListAdapter;
        List<String> expandableListTitle;
        HashMap<String, List<String>> expandableListDetail;

        WebView mywebview,mywebviewfaq,mywebviewspc;
        ProgressDialog progressDialog;

        String pid;
        String websitid;
        String mprice, mcrncy, mimage;
        TextView brandtxt, ostxt,storagetxt, memorytxt,simtxt,colortxt,displaytxt,networktxt;

        ImageButton ImgBtn;

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                View rootView = inflater.inflate(R.layout.activity_tab1, container, false);


               // textViewcurncy = (TextView) rootView.findViewById(R.id.crncytype);
              //  textViewprice = (TextView) rootView.findViewById(R.id.pricetext1);
              //  imageView = (ImageView) rootView.findViewById(R.id.cardimg);

//                SharedPreferences pref = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//
//                mcrncy = pref.getString("currency", "");
//                mprice = pref.getString("price", "");
//                mimage = pref.getString(

                SharedPreferences pref = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                pid = pref.getString("usermessage", "");
               // mcrncy = pref.getString("currency", "");
               // mprice = pref.getString("price", "");
               // mimage = pref.getString("cardimage", "");


              //  PrdTextName.setText(prdname);
//                Glide.with(this)
//                        .load(mimage)
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .fitCenter()
//                        .into(imageView);
//
//                //textViewname.setText(mname);
//                textViewprice.setText(mprice);
//                textViewcurncy.setText(mcrncy);

                //ImgBtn=(ImageButton)rootView.findViewById(R.id.chartbtn);

                textViewcurncy = (TextView) rootView.findViewById(R.id.crncytype);
                textViewprice = (TextView) rootView.findViewById(R.id.pricetext1);
                imageView = (ImageView) rootView.findViewById(R.id.cardimg);

                textViewclr = (TextView) rootView.findViewById(R.id.mcolor);
                textViewstrg = (TextView) rootView.findViewById(R.id.mmemory);
                textViewntwk = (TextView) rootView.findViewById(R.id.mnetwork);

                Intent intent = getActivity().getIntent();
                Bundle b = intent.getExtras();

                if (b != null) {

                    String color = (String) b.get("color");
                    textViewclr.setText(color);
                    String strg = (String) b.get("storage");
                    textViewstrg.setText(strg);
                    String ntwk = (String) b.get("network");
                    textViewntwk.setText(ntwk);

                }

//                ImgBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(getActivity(), PriceChartActivity.class));
//                    }
//                });

//                Glide.with(getActivity())
//                        .load(mimage)
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .fitCenter()
//                        .into(imageView);
//
//                textViewprice.setText(mprice);
//                textViewcurncy.setText(mcrncy);


                //moreinfo

//                mLinearLayoutLess = (LinearLayout) rootView.findViewById(R.id.linrless);
//                mLinearLayoutDetalis = (LinearLayout) rootView.findViewById(R.id.expandable);
//                mLinearLayoutDetalis.setVisibility(View.GONE);
//                mLinearLayoutMore = (LinearLayout) rootView.findViewById(R.id.linrtextmore);
//
//                mLinearLayoutMore.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        mLinearLayoutMore.setVisibility(View.GONE);
//                        expand();
//
//                    }
//                });
//                mLinearLayoutLess.setOnClickListener(new View.OnClickListener() {
//
//
//                    @Override
//                    public void onClick(View v) {
//                        mLinearLayoutMore.setVisibility(View.VISIBLE);
//                        collapse();
//
//                    }
//                });

//                TextView optiontext = (TextView) rootView.findViewById(R.id.moreoptiontxt);
//                optiontext.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                                                        //MoreOptionActivity
//                        startActivity(new Intent(getActivity(), OptionsActivity.class));
//                    }
//                });

                //replace text with button

//                TextView optiontext = (TextView) rootView.findViewById(R.id.options);
//                optiontext.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        startActivity(new Intent(getActivity(), MoreOptionActivity.class));
//                    }
//                });

                PrdDetsils();

                dialogbutton = (LinearLayout) rootView.findViewById(R.id.optioncard);
                dialogbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String color=textViewclr.getText().toString();
                        String storage=textViewstrg.getText().toString();
                        String network=textViewntwk.getText().toString();

                        SharedPreferences pref = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = pref.edit();
                        edit.putString("color",color);
                        edit.putString("storag",storage);
                        edit.putString("netwk",network);

                        edit.apply();
                        Intent intent = new Intent(getActivity(), DialogActivity.class);

                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    }
                });

                //moresites

                mExampleList = new ArrayList<>();
                sRecyclerview = (RecyclerView) rootView.findViewById(R.id.siterecycl);
                sRecyclerview.setNestedScrollingEnabled(false);
                sRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                mRequestQueue = Volley.newRequestQueue(getActivity());
                sRecyclerview.setHasFixedSize(true);

                parseJSON();

                //similar product

                mExampleList1 = new ArrayList<>();
                mRequestQueue1 = Volley.newRequestQueue(getActivity());
                mRecyclerview1 = (RecyclerView) rootView.findViewById(R.id.recyclerviewsmlr);
                mRecyclerview1.setNestedScrollingEnabled(false);
                mRecyclerview1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                mRecyclerview1.setHasFixedSize(true);

                parseJSON1();

                // most similar product

                mExampleList2 = new ArrayList<>();
                mRequestQueue2 = Volley.newRequestQueue(getActivity());
                mRecyclerview2 = (RecyclerView) rootView.findViewById(R.id.recyclerviewsmlrmost);
                mRecyclerview2.setNestedScrollingEnabled(false);
                mRecyclerview2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                mRecyclerview2.setHasFixedSize(true);
                parseJSON2();

                //CardPager cardPager = new CardPager(this.getActivity());

                return rootView;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                View rootView = inflater.inflate(R.layout.activity_tab2, container, false);

                SharedPreferences pref = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                pid = pref.getString("usermessage", "");

                mywebview = (WebView) rootView.findViewById(R.id.webView1);
                mywebview.setWebViewClient(new MyWebViewClient());
                String url= "https://ae.priceomania.com/mobileappwebservices/getDescription?models_id="+pid;
                mywebview.getSettings().setJavaScriptEnabled(true);
                mywebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                mywebview.loadUrl(url);

                //similar product

                mExampleList1 = new ArrayList<>();
                mRequestQueue1 = Volley.newRequestQueue(getActivity());
                mRecyclerview1 = (RecyclerView) rootView.findViewById(R.id.recyclerviewsmlr);
                mRecyclerview1.setNestedScrollingEnabled(false);
                mRecyclerview1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                mRecyclerview1.setHasFixedSize(true);

                parseJSON1();

                // most similar product

                mExampleList2 = new ArrayList<>();
                mRequestQueue2 = Volley.newRequestQueue(getActivity());
                mRecyclerview2 = (RecyclerView) rootView.findViewById(R.id.recyclerviewsmlrmost);
                mRecyclerview2.setNestedScrollingEnabled(false);
                mRecyclerview2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                mRecyclerview2.setHasFixedSize(true);

                parseJSON2();

                return rootView;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                View rootView = inflater.inflate(R.layout.activity_tab3, container, false);

                SharedPreferences pref = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                pid = pref.getString("usermessage", "");

                mywebviewfaq = (WebView) rootView.findViewById(R.id.webViewfaq);
                mywebviewfaq.setWebViewClient(new MyWebViewClient());
                String url= "https://ae.priceomania.com/mobileappwebservices/getFaq?models_id="+pid;
                mywebviewfaq.getSettings().setJavaScriptEnabled(true);
                mywebviewfaq.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                mywebviewfaq.loadUrl(url);

                //similar product

                mExampleList1 = new ArrayList<>();
                mRequestQueue1 = Volley.newRequestQueue(getActivity());

                mRecyclerview1 = (RecyclerView) rootView.findViewById(R.id.recyclerviewsmlr);
                mRecyclerview1.setNestedScrollingEnabled(false);
                mRecyclerview1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                mRecyclerview1.setHasFixedSize(true);
                parseJSON1();


                // most similar product

                mExampleList2 = new ArrayList<>();
                mRequestQueue2 = Volley.newRequestQueue(getActivity());

                mRecyclerview2 = (RecyclerView) rootView.findViewById(R.id.recyclerviewsmlrmost);
                mRecyclerview2.setNestedScrollingEnabled(false);
                mRecyclerview2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                mRecyclerview2.setHasFixedSize(true);
                parseJSON2();

//                expandableListView = (ExpandableListView)rootView.findViewById(R.id.expandableListView);
//                expandableListDetail = ExpandableListDataPump.getData();
//                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
//                expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
//                expandableListView.setAdapter(expandableListAdapter);
//                expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//                    @Override
//                    public void onGroupExpand(int groupPosition) {
////                        Toast.makeText(getActivity().getApplicationContext(),
////                                expandableListTitle.get(groupPosition) + " List Expanded.",
////                                Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//                    @Override
//                    public void onGroupCollapse(int groupPosition) {
////                        Toast.makeText(getActivity().getApplicationContext(),
////                                expandableListTitle.get(groupPosition) + " List Collapsed.",
////                                Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//                expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//                    @Override
//                    public boolean onChildClick(ExpandableListView parent, View v,
//                                                int groupPosition, int childPosition, long id) {
////                        Toast.makeText(
////                                getActivity().getApplicationContext(),
////                                expandableListTitle.get(groupPosition)
////                                        + " -> "
////                                        + expandableListDetail.get(
////                                        expandableListTitle.get(groupPosition)).get(
////                                        childPosition), Toast.LENGTH_SHORT
////                        ).show();
//                        return false;
//                    }
//                });
                return rootView;

            }else if (getArguments().getInt(ARG_SECTION_NUMBER) == 4) {
                View rootView = inflater.inflate(R.layout.activity_specf, container, false);

                SharedPreferences pref = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                pid = pref.getString("usermessage", "");

                brandtxt=(TextView)rootView.findViewById(R.id.brand);
                ostxt=(TextView)rootView.findViewById(R.id.ostype);
                storagetxt=(TextView)rootView.findViewById(R.id.storage);
                memorytxt=(TextView)rootView.findViewById(R.id.memory);
                simtxt=(TextView)rootView.findViewById(R.id.sim);
                colortxt=(TextView)rootView.findViewById(R.id.color);
                displaytxt=(TextView)rootView.findViewById(R.id.display);
                networktxt=(TextView)rootView.findViewById(R.id.network);

                //similar product

                mExampleList1 = new ArrayList<>();
                mRequestQueue1 = Volley.newRequestQueue(getActivity());

                mRecyclerview1 = (RecyclerView) rootView.findViewById(R.id.recyclerviewsmlr);
                mRecyclerview1.setNestedScrollingEnabled(false);
                mRecyclerview1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                mRecyclerview1.setHasFixedSize(true);
                parseJSON1();

                // most similar product

                mExampleList2 = new ArrayList<>();
                mRequestQueue2 = Volley.newRequestQueue(getActivity());
                mRecyclerview2 = (RecyclerView) rootView.findViewById(R.id.recyclerviewsmlrmost);
                mRecyclerview2.setNestedScrollingEnabled(false);
                mRecyclerview2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                mRecyclerview2.setHasFixedSize(true);
                parseJSON2();

                SpeciData();

                return rootView;
            }

            else
                {

                View rootView = inflater.inflate(R.layout.fragment_card_details, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
              }
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
                progressDialog = new ProgressDialog(getActivity());
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


        private void PrdDetsils() {

            //Log.e("resp",pid);

            String url="https://ae.priceomania.com/mobileappwebservices/compareData?pid="+pid;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject rootJsonObject = new JSONObject(response);
                                JSONArray subCategoryArray = rootJsonObject.getJSONArray("PRODUCTDETAIL");

                                for (int i = 0; i < subCategoryArray.length(); i++) {
                                    JSONObject obj = subCategoryArray.getJSONObject(i);

                                    String image=obj.getString("product_image");
                                    String currency=obj.getString("currency_type");
                                    String price=obj.getString("price");

                                    String image_path = "http://ae.priceomania.com/backend/ProductImage/"+image;

                                    // Log.e("img",image_path);
                                    Glide.with(getActivity())
                                            .load(image_path)
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .fitCenter()
                                            .into(imageView);

                                    textViewcurncy.setText(currency);
                                    textViewprice.setText(price);
                                }

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

            RequestQueue queue2 = Volley.newRequestQueue(getActivity());
            queue2.add(stringRequest);
        }


        //sites name in details page

        private void parseJSON() {

            StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://ae.priceomania.com/mobileappwebservices/compareData?pid="+pid,
                    new Response.Listener<String>() {
                        @Override

                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String prod_details_json = jsonObject.getString("prod_details_json");
                                JSONObject jsonObject1= new JSONObject(prod_details_json);
                                JSONArray jsonArray= jsonObject1.getJSONArray("cmp_store");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                     websitid=object.getString("website_id");
                                    //Log.e("wid",websitid);

                                    //if (websitid!=object.getString("website_id"))

                                    mExampleList.add(new CardModel(object.optString("id"),
                                            object.optString("product_name"),
                                            object.optString("website_name"),
                                            object.optString("website_logo"),
                                            object.optString("currency_type"),
                                            object.optString("price"),
                                            object.optString("website_url")));
                                }
                                //if (mExampleList.contains("website_id"))

                                mExampleAdapter = new CustomAdapterSite(getActivity(), mExampleList);
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

            mRequestQueue = Volley.newRequestQueue(getActivity());
            mRequestQueue.add(stringRequest);
        }

        //similar product

        private void parseJSON1() {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, AllUrls.SIMILAR_PROD+pid,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject rootJsonObject = new JSONObject(response);
                                JSONArray subCategoryArray = rootJsonObject.getJSONArray("relatedproducts");
                                //Log.e("subCategoryArray", subCategoryArray.length() + "");

                                for (int i = 0; i < subCategoryArray.length(); i++) {
                                    JSONObject object = subCategoryArray.getJSONObject(i);

                                    mExampleList1.add(new CardDetailsApp(object.optString("id"),
                                            object.optString("product_image"),
                                            object.optString("model_name"),
                                            object.optString("currency_type"),
                                            object.optString("price"),
                                            object.optString("store_count")));
                                }
                                Log.e("rootJsonArray",mExampleList1.size()+"");

                                mExampleAdapter1 = new CardAdapter(getActivity(), mExampleList1);
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
                            Log.e("TAg",error.getMessage());
                        }
                    });

            mRequestQueue1 = Volley.newRequestQueue(getActivity());
            mRequestQueue1.add(stringRequest);
        }

        // most similar product

        private void parseJSON2() {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://ae.priceomania.com/mobileappwebservices/getPopularProducts?prod_id="+pid,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject rootJsonObject = new JSONObject(response);
                                JSONArray subCategoryArray = rootJsonObject.getJSONArray("PopularProducts");
                                //Log.e("subCategoryArray", subCategoryArray.length() + "");

                                for (int i = 0; i < subCategoryArray.length(); i++) {
                                    JSONObject object = subCategoryArray.getJSONObject(i);
                                    mExampleList2.add(new CardDetailsApp(object.optString("id"),
                                            object.optString("product_image"),
                                            object.optString("model_name"),
                                            object.optString("currency_type"),
                                            object.optString("price"),
                                            object.optString("store_count")));
                                }

                                Log.e("rootJsonArray",mExampleList2.size()+"");

                                mExampleAdapter2 = new CardAdapter(getActivity(), mExampleList2);
                                mRecyclerview2.setAdapter(mExampleAdapter2);
                                mExampleAdapter2.notifyDataSetChanged();
                                mRecyclerview2.setHasFixedSize(true);

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

            mRequestQueue2 = Volley.newRequestQueue(getActivity());
            mRequestQueue2.add(stringRequest);
        }

        private void SpeciData() {

            //Log.e("resp",pid);

            String url="https://ae.priceomania.com/mobileappwebservices/getFeatures?models_id="+pid;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONArray rootJsonArray = new JSONArray(response);
                                Log.e("rootJsonArrayLength",rootJsonArray.length()+"");

                                for (int i = 0; i < rootJsonArray.length(); i++) {
                                    JSONObject obj = rootJsonArray.getJSONObject(i);


                                    String brand=obj.getString("Brand");
                                    String ostype=obj.getString("Operating System Type");
                                    String strge=obj.getString("Storage Capacity");
                                    String ram=obj.getString("Memory RAM");
                                    String sim=obj.getString("Number Of SIM");
                                    String color=obj.getString("Color");
                                    String display=obj.getString("Display Size (Inch)");
                                    String network=obj.getString("Cellular Network Technology");

                                    brandtxt.setText(brand);
                                    ostxt.setText(ostype);
                                    storagetxt.setText(strge);
                                    memorytxt.setText(ram);
                                    simtxt.setText(sim);
                                    colortxt.setText(color);
                                    displaytxt.setText(display);
                                    networktxt.setText(network);
                                }

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

            RequestQueue queue2 = Volley.newRequestQueue(getActivity());
            queue2.add(stringRequest);
        }

//        private void expand() {
//            //set Visible
//            mLinearLayoutDetalis.setVisibility(View.VISIBLE);
//            // mLinearLayoutMore.setVisibility(View.GONE);
//
//
//            final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//            final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//            mLinearLayoutDetalis.measure(widthSpec, heightSpec);
//
//
//            ValueAnimator mAnimator = slideAnimator(0, mLinearLayoutDetalis.getMeasuredHeight());
//            mAnimator.start();
//
//        }
//
//        private void collapse() {
//            int finalHeight = mLinearLayoutDetalis.getHeight();
//
//            ValueAnimator mAnimator = slideAnimator(finalHeight, 0);
//
//            mAnimator.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animator) {
//                    //Height=0, but it set visibility to GONE
//                    mLinearLayoutDetalis.setVisibility(View.GONE);
//
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//
//                }
//
//            });
//            mAnimator.start();
//        }
//
//
//        private ValueAnimator slideAnimator(int start, int end) {
//
//            ValueAnimator animator = ValueAnimator.ofInt(start, end);
//
//            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                    //Update Height
//                    int value = (Integer) valueAnimator.getAnimatedValue();
//                    ViewGroup.LayoutParams layoutParams = mLinearLayoutDetalis.getLayoutParams();
//                    layoutParams.height = value;
//                    mLinearLayoutDetalis.setLayoutParams(layoutParams);
//                }
//            });
//            return animator;
//        }



        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

            //((MyAdapter)recyclerView.getAdapter()).onSaveInstanceState(outState);
        }

        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         */
        public static class SectionsPagerAdapter extends FragmentPagerAdapter {

//            public SectionsPagerAdapter(FragmentManager fm,String priceString) {
//                super(fm);
//                this.priceString = priceString;
//            }

            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);

            }

            @Override
            public Fragment getItem(int position) {

                // getItem is called to instantiate the fragment for the given page.
                // Return a PlaceholderFragment (defined as a static inner class below).
                return PlaceholderFragment.newInstance(position + 1);
            }

            @Override
            public int getCount() {
                // Show 3 total pages.
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Online Store(s)";
                    case 1:
                        return "Description";
                    case 2:
                        return "FAQ";
                    case 3:
                        return "Specs";
                }
                return null;
            }
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        return true;

    }

}
