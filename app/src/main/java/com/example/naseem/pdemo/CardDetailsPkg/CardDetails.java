package com.example.naseem.pdemo.CardDetailsPkg;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.naseem.pdemo.Adapter;
import com.example.naseem.pdemo.App;
import com.example.naseem.pdemo.AutoEditTextActivity;
import com.example.naseem.pdemo.BottomBar.Activity2;
import com.example.naseem.pdemo.BottomBar.Activity3;
import com.example.naseem.pdemo.BottomBar.Activity4;
import com.example.naseem.pdemo.BottomBar.BottomNavigationViewHelper;
import com.example.naseem.pdemo.CategoryItems.Child;
import com.example.naseem.pdemo.CategoryItems.ChildActivity;
import com.example.naseem.pdemo.CategoryItems.ChildAdapter;
import com.example.naseem.pdemo.CategoryItems.Sub_ChildActivity;
import com.example.naseem.pdemo.MainActivity;
import com.example.naseem.pdemo.MoreSites.CardModel;
import com.example.naseem.pdemo.MoreSites.CustomAdapterSite;
import com.example.naseem.pdemo.Options.MoreOptionActivity;
import com.example.naseem.pdemo.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;

public class CardDetails extends AppCompatActivity {

    private PlaceholderFragment.SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    TextView PrdTextName;
    String prdname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        mSectionsPagerAdapter = new PlaceholderFragment.SectionsPagerAdapter(getSupportFragmentManager());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences pref = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        PrdTextName=(TextView)findViewById(R.id.prdnametext);
        prdname = pref.getString("name", "");
        PrdTextName.setText(prdname);

         getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mSectionsPagerAdapter = new PlaceholderFragment.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
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

//        LinearLayout mLinearLayoutDetalis;
//        LinearLayout mLinearLayoutMore;
//        LinearLayout mLinearLayoutLess;


        String ustring,mname,mcrncy,mprice,mimage;
        String color,strg,netwk;

        // more sites

        private String MORE_SITE_URL="http://ae.priceomania.com/mobileappwebservices/getCompareProductData?pid=";

        private CustomAdapterSite mExampleAdapter;
        private ArrayList<CardModel> mExampleList;
        private RequestQueue mRequestQueue;
        private RecyclerView sRecyclerview;
        String pid;


        //similar products

        private CardAdapter mExampleAdapter1;
        private ArrayList<CardDetailsApp> mExampleList1;
        private RequestQueue mRequestQueue1;
        private RecyclerView mRecyclerview1;


        //product faqs

        ExpandableListView expandableListView;
        ExpandableListAdapter expandableListAdapter;
        List<String> expandableListTitle;
        HashMap<String, List<String>> expandableListDetail;



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


                //similar product

                mExampleList1 = new ArrayList<>();
                mRequestQueue1 = Volley.newRequestQueue(getActivity());

                mRecyclerview1 = (RecyclerView) rootView.findViewById(R.id.recyclerviewsmlr);
                mRecyclerview1.setNestedScrollingEnabled(false);
                mRecyclerview1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                mRecyclerview1.setHasFixedSize(true);

                parseJSON1();

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


                //textViewname = (TextView) rootView.findViewById(R.id.mobilenametext);
                textViewcurncy = (TextView) rootView.findViewById(R.id.crncytype);
                textViewprice = (TextView) rootView.findViewById(R.id.pricetext1);
                imageView = (ImageView) rootView.findViewById(R.id.cardimg);

                SharedPreferences pref = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

                ustring = pref.getString("usermessage", "");
                mname = pref.getString("name", "");
                mcrncy = pref.getString("currency", "");
                mprice = pref.getString("price", "");
                mimage = pref.getString("cardimage", "");

                Glide.with(getActivity())
                        .load(mimage)
                        .fitCenter()
                        .into(imageView);

                // Picasso.with(getActivity()).load(mimage).fit().centerInside().into(imageView);
                //textViewname.setText(mname);
                textViewprice.setText(mprice);
                textViewcurncy.setText(mcrncy);

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

