package com.example.naseem.pdemo.CardDetailsPkg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.TabLayout;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naseem.pdemo.Models.Phone;
import com.example.naseem.pdemo.Models.PhoneCategory;
import com.example.naseem.pdemo.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;


import java.util.List;

import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class CardDetails extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private PlaceholderFragment.SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    TextView textViewpri;
    private Button btnSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

//        btnSite=(Button)findViewById(R.id.buttonsite);
//        btnSite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CardDetails.this, GotoSIte.class));
//            }
//        });

        mSectionsPagerAdapter = new PlaceholderFragment.SectionsPagerAdapter(getSupportFragmentManager());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Card Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mSectionsPagerAdapter = new PlaceholderFragment.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabTextColors(R.color.colorBlack,R.color.colorBlack);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_details, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment  {



        Button buttonSite,buttonSite1;

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        private RecyclerView mRecyclerview;
        private RecyclerView recyclerView;
        //LinearLayout sliderDotspanel;
        private int dotscount;
        CardView  dialogbutton;
        String image_url;
        private ImageView[] dots;
        //ViewPager viewPager;
        public  TextView textViewname ,textViewcurncy,textViewprice ,textViewcount,textViewcolor;
       public  ImageView imageView;

        String name;
        String price;
        int image;

        //int images[] = {R.drawable.appleiphone6, R.drawable.apple,
                //R.drawable.apple7plus,R.drawable.appleiphone6,R.drawable.applemini,R.drawable.applimini11,R.drawable.apple,R.drawable.appleiphone6};
        ImageAdapter imageAdapter;





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



                ExpandableLayout layout=(ExpandableLayout)rootView.findViewById(R.id.el);
                layout.setRenderer(new ExpandableLayout.Renderer<PhoneCategory, Phone>(){

                                                    @Override
                                                    public void renderParent(View view, PhoneCategory model, boolean b, int i) {
                                                        ((TextView) view.findViewById(R.id.texviewtmore)).setText(model.name);
                                                    }

                                                    @Override
                                                    public void renderChild(View view, Phone model, int i, int i1) {
                                                        ((TextView) view.findViewById(R.id.tvChild)).setText(model.name);

                                                    }
                                                });

                layout.addSection(getSection());
//                layout.addSection(getSection());
//                layout.addSection(getSection());
//                layout.addSection(getSection());
//                layout.addSection(getSection());
//                layout.addSection(getSection());








                //sliderDotspanel = (LinearLayout) rootView.findViewById(R.id.SliderDots);
                textViewcolor=(TextView)rootView.findViewById(R.id.mcolor) ;

                textViewname=(TextView)rootView.findViewById(R.id.mobilenametext) ;
                textViewcurncy=(TextView)rootView.findViewById(R.id.crncytype) ;
                textViewprice=(TextView)rootView.findViewById(R.id.pricetext1);
                //textViewcount=(TextView)rootView.findViewById(R.id.mobilenametext);

               imageView=(ImageView)rootView.findViewById(R.id.cardimg);

                final Intent i=getActivity().getIntent();

                Bundle bd = i.getExtras();



                if(bd != null)
                {
                    image_url= String.valueOf(i.getStringExtra("cardimage"));

                    Picasso.with(getActivity()).load(image_url).fit().centerInside().into(imageView);

                        final String getname = (String) bd.get("name");
                        textViewname.setText(getname);
                        final String getprice = (String) bd.get("price");
                        textViewprice.setText(getprice);
                        final String getcurncy = (String) bd.get("currency");
                        textViewcurncy.setText(getcurncy);

                }

                dialogbutton=(CardView) rootView.findViewById(R.id.optioncard);
                dialogbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(), DialogActivity.class);

                        startActivity(intent);


                       //startActivity(new Intent(getActivity(), DialogActivity.class));

                        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    }
                });




                buttonSite=(Button)rootView.findViewById(R.id.buttonsite);
                buttonSite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(),GotoSIte.class));

                    }
                });


                mRecyclerview=(RecyclerView)rootView.findViewById(R.id.recyclerview);
                mRecyclerview.setNestedScrollingEnabled(false);





                mRecyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity()));
                mRecyclerview.setHasFixedSize(true);
                setupAdapter();


                //viewPager = (ViewPager)rootView.findViewById(R.id.viewpager);


                //imageAdapter = new ImageAdapter(this.getActivity(), images);
               // viewPager.setAdapter(imageAdapter);
                //sliderDotspanel = (LinearLayout) rootView.findViewById(R.id.SliderDots);

                CardPager cardPager = new CardPager(this.getActivity());
                //viewPager.setAdapter(cardPager);

                //dotscount = cardPager.getCount();
                //dots = new ImageView[dotscount];

//                for(int i = 0; i < dotscount; i++){
//
//                    dots[i] = new ImageView(this.getActivity());
//                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.non_active_dot));
//
//                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//                    params.setMargins(8, 0, 8, 0);
//
//                    sliderDotspanel.addView(dots[i], params);
//
//                }

                //dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));

