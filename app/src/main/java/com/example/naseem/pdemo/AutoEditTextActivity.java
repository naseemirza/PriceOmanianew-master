package com.example.naseem.pdemo;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.naseem.pdemo.CardDetailsPkg.CardDetails;
import com.example.naseem.pdemo.CategoryItems.Categories;
import com.example.naseem.pdemo.MobilePhonesItems.MobilePhones;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;


public class AutoEditTextActivity extends AppCompatActivity {

    ImageView imageview;
    ImageButton imageButton;
    ListView listView;
    ArrayAdapter<String > ItemAdapter = null;
    //ArrayAdapter<String> ItemAdapter1 = null;
    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_edit_text);

        imageview = (ImageView)findViewById(R.id.imageView1);
        imageview.setVisibility(View.GONE);

        imageButton = (ImageButton) findViewById(R.id.bachbtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AutoEditTextActivity.this,MainActivity.class));
            }
        });

        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);


        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                imageview.setVisibility(View.VISIBLE);
                //adapter.getFilter().filter(s.toString());



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        imageview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                autoCompleteTextView.getText().clear();


            }
        });



        ItemAdapter = new
                ArrayAdapter<String>(this,R.layout.hint_completion_layout,R.id.tvHintCompletion, MobilePhones.mobileItems);



        autoCompleteTextView.setAdapter(ItemAdapter);

        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(AutoEditTextActivity.this, CardDetails.class));
            }
        });
    }
}