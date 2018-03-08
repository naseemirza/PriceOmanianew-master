package com.example.naseem.pdemo.CategoryItems;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.naseem.pdemo.Computers;
import com.example.naseem.pdemo.MobilePhonesItems.MobilePhones;
import com.example.naseem.pdemo.R;

public class Categories extends AppCompatActivity {

    String categoriesItems[] = {
            "Mobile Phones (23026)", "Computers/Laptops (34838)", "Tablets (1437)", "Cameras (15610)",
            "TV Video (2700)", "Audio (1525)", "Video Games (1499)", "Kids & Babies (27359)",
            "Men Fashion (13164)", "Eyewear (11946)", "Watches (15670)", "Women Fashion (44105)",
            "Home Appliances (3149)", "Home & Furniture (30479)", "Office Supplies (3043)", "Sports & Fitness (13383)",

    };

    Integer image_id[] = {
            R.drawable.mobile_phones, R.drawable.computers, R.drawable.tablets, R.drawable.cameras,
            R.drawable.tv_video, R.drawable.audio, R.drawable.video_games, R.drawable.kids_babies,
            R.drawable.men_fashion, R.drawable.eyewear, R.drawable.watches, R.drawable.women_fashion,
            R.drawable.home_appliances, R.drawable.home_furniture, R.drawable.office_supplies, R.drawable.sports_fitness,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CategoryListAdapter adapter = new CategoryListAdapter(this, image_id, categoriesItems);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), MobilePhones.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 1) {
                    Intent myIntent = new Intent(view.getContext(), Computers.class);
                    startActivityForResult(myIntent, 0);
                }

//                if (position == 2) {
//                    Intent myIntent = new Intent(view.getContext(), ListItemActivity1.class);
//                    startActivityForResult(myIntent, 0);
//                }
//
//                if (position == 3) {
//                    Intent myIntent = new Intent(view.getContext(), ListItemActivity2.class);
//                    startActivityForResult(myIntent, 0);
//                }
//
//                if (position == 4) {
//                    Intent myIntent = new Intent(view.getContext(), ListItemActivity1.class);
//                    startActivityForResult(myIntent, 0);
//                }
//
//                if (position == 5) {
//                    Intent myIntent = new Intent(view.getContext(), ListItemActivity2.class);
//                    startActivityForResult(myIntent, 0);
//                }
//
//                if (position == 6) {
//                    Intent myIntent = new Intent(view.getContext(), ListItemActivity1.class);
//                    startActivityForResult(myIntent, 0);
//                }
//
//                if (position == 7) {
//                    Intent myIntent = new Intent(view.getContext(), ListItemActivity2.class);
//                    startActivityForResult(myIntent, 0);
//                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
