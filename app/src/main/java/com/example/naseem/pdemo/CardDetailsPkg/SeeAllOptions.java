package com.example.naseem.pdemo.CardDetailsPkg;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.naseem.pdemo.R;

public class SeeAllOptions extends AppCompatActivity {
    private TabLayout tabLayout;

    String Itemlist[] = {
                "Black,64 GB,3G",
                "Black,64 GB,LTE",
                "Blue,64 GB,LTE",
                "Gold,64 GB,LTE",
                "Grey,64 GB,LTE",
                "Grey,64 GB,3G",
                "Gold,64 GB,3G",
                "Blue,64 GB,3G",

    };

    String Pricelist[] = {
                "Rs.16599",
                "Rs.39999",
                "Rs.44679",
                "Rs.44679",
                "Rs.44679",
                "Rs.48900",
                "Rs.53900",
                "Rs.66990",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_options);

        getSupportActionBar().setTitle("Filters");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("All Options"));


        OptionAdapter adapter = new OptionAdapter(this, Itemlist, Pricelist);
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);

       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               startActivity(new Intent(SeeAllOptions.this,CardDetails.class));
           }
       });

   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        return true;
    }
}
