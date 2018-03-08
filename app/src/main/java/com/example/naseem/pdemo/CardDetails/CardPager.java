package com.example.naseem.pdemo.CardDetails;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.naseem.pdemo.R;

/**
 * Created by User on 3/6/2018.
 */

public class CardPager extends PagerAdapter {


    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private Context context;
    private Integer [] images={R.drawable.appleiphone6, R.drawable.apple,
            R.drawable.apple7plus,R.drawable.appleiphone6,R.drawable.applemini,R.drawable.applimini11,R.drawable.apple,R.drawable.appleiphone6};
    private LayoutInflater layoutInflater;
    public CardPager(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==  object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.cardimage, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewCard);
        imageView.setImageResource(images[position]);


        ViewPager vp=(ViewPager) container;
        vp.addView(view,0);
        return view;


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp=(ViewPager) container;
        View view=(View) object;
        vp.removeView(view);
    }
}
