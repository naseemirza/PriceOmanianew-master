package com.example.naseem.pdemo.CardDetails;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.naseem.pdemo.R;

public class ColorActivity extends AppCompatActivity {
    private TabLayout tabLayout;


    String Itemlist[] = {
            "All Colors",
            "Black",
            "Blue",
            "Gold",
            "Grey",


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        getSupportActionBar().setTitle("Filters");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Color"));


        ColorAdapter adapter = new ColorAdapter(this, Itemlist);
        ListView lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long arg3) {
                TextView tv=(TextView)findViewById(R.id.textViewcolor);
                String name = arg0.getItemAtPosition(position).toString();
                //String name = tv.getText().toString();
                Intent intent=new Intent(ColorActivity.this,DialogActivity.class);
                intent.putExtra("color",name);
                startActivity(intent);

                LinearLayout item_view = (LinearLayout) view;
                final RadioButton itemcheck = (RadioButton)
                        item_view.findViewById(R.id.rbuttonc);



                if (itemcheck.isChecked()) {
                    itemcheck.setChecked(true);

                } else {
                    itemcheck.setChecked(true);
                }

                itemcheck.setChecked(true);

            }

       });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        return true;
    }

}
