package com.example.naseem.pdemo.Options;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.naseem.pdemo.CardDetailsPkg.GotoSIte;
import com.example.naseem.pdemo.R;

public class MoreOptionActivity extends AppCompatActivity {

    Button buttongtosote;
    LinearLayout mLinearLayoutDetalis,mLinearLayoutDetalis1,mLinearLayoutDetalis2;
    LinearLayout mLinearLayoutMore,mLinearLayoutMore1,mLinearLayoutMore2;
    LinearLayout mLinearLayoutLess,mLinearLayoutLess1,mLinearLayoutLess2;

    Button button,button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_option);


        getSupportActionBar().setTitle("Store Options");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //1st cardview

        button=(Button) findViewById(R.id.buttonsite1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoreOptionActivity.this,GotoSIte.class));
            }
        });

        mLinearLayoutLess=(LinearLayout)findViewById(R.id.linrless);
        mLinearLayoutDetalis = (LinearLayout) findViewById(R.id.expandable);
        mLinearLayoutDetalis.setVisibility(View.GONE);

        mLinearLayoutMore = (LinearLayout) findViewById(R.id.linrtextmore);

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


        //2nd cardview

        button1=(Button) findViewById(R.id.buttonsite11);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoreOptionActivity.this,GotoSIte.class));
            }
        });


        mLinearLayoutLess1=(LinearLayout)findViewById(R.id.linrless1);
        mLinearLayoutDetalis1 = (LinearLayout) findViewById(R.id.expandable1);
        mLinearLayoutDetalis1.setVisibility(View.GONE);

        mLinearLayoutMore1 = (LinearLayout) findViewById(R.id.linrtextmore1);

        mLinearLayoutMore1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLinearLayoutMore1.setVisibility(View.GONE);
                expand1();

            }
        });
        mLinearLayoutLess1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                mLinearLayoutMore1.setVisibility(View.VISIBLE);
                collapse1();

            }
        });


        //3rd cardview

        button2=(Button) findViewById(R.id.buttonsite111);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoreOptionActivity.this,GotoSIte.class));
            }
        });

        mLinearLayoutLess2=(LinearLayout)findViewById(R.id.linrless11);
        mLinearLayoutDetalis2 = (LinearLayout) findViewById(R.id.expandable11);
        mLinearLayoutDetalis2.setVisibility(View.GONE);

        mLinearLayoutMore2 = (LinearLayout) findViewById(R.id.linrtextmore11);

        mLinearLayoutMore2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLinearLayoutMore2.setVisibility(View.GONE);
                expand2();

            }
        });
        mLinearLayoutLess2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                mLinearLayoutMore2.setVisibility(View.VISIBLE);
                collapse2();

            }
        });





    }



    //1st cardview

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


    //2nd cardview

    private void expand1() {
        //set Visible
        mLinearLayoutDetalis1.setVisibility(View.VISIBLE);
        // mLinearLayoutMore.setVisibility(View.GONE);



        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mLinearLayoutDetalis1.measure(widthSpec, heightSpec);


        ValueAnimator mAnimator = slideAnimator1(0, mLinearLayoutDetalis1.getMeasuredHeight());
        mAnimator.start();

    }
    private void collapse1() {
        int finalHeight = mLinearLayoutDetalis1.getHeight();

        ValueAnimator mAnimator = slideAnimator1(finalHeight, 0);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                mLinearLayoutDetalis1.setVisibility(View.GONE);

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


    private ValueAnimator slideAnimator1(int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mLinearLayoutDetalis1.getLayoutParams();
                layoutParams.height = value;
                mLinearLayoutDetalis1.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }


    //3rd cardview

    private void expand2() {
        //set Visible
        mLinearLayoutDetalis2.setVisibility(View.VISIBLE);
        // mLinearLayoutMore.setVisibility(View.GONE);



        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mLinearLayoutDetalis2.measure(widthSpec, heightSpec);


        ValueAnimator mAnimator = slideAnimator2(0, mLinearLayoutDetalis2.getMeasuredHeight());
        mAnimator.start();

    }
    private void collapse2() {
        int finalHeight = mLinearLayoutDetalis2.getHeight();

        ValueAnimator mAnimator = slideAnimator2(finalHeight, 0);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                mLinearLayoutDetalis2.setVisibility(View.GONE);

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


    private ValueAnimator slideAnimator2(int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mLinearLayoutDetalis2.getLayoutParams();
                layoutParams.height = value;
                mLinearLayoutDetalis2.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
