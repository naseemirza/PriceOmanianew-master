package com.example.naseem.pdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Computers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computers);

        getSupportActionBar().setTitle("Computers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
