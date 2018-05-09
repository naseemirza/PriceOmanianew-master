package com.example.naseem.pdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import com.example.naseem.pdemo.BottomBar.Activity1;
import com.example.naseem.pdemo.BottomBar.Activity2;
import com.example.naseem.pdemo.BottomBar.Activity3;
import com.example.naseem.pdemo.BottomBar.Activity4;
import com.example.naseem.pdemo.BottomBar.BottomNavigationViewHelper;
import com.example.naseem.pdemo.CategoryItems.ParentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Adapter mExampleAdapter1,mExampleAdapter2,mExampleAdapter3,mExampleAdapter4;
    private ArrayList<App> mExampleList1,mExampleList2,mExampleList3,mExampleList4;
    private RequestQueue mRequestQueue1,mRequestQueue2,mRequestQueue3,mRequestQueue4;

    private RecyclerView mRecyclerview1;
    private RecyclerView mRecyclerview2;
    private RecyclerView mRecyclerview3;
    private RecyclerView mRecyclerview4;

    private EditText editText;
    public static String BACK_STACK_TAG = "tag";

    public static final String ORIENTATION = "orientation";
    private Boolean mHorizontal;
    public TextView textViewprice,textviewname;

    //private List<App> mApps;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {
            Toast.makeText(MainActivity.this,"", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);
        }


        //bottombar


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:

                        break;

                    case R.id.ic_android:
                        Intent intent1 = new Intent(MainActivity.this, AutoEditTextActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_books:
                        Intent intent2 = new Intent(MainActivity.this, Activity2.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_center_focus:
                        Intent intent3 = new Intent(MainActivity.this, Activity3.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_backup:
                        Intent intent4 = new Intent(MainActivity.this,Activity4.class);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });


        //Snap 1

        mExampleList1 = new ArrayList<>();
        mRequestQueue1 = Volley.newRequestQueue(this);

        mRecyclerview1=(RecyclerView)findViewById(R.id.recyclerview1);
        mRecyclerview1.setNestedScrollingEnabled(false);
        mRecyclerview1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mRecyclerview1.setHasFixedSize(true);

        parseJSON1();

        //Snap 2

        mExampleList2 = new ArrayList<>();
        mRequestQueue2= Volley.newRequestQueue(this);

        mRecyclerview2=(RecyclerView)findViewById(R.id.recyclerview2);
        mRecyclerview2.setNestedScrollingEnabled(false);
        mRecyclerview2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mRecyclerview2.setHasFixedSize(true);

        parseJSON2();

        //Snap 3

        mExampleList3 = new ArrayList<>();
        mRequestQueue3= Volley.newRequestQueue(this);

        mRecyclerview3=(RecyclerView)findViewById(R.id.recyclerview3);
        mRecyclerview3.setNestedScrollingEnabled(false);
        mRecyclerview3.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mRecyclerview3.setHasFixedSize(true);

        parseJSON3();


        //Snap 4

        mExampleList4 = new ArrayList<>();
        mRequestQueue4= Volley.newRequestQueue(this);

        mRecyclerview4=(RecyclerView)findViewById(R.id.recyclerview4);
        mRecyclerview4.setNestedScrollingEnabled(false);
        mRecyclerview4.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mRecyclerview4.setHasFixedSize(true);

       // parseJSON4();

       // AutoCompleteTextView

        editText=(EditText)findViewById(R.id.etext);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AutoEditTextActivity.class);
                startActivity(intent);
            }
        });

        textViewprice=(TextView)findViewById(R.id.onlinestoreTextview2);
        textviewname=(TextView)findViewById(R.id.nameTextview);

//        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
//        mRecyclerview.setNestedScrollingEnabled(false);
//
//
//        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
//
//        mRecyclerview.setAdapter(mAdapter);
//        mRecyclerview.setHasFixedSize(true);

//  mRecyclerview.addOnItemTouchListener(new CustomRVItemTouchListener(this, mRecyclerview, new RecyclerViewItemClickListener() {
//      @Override
//      public void onClick(View view, int position) {
//
//          startActivity(new Intent(MainActivity.this,CardDetails.class));
//      }
//  }));

