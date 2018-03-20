package com.example.naseem.pdemo.CardDetailsPkg;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.naseem.pdemo.Adapter;
import com.example.naseem.pdemo.R;

public class DialogActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    private CardView cardView1,cardView2,cardView3;
    private Button buttonaply;
    Adapter mAdapter;
    RelativeLayout relativeLayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);


        getSupportActionBar().setTitle("Filters");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Options"));

//        buttonaply=(Button)findViewById(R.id.buttonapply);
//        buttonaply.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//
//
//
//              Intent intent=new Intent(DialogActivity.this,CardDetails.class);
//
//
//
//              startActivityForResult(intent,0);
//
//              overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
//
//          }
//      });



        TextView textView=(TextView)findViewById(R.id.subtextcolor);
        TextView textView1=(TextView)findViewById(R.id.storagetext);
        TextView textView2=(TextView)findViewById(R.id.brandtextview);



       final Intent iin=getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            final String cname = (String) b.get("color");
            textView.setText(cname);
            final String strgname = (String) b.get("storage");
            textView1.setText(strgname);
            final String brndname = (String) b.get("brand");
            textView2.setText(brndname);

        }


        relativeLayout=(RelativeLayout)findViewById(R.id.rel1) ;


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DialogActivity.this,SeeAllOptions.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        cardView1=(CardView)findViewById(R.id.card1);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DialogActivity.this,ColorActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        cardView2=(CardView)findViewById(R.id.card2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DialogActivity.this,StorageActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        cardView3=(CardView)findViewById(R.id.card3);
        cardView3.setOnClickListener(new View.OnClickListener() {
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