//                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                    @Override
//                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                    }
//
//                    @Override
//                    public void onPageSelected(int position) {
//
//                        for(int i = 0; i< dotscount; i++){
//                            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.non_active_dot));
//                        }
//
//                        dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));
//
//                    }
//
//                    @Override
//                    public void onPageScrollStateChanged(int state) {
//
//                    }
//                });
                //Timer timer=new Timer();
                //timer.scheduleAtFixedRate(new MyTimerTask(),2000,3000);


                return rootView;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                View rootView = inflater.inflate(R.layout.activity_tab2, container, false);
                return rootView;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                View rootView = inflater.inflate(R.layout.activity_tab3, container, false);
                return rootView;
            } else {

                View rootView = inflater.inflate(R.layout.fragment_card_details, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
            }



        }

        private Section<PhoneCategory,Phone> getSection(){
            Section<PhoneCategory,Phone> section=new Section<>();
            PhoneCategory phoneCategory=new PhoneCategory("MoreItem+");
            List<Phone> listname=new ArrayList<>();
            for (int i=1;i<=1;i++)
                listname.add(new Phone("Delivery "));
                //listname.add(new Phone("Notes "));

                section.parent=phoneCategory;
                section.children.addAll(listname);
                return section;


        }


        @Override
        public void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);

            //((MyAdapter)recyclerView.getAdapter()).onSaveInstanceState(outState);
        }



//        public class MyTimerTask extends TimerTask {
//            @Override
//            public void run() {
//
//                if(getActivity() == null)
//                    return;
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (viewPager.getCurrentItem()==0){
//                            viewPager.setCurrentItem(1);
//                        }
//                        else if(viewPager.getCurrentItem()==1){
//                            viewPager.setCurrentItem(2);
//                        }
//                        else if(viewPager.getCurrentItem()==2){
//                            viewPager.setCurrentItem(3);
//                        }
//                        else {
//                            viewPager.setCurrentItem(0);
//                        }
//                    }
//                });
//            }
//        }

        private void setupAdapter(){

            List<CardDetailsApp> apps=getApps();

            CardSnapAdapter snapAdapter=new CardSnapAdapter();

            snapAdapter.addSnap(new CardSnap(Gravity.START,"Similar Products",apps));

            //if(mHorizontal){
            // snapAdapter.addSnap(new Snap(Gravity.START,"FEATURED PRODUCTS",apps));
            // snapAdapter.addSnap(new Snap(Gravity.START,"APPLE IPHONES",apps1));

            //snapAdapter.addSnap(new Snap(Gravity.END,"",appd));
//            snapAdapter.addSnap(new Snap(Gravity.END,"CAMERAS",apps));
//            snapAdapter.addSnap(new Snap(Gravity.CENTER,"TABLETS",apps1));
//
//        }else {
//
//            snapAdapter.addSnap(new Snap(Gravity.CENTER_VERTICAL,"Apple Products",apps));
//            snapAdapter.addSnap(new Snap(Gravity.TOP,"Apple Products",apps));
//            snapAdapter.addSnap(new Snap(Gravity.BOTTOM,"Apple Products",apps));
//        }
            mRecyclerview.setAdapter(snapAdapter);


        }

        private List<CardDetailsApp> getApps(){
            List<CardDetailsApp> apps=new ArrayList<>();

            apps.add(new CardDetailsApp("Apple iPhone 5 plus", "AED 21990.00", "38 Online Store(s)", R.drawable.apple7plus));
            apps.add(new CardDetailsApp("Apple iPhone ", "AED 18190.00", "38 Online Store(s)", R.drawable.apple));
            apps.add(new CardDetailsApp("Apple iPhone 7 plus", "AED 1125.60", "38 Online Store(s)", R.drawable.microlumia));
            apps.add(new CardDetailsApp("Apple iPhone ", "AED 2199.00", "38 Online Store(s)", R.drawable.apple7plus));
            apps.add(new CardDetailsApp("Apple iPhone 7 plus", "AED 18190.00", "38 Online Store(s)", R.drawable.apple7plus));
            apps.add(new CardDetailsApp("Apple iPhone ", "AED 1125.60", "38 Online Store(s)", R.drawable.microlumia));
            apps.add(new CardDetailsApp("Apple iPhone 7 plus", "AED 2199.00", "38 Online Store(s)", R.drawable.apple7plus));
            apps.add(new CardDetailsApp("Apple iPhone ", "AED 18190.00", "38 Online Store(s)", R.drawable.applemini));
            apps.add(new CardDetailsApp("Apple iPhone 7 plus", "AED 1125.60", "38 Online Store(s)", R.drawable.microlumia));
            apps.add(new CardDetailsApp("Apple iPhone 7 plus", "AED 2199.00", "38 Online Store(s)", R.drawable.apple7plus));




            return apps;
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
                return 6;
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
                    case 3:
                        return "Compare Prices";
                    case 4:
                        return "Description";
                    case 5:
                        return "FAQ";
                }
                return null;
            }
        }


    }



}