//        if (savedInstanceState == null) {
//            mHorizontal = true;
//
//        } else {
//            mHorizontal = savedInstanceState.getBoolean(ORIENTATION);
//        }
       // setupAdapter();


        //viewpager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        MyCustomPagerAdapter myCustomPagerAdapter = new MyCustomPagerAdapter(this);
        viewPager.setAdapter(myCustomPagerAdapter);

        dotscount = myCustomPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 3000);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }


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

                                mExampleList1.add(new App(object.optString("product_image"),
                                        object.optString("modelno"),
                                        object.optString("currency_type"),
                                        object.optString("price"),
                                        object.optString("store_count")));
                            }

                            Log.e("rootJsonArray",mExampleList1.size()+"");

                            mExampleAdapter1 = new Adapter(MainActivity.this, mExampleList1);
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("TAg",error.getMessage());
                    }
                });

        mRequestQueue1 = Volley.newRequestQueue(this);
        mRequestQueue1.add(stringRequest);
    }

    private void parseJSON2() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://ae.priceomania.com/mobileappwebservices/getMobiles",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("rootJsonArray",response);
                            JSONArray rootJsonArray = new JSONArray(response);

                            Log.e("rootJsonArrayLength",rootJsonArray.length()+"");

                            for (int i = 0; i < rootJsonArray.length(); i++) {
                                JSONObject object = rootJsonArray.getJSONObject(i);

                                mExampleList2.add(new App(object.optString("product_image"),
                                        object.optString("modelno"),
                                        object.optString("currency_type"),
                                        object.optString("price"),
                                        object.optString("store_count")));
                            }

                            Log.e("rootJsonArray",mExampleList2.size()+"");

                            mExampleAdapter2 = new Adapter(MainActivity.this, mExampleList2);
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("TAg",error.getMessage());
                    }
                });

        mRequestQueue2 = Volley.newRequestQueue(this);
        mRequestQueue2.add(stringRequest);
    }

    private void parseJSON3() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://ae.priceomania.com/mobileappwebservices/getCamera",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("rootJsonArray",response);
                            JSONArray rootJsonArray = new JSONArray(response);

                            Log.e("rootJsonArrayLength",rootJsonArray.length()+"");

                            for (int i = 0; i < rootJsonArray.length(); i++) {
                                JSONObject object = rootJsonArray.getJSONObject(i);

                                mExampleList3.add(new App(object.optString("product_image"),
                                        object.optString("modelno"),
                                        object.optString("currency_type"),
                                        object.optString("price"),
                                        object.optString("store_count")));
                            }

                            Log.e("rootJsonArray",mExampleList3.size()+"");

                            mExampleAdapter3 = new Adapter(MainActivity.this, mExampleList3);
                            mRecyclerview3.setAdapter(mExampleAdapter3);
                            mExampleAdapter3.notifyDataSetChanged();
                            mRecyclerview3.setHasFixedSize(true);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("TAg",error.getMessage());
                    }
                });

        mRequestQueue3 = Volley.newRequestQueue(this);
        mRequestQueue3.add(stringRequest);
    }


//    private void parseJSON4() {
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://ae.priceomania.com/mobileappwebservices/getTablets",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            Log.e("rootJsonArray",response);
//                            JSONArray rootJsonArray = new JSONArray(response);
//
//                            Log.e("rootJsonArrayLength",rootJsonArray.length()+"");
//
//                            for (int i = 0; i < rootJsonArray.length(); i++) {
//                                JSONObject object = rootJsonArray.getJSONObject(i);
//
//                                mExampleList4.add(new App(object.optString("product_image"),
//                                        object.optString("modelno"),
//                                        object.optString("currency_type"),
//                                        object.optString("price"),
//                                        object.optString("store_count")));
//                            }
//
//                            Log.e("rootJsonArray",mExampleList4.size()+"");
//
//                            mExampleAdapter4 = new Adapter(MainActivity.this, mExampleList4);
//                            mRecyclerview4.setAdapter(mExampleAdapter4);
//                            mExampleAdapter4.notifyDataSetChanged();
//                            mRecyclerview4.setHasFixedSize(true);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
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
//        mRequestQueue4 = Volley.newRequestQueue(this);
//        mRequestQueue4.add(stringRequest);
//    }



