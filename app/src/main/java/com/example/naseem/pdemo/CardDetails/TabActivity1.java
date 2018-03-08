package com.example.naseem.pdemo.CardDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.naseem.pdemo.R;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

public class TabActivity1 extends AppCompatActivity {

    SimpleRatingBar ratingBar;

    //Button buttonSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);



        ratingBar = (SimpleRatingBar) findViewById(R.id.ratingBarID);
//        buttonSite=(Button)findViewById(R.id.buttonsite);
//        buttonSite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(TabActivity1.this,GotoSIte.c));
//
//            }
//        });



    }


}
