package com.example.naseem.pdemo.MobilePhonesItems;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.naseem.pdemo.R;

public class MobilePhones extends AppCompatActivity {


    String mobileItems[] = {
            "Samsun (705)", "Appel (175)", "Blackberry (82)", "Sony (251)",
            "LG (206)", "HTC (238)", "Microsoft (22)","Lenovo (177)", "Motorola (95)",
            "Huawei (375)", "Nokia (400)", "Xiaomi (89)", "Alcatel (103)",
            "Asus (86)", "Lava (60)", "Google (12)", "Mobile Accessories (19533)",

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_phones);
        getSupportActionBar().setTitle("Mobile Phones");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mobileListAdapter adapter = new mobileListAdapter(this, mobileItems);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

    }
}