//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putBoolean(ORIENTATION, mHorizontal);
//    }

//    private void setupAdapter() {
//        List<App> apps = getApps();
//        List<App> apps1 = getApps1();
//        List<App> apps2 = getApps2();
//        List<App> apps3 = getApps3();
//
//
//        SnapAdapter snapAdapter = new SnapAdapter();
//        if (mHorizontal) {
//            snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "FEATURED PRODUCTS", apps));
//            // snapAdapter.addSnap(new Snap(Gravity.START,"APPLE IPHONES",apps));
//            snapAdapter.addSnap(new Snap(Gravity.START, "APPLE IPHONES", apps1));
//            snapAdapter.addSnap(new Snap(Gravity.END, "CAMERAS", apps2));
//            snapAdapter.addSnap(new Snap(Gravity.END, "TABLETS", apps3));
//
//            //snapAdapter.addSnap(new Snap(Gravity.CENTER,"Apple Products",apps));
//        } else {
//
//            snapAdapter.addSnap(new Snap(Gravity.CENTER_VERTICAL, "Apple Products", apps));
//            snapAdapter.addSnap(new Snap(Gravity.TOP, "Apple Products", apps));
//            snapAdapter.addSnap(new Snap(Gravity.BOTTOM, "Apple Products", apps));
//        }
//        mRecyclerview.setAdapter(snapAdapter);
//
//    }


//    private List<App> getApps() {
//        List<App> apps = new ArrayList<>();
//
//        apps.add(new App("Apple iPhone 7 plus", "AED 2199.00", "38 Online Store(s)", R.drawable.apple7plus));
//        apps.add(new App("Apple iPhone 7 plus", "AED 18190.00", "38 Online Store(s)", R.drawable.canon));
//        apps.add(new App("Apple iPhone 7 plus", "AED 1125.60", "38 Online Store(s)", R.drawable.microlumia));
//        apps.add(new App("Apple iPhone 7 plus", "AED 2199.00", "38 Online Store(s)", R.drawable.apple7plus));
//        apps.add(new App("Apple iPhone 7 plus", "AED 18190.00", "38 Online Store(s)", R.drawable.canon));
//        apps.add(new App("Apple iPhone 7 plus", "AED 1125.60", "38 Online Store(s)", R.drawable.microlumia));
//        apps.add(new App("Apple iPhone 7 plus", "AED 2199.00", "38 Online Store(s)", R.drawable.apple7plus));
//        apps.add(new App("Apple iPhone 7 plus", "AED 18190.00", "38 Online Store(s)", R.drawable.canon));
//        apps.add(new App("Apple iPhone 7 plus", "AED 1125.60", "38 Online Store(s)", R.drawable.microlumia));
//        apps.add(new App("Apple iPhone 7 plus", "AED 2199.00", "38 Online Store(s)", R.drawable.apple7plus));
//
//        return apps;
//    }


