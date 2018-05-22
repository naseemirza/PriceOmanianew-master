package com.example.naseem.pdemo.CardDetailsPkg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.naseem.pdemo.Adapter;
import com.example.naseem.pdemo.R;

public class DialogActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    private CardView cardView1,cardView2,cardView3;
    private  TextView textViewc,textViews,textViewn;
    private Button buttonaply;
    Adapter mAdapter;
    RelativeLayout relativeLayout;
    String color,strg,netwk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        textViewc=(TextView)findViewById(R.id.subtextcolor);
        textViews=(TextView)findViewById(R.id.storagetext);
        textViewn=(TextView)findViewById(R.id.brandtextview);

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        color=pref.getString("color","");
        strg=pref.getString("storag","");
        netwk=pref.getString("netwk","");


        textViewc.setText(color);
        textViews.setText(strg);
        textViewn.setText(netwk);


        getSupportActionBar().setTitle("Filters");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Options"));

//        final Intent i = getIntent();
//        Bundle bd = i.getExtras();
//
//        if (bd != null) {
//
//
//            final String subCategoryId = (String) bd.get("category_id");
//
//            Log.e("subId", subCategoryId);
//
//
//        }


        textViewc=(TextView)findViewById(R.id.subtextcolor);
        textViews=(TextView)findViewById(R.id.storagetext);
        textViewn=(TextView)findViewById(R.id.brandtextview);

        buttonaply=(Button)findViewById(R.id.buttonok);
        buttonaply.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              String color=textViewc.getText().toString();
              String storage=textViews.getText().toString();
              String network=textViewn.getText().toString();

//              SharedPreferences pref = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//              SharedPreferences.Editor edit = pref.edit();
//
//              edit.putString("color",color);
//              edit.putString("storag",storage);
//              edit.putString("netwk",network);
//
//              edit.commit();


              Intent intent=new Intent(DialogActivity.this,CardDetails.class);

              intent.putExtra("color",color);
              intent.putExtra("storage",storage);
              intent.putExtra("network",network);
              startActivity(intent);

              overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);


          }
      });



        //relativeLayout=(RelativeLayout)findViewById(R.id.rel1) ;


//        relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(DialogActivity.this,SeeAllOptions.class));
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });

        cardView1=(CardView)findViewById(R.id.card1);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DialogActivity.this,ColorActivity.class);
                startActivityForResult(intent, 2);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        cardView2=(CardView)findViewById(R.id.card2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DialogActivity.this,StorageActivity.class);
                startActivityForResult(intent, 3);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        cardView3=(CardView)findViewById(R.id.card3);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(DialogActivity.this,NetworkActivity.class);
                startActivityForResult(intent, 4);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {

//            SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//            String message=pref.getString("color","");
//            textViewc.setText(message);
            String message=data.getStringExtra("color");
            textViewc.setText(message);
        }
        if(requestCode==3)
        {
//            SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//            String message1=pref.getString("storag","");
//            textViews.setText(message1);

            String message1=data.getStringExtra("storage");
            textViews.setText(message1);
        }
        if(requestCode==4)
        {

//            SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//            String message2=pref.getString("netwk","");
//            textViewn.setText(message2);

            String message2=data.getStringExtra("n/w");
            textViewn.setText(message2);
        }
    }




    @Override
    public boolean onSupportNavigateUp(){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        return true;
    }

}
