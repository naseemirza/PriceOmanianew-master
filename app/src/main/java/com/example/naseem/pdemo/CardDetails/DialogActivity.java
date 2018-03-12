package com.example.naseem.pdemo.CardDetails;

import android.content.Intent;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.naseem.pdemo.R;

public class DialogActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    RelativeLayout relativeLayout;
    CardView cardViewopt;
    Button buttonaply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        buttonaply=(Button)findViewById(R.id.buttonapply);
        buttonaply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TextView tv=(TextView)findViewById(R.id.textViewcolor);
//                TextView tv1=(TextView)findViewById(R.id.textViewstorage);
//                TextView tv2=(TextView)findViewById(R.id.textViewbrand);
                //TextView textView1=(TextView)findViewById(R.id.storagetext);
                //TextView textView2=(TextView)findViewById(R.id.textViewbrand);

//                String color = tv.getText().toString();
//                String storage = tv1.getText().toString();
//                String brand = tv2.getText().toString();
                Intent intent=new Intent(DialogActivity.this,CardDetails.class);
//                intent.putExtra("color",color);
//                intent.putExtra("storage",storage);
//                intent.putExtra("brand",brand);
                 startActivity(intent);



            }
        });

        TextView textView=(TextView)findViewById(R.id.subtextcolor);
        TextView textView1=(TextView)findViewById(R.id.storagetext);
        TextView textView2=(TextView)findViewById(R.id.textViewbrand);



        Intent iin=getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("color");

            String j1 =(String) b.get("storage");
            String j2 =(String) b.get("brand");

            textView.setText(j);

            textView1.setText(j1);
            textView2.setText(j2);


        }

        getSupportActionBar().setTitle("Filters");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Options"));

        cardViewopt=(CardView)findViewById(R.id.card2);
        cardViewopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DialogActivity.this,SeeAllOptions.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        cardViewopt=(CardView)findViewById(R.id.card3);
        cardViewopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DialogActivity.this,ColorActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        cardViewopt=(CardView)findViewById(R.id.card4);
        cardViewopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DialogActivity.this,StorageActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        cardViewopt=(CardView)findViewById(R.id.card5);
        cardViewopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DialogActivity.this,BrandActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        //TabItem tabItem=(TabItem)findViewById(R.id.tabitm);



        //relativeLayout=(RelativeLayout)findViewById(R.id.re2);
//        relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(DialogActivity.this,AllOptions.class));
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });






    }



    @Override
    public boolean onSupportNavigateUp(){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        return true;
    }

}