                //replace text with button

//                TextView optiontext = (TextView) rootView.findViewById(R.id.options);
//                optiontext.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        startActivity(new Intent(getActivity(), MoreOptionActivity.class));
//                    }
//                });


//                dialogbutton = (LinearLayout) rootView.findViewById(R.id.optioncard);
//                dialogbutton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        String color=textViewclr.getText().toString();
//                        String storage=textViewstrg.getText().toString();
//                        String network=textViewntwk.getText().toString();
//
//                        SharedPreferences pref = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor edit = pref.edit();
//                        edit.putString("color",color);
//                        edit.putString("storag",storage);
//                        edit.putString("netwk",network);
//
//                        edit.commit();
//                        Intent intent = new Intent(getActivity(), DialogActivity.class);
//
//                        startActivity(intent);
//
//                        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//
//                    }
//                });

                //moresites

                pid = pref.getString("usermessage", "");

                 Log.e("responce",pid);


                mExampleList = new ArrayList<>();
                sRecyclerview = (RecyclerView) rootView.findViewById(R.id.siterecycl);
                sRecyclerview.setNestedScrollingEnabled(false);
                sRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                mRequestQueue = Volley.newRequestQueue(getActivity());
                sRecyclerview.setHasFixedSize(true);

                parseJSON();


                //CardPager cardPager = new CardPager(this.getActivity());


                return rootView;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                View rootView = inflater.inflate(R.layout.activity_tab2, container, false);
                return rootView;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                View rootView = inflater.inflate(R.layout.activity_tab3, container, false);

                expandableListView = (ExpandableListView)rootView.findViewById(R.id.expandableListView);
                expandableListDetail = ExpandableListDataPump.getData();
                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
                expandableListView.setAdapter(expandableListAdapter);
                expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    @Override
                    public void onGroupExpand(int groupPosition) {
//                        Toast.makeText(getActivity().getApplicationContext(),
//                                expandableListTitle.get(groupPosition) + " List Expanded.",
//                                Toast.LENGTH_SHORT).show();
                    }
                });

                expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                    @Override
                    public void onGroupCollapse(int groupPosition) {
//                        Toast.makeText(getActivity().getApplicationContext(),
//                                expandableListTitle.get(groupPosition) + " List Collapsed.",
//                                Toast.LENGTH_SHORT).show();

                    }
                });

                expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                                                int groupPosition, int childPosition, long id) {
//                        Toast.makeText(
//                                getActivity().getApplicationContext(),
//                                expandableListTitle.get(groupPosition)
//                                        + " -> "
//                                        + expandableListDetail.get(
//                                        expandableListTitle.get(groupPosition)).get(
//                                        childPosition), Toast.LENGTH_SHORT
//                        ).show();
                        return false;
                    }
                });
                return rootView;





            } else {

                View rootView = inflater.inflate(R.layout.fragment_card_details, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
            }


        }


        //sites name in details page

        private void parseJSON() {

            StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://ae.priceomania.com/mobileappwebservices/getCompareProductData?pid="+pid,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.e("responce",response );


                            try {

                                JSONObject rootJsonObject = new JSONObject(response);

                                JSONArray subCategoryArray = rootJsonObject.getJSONArray("websitedata");
                                //Log.e("subCategoryArray", subCategoryArray.length() + "");

                                for (int i = 0; i < subCategoryArray.length(); i++) {
                                    JSONObject object = subCategoryArray.getJSONObject(i);

                                    mExampleList.add(new CardModel(object.optString("id"),
                                            object.optString("product_name"),
                                            object.optString("website_name"),
                                            object.optString("website_logo"),
                                            object.optString("currency_type"),
                                            object.optString("price"),
                                            object.optString("product_url")));
                                }

                                Log.e("rootJsonArray", String.valueOf(mExampleList));

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

            StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://ae.priceomania.com/mobileappwebservices/getfeaturedproducts",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                Log.e("rootJsonArray",response);
                                JSONArray rootJsonArray = new JSONArray(response);

                                Log.e("rootJsonArrayLength",rootJsonArray.length()+"");

                                for (int i = 0; i < rootJsonArray.length(); i++) {
                                    JSONObject object = rootJsonArray.getJSONObject(i);

                                    mExampleList1.add(new CardDetailsApp(object.optString("id"),
                                            object.optString("product_image"),
                                            object.optString("modelno"),
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
//


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
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "38 Online Store(s)";
                    case 1:
                        return "Description";
                    case 2:
                        return "FAQ";


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
