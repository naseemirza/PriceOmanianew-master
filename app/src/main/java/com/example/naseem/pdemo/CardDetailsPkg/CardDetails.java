package com.example.naseem.pdemo.CardDetailsPkg;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.naseem.pdemo.Options.MoreOptionActivity;
import com.example.naseem.pdemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


import java.util.List;

public class CardDetails extends AppCompatActivity {

    private PlaceholderFragment.SectionsPagerAdapter mSectionsPagerAdapter;

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment  {



        Button buttonSite,buttonSite1;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

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

        LinearLayout mLinearLayoutDetalis;
        LinearLayout mLinearLayoutMore;
        LinearLayout mLinearLayoutLess;

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



                mLinearLayoutLess=(LinearLayout)rootView.findViewById(R.id.linrless);
                mLinearLayoutDetalis = (LinearLayout)rootView.findViewById(R.id.expandable);
                mLinearLayoutDetalis.setVisibility(View.GONE);
                mLinearLayoutMore = (LinearLayout)rootView.findViewById(R.id.linrtextmore);

                mLinearLayoutMore.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mLinearLayoutMore.setVisibility(View.GONE);
                        expand();

                    }
                });
                mLinearLayoutLess.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        mLinearLayoutMore.setVisibility(View.VISIBLE);
                        collapse();

                    }
                });

                TextView optiontext=(TextView)rootView.findViewById(R.id.options);
                optiontext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(),MoreOptionActivity.class));
                    }
                });


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


                CardPager cardPager = new CardPager(this.getActivity());

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


        private void expand() {
            //set Visible
            mLinearLayoutDetalis.setVisibility(View.VISIBLE);
            // mLinearLayoutMore.setVisibility(View.GONE);



            final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            mLinearLayoutDetalis.measure(widthSpec, heightSpec);


            ValueAnimator mAnimator = slideAnimator(0, mLinearLayoutDetalis.getMeasuredHeight());
            mAnimator.start();

        }
        private void collapse() {
            int finalHeight = mLinearLayoutDetalis.getHeight();

            ValueAnimator mAnimator = slideAnimator(finalHeight, 0);

            mAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    //Height=0, but it set visibility to GONE
                    mLinearLayoutDetalis.setVisibility(View.GONE);

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

            });
            mAnimator.start();
        }


        private ValueAnimator slideAnimator(int start, int end) {

            ValueAnimator animator = ValueAnimator.ofInt(start, end);

            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    //Update Height
                    int value = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = mLinearLayoutDetalis.getLayoutParams();
                    layoutParams.height = value;
                    mLinearLayoutDetalis.setLayoutParams(layoutParams);
                }
            });
            return animator;
        }

        @Override
        public void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);

            //((MyAdapter)recyclerView.getAdapter()).onSaveInstanceState(outState);
        }


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
