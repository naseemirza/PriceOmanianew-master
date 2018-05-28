package com.example.naseem.pdemo.CategoryItems;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.naseem.pdemo.R;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {

    GridAdapter adapter;
    GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        getSupportActionBar().setTitle("More Items");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gv= (GridView) findViewById(R.id.gv);

        adapter=new GridAdapter(this,getData());
        gv.setAdapter(adapter);
    }

    private ArrayList getData()
    {
        ArrayList<GridModel> grdm=new ArrayList<>();

        GridModel s=new GridModel();
        s.setName("Apple");
        s.setCurrency("AED");
        s.setPrice("2018");
        s.setTotalcount("20");
        s.setImage(R.drawable.apple);
        grdm.add(s);

        s=new GridModel();
        s.setName("Apple");
        s.setCurrency("AED");
        s.setPrice("2018");
        s.setTotalcount("20");
        s.setImage(R.drawable.apple);
        grdm.add(s);


        s=new GridModel();
        s.setName("Apple");
        s.setCurrency("AED");
        s.setPrice("2018");
        s.setTotalcount("20");
        s.setImage(R.drawable.apple);
        grdm.add(s);

        s=new GridModel();
        s.setName("Apple");
        s.setCurrency("AED");
        s.setPrice("2018");
        s.setTotalcount("20");
        s.setImage(R.drawable.apple);
        grdm.add(s);


        s=new GridModel();
        s.setName("Apple");
        s.setCurrency("AED");
        s.setPrice("2018");
        s.setTotalcount("20");
        s.setImage(R.drawable.apple);
        grdm.add(s);


        s=new GridModel();
        s.setName("Apple");
        s.setCurrency("AED");
        s.setPrice("2018");
        s.setTotalcount("20");
        s.setImage(R.drawable.apple);
        grdm.add(s);


        s=new GridModel();
        s.setName("Apple");
        s.setCurrency("AED");
        s.setPrice("2018");
        s.setTotalcount("20");
        s.setImage(R.drawable.apple);
        grdm.add(s);

        s=new GridModel();
        s.setName("Apple");
        s.setCurrency("AED");
        s.setPrice("2018");
        s.setTotalcount("20");
        s.setImage(R.drawable.apple);
        grdm.add(s);


        s=new GridModel();
        s.setName("Apple");
        s.setCurrency("AED");
        s.setPrice("2018");
        s.setTotalcount("20");
        s.setImage(R.drawable.apple);
        grdm.add(s);

        return grdm;
    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(GridActivity.this, Sub_ChildActivity.class);
//        startActivity(intent);
//        finish();
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
//    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        return true;
    }
}