//    private List<App> getApps1() {
//        List<App> apps1 = new ArrayList<>();
//
//        apps1.add(new App("Apple IPhone X", "AED 2199.00", "10 Online Store(s)", R.drawable.appleiphone));
//        apps1.add(new App("iPhone 7 Plus-32 Gb,Gold", "AED 34229.00", "16 Online Store(s)", R.drawable.newmobile));
//        apps1.add(new App("Apple iPhone 6,32 GB", "AED 5110.60", "18 Online Store(s)", R.drawable.appleiphone6));
//        apps1.add(new App("Apple IPhone X", "AED 2199.00", "10 Online Store(s)", R.drawable.appleiphone));
//        apps1.add(new App("iPhone 7 Plus-32 Gb,Gold", "AED 34229.00", "16 Online Store(s)", R.drawable.newmobile));
//        apps1.add(new App("Apple iPhone 6,32 GB", "AED 5110.60", "18 Online Store(s)", R.drawable.appleiphone6));
//        apps1.add(new App("Apple IPhone X", "AED 2199.00", "10 Online Store(s)", R.drawable.appleiphone));
//        apps1.add(new App("iPhone 7 Plus-32 Gb,Gold", "AED 34229.00", "16 Online Store(s)", R.drawable.newmobile));
//        apps1.add(new App("Apple iPhone 6,32 GB", "AED 5110.60", "18 Online Store(s)", R.drawable.appleiphone6));
//
//        return apps1;
//    }
//
//
//    private List<App> getApps2() {
//        List<App> apps2 = new ArrayList<>();
//
//        apps2.add(new App("Canon EOS 6D ", "AED 6089.00", "4 Online Store(s)", R.drawable.canon1));
//        apps2.add(new App("Canon EOS 800D", "AED 34229.00", "4 Online Store(s)", R.drawable.camera));
//        apps2.add(new App("Canon GIX ll", "AED 1795.60", "4 Online Store(s)", R.drawable.canon));
//        apps2.add(new App("Canon EOS 6D ", "AED 6089.00", "4 Online Store(s)", R.drawable.canon1));
//        apps2.add(new App("Canon EOS 800D", "AED 34229.00", "4 Online Store(s)", R.drawable.camera));
//        apps2.add(new App("Canon GIX ll", "AED 1795.60", "4 Online Store(s)", R.drawable.canon));
//        apps2.add(new App("Canon EOS 6D ", "AED 6089.00", "4 Online Store(s)", R.drawable.canon1));
//        apps2.add(new App("Canon EOS 800D", "AED 34229.00", "4 Online Store(s)", R.drawable.camera));
//        apps2.add(new App("Canon GIX ll", "AED 1795.60", "4 Online Store(s)", R.drawable.canon));
//
//        return apps2;
//    }
//
//
//    private List<App> getApps3() {
//        List<App> apps3 = new ArrayList<>();
//
//        apps3.add(new App("Apple Ipad Mini 3", "AED 1060.00", "15 Online Store(s)", R.drawable.applemini1));
//        apps3.add(new App("Apple iPad Mini 3 Tablet-7.9", "AED 1049.00", "17 Online Store(s)", R.drawable.applimini11));
//        apps3.add(new App("Apple iPad Mini 2", "AED 1179.60", "22 Online Store(s)", R.drawable.applemini));
//        apps3.add(new App("Canon EOS 6D ", "AED 6089.00", "4 Online Store(s)", R.drawable.canon1));
//        apps3.add(new App("Apple Ipad Mini 3", "AED 1060.00", "15 Online Store(s)", R.drawable.applemini1));
//        apps3.add(new App("Apple iPad Mini 3 Tablet-7.9", "AED 1049.00", "17 Online Store(s)", R.drawable.applimini11));
//        apps3.add(new App("Apple iPad Mini 2", "AED 1179.60", "22 Online Store(s)", R.drawable.applemini));
//        apps3.add(new App("Apple Ipad Mini 3", "AED 1060.00", "15 Online Store(s)", R.drawable.applemini1));
//        apps3.add(new App("Apple iPad Mini 3 Tablet-7.9", "AED 1049.00", "17 Online Store(s)", R.drawable.applimini11));
//        apps3.add(new App("Apple iPad Mini 2", "AED 1179.60", "22 Online Store(s)", R.drawable.applemini));
//
//        return apps3;
//    }
//



    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }
                    else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }
                    else if(viewPager.getCurrentItem()==2){
                        viewPager.setCurrentItem(3);
                    }
                    else {
                        viewPager.setCurrentItem(0);
                    }


                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_login) {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));


            // Handle the camera action
        } else if (id == R.id.nav_wallet) {

        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_myad) {

        } else if (id == R.id.nav_category) {
            startActivity(new Intent(MainActivity.this,ParentActivity.class));




        }else if (id == R.id.nav_offer) {

        }else if (id == R.id.nav_store) {

        }else if (id == R.id.nav_country) {

        }else if (id == R.id.nav_language) {

        }else if (id == R.id.nav_rate) {

        }
        else if (id == R.id.nav_feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
