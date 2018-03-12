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

public class StorageActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    String Itemlist[] = {
            "32 GB",
            "64 GB",
            "128 GB",
            "256 GB",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        getSupportActionBar().setTitle("Filters");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Storage"));

        StorageAdapter adapter = new StorageAdapter(this, Itemlist);
        ListView lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long arg3) {
                TextView tv=(TextView)findViewById(R.id.textViewstorage);
                String name1 = arg0.getItemAtPosition(position).toString();
                //String name1 = tv.getText().toString();
                Intent intent=new Intent(StorageActivity.this,DialogActivity.class);
                intent.putExtra("storage",name1);
                startActivity(intent);

                LinearLayout item_view = (LinearLayout) view;
                final RadioButton itemcheck = (RadioButton)
                        item_view.findViewById(R.id.rbuttons);



                if (itemcheck.isChecked()) {
                    itemcheck.setChecked(true);

                } else {
                    itemcheck.setChecked(true);
                }

                itemcheck.setChecked(true);

            }

        });
    }
}
