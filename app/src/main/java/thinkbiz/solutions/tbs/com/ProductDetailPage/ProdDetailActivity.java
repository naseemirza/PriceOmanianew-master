package thinkbiz.solutions.tbs.com.ProductDetailPage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import thinkbiz.solutions.tbs.com.CardDetailsPkg.CardDetails;
import thinkbiz.solutions.tbs.com.ProductDetailPage.Tabs.Pager;
import thinkbiz.solutions.tbs.com.R;

public class ProdDetailActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    String pid,mname,mcrncy,mprice,mimage;
   // private ProdDetailActivity.PlaceholderFragment.SectionsPagerAdapter mSectionsPagerAdapter;
    public TextView textViewname, textViewcurncy, textViewprice;
    public ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_detail);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        mname = pref.getString("name", "");
        pid = pref.getString("usermessage", "");
        mcrncy = pref.getString("currency", "");
        mprice = pref.getString("price", "");
        mimage = pref.getString("cardimage", "");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Online stores(1)"));
        tabLayout.addTab(tabLayout.newTab().setText("Description"));
        tabLayout.addTab(tabLayout.newTab().setText("FAQ"));
        tabLayout.addTab(tabLayout.newTab().setText("Specs"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.container);
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(this);



    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
