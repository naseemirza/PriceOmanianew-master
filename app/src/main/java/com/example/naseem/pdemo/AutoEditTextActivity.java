package com.example.naseem.pdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.naseem.pdemo.CategoryItems.Categories;
import com.example.naseem.pdemo.MobilePhonesItems.MobilePhones;

public class AutoEditTextActivity extends AppCompatActivity {


    ImageButton imageButton;
    AutoCompleteTextView autoCompleteTextView;
   // String[] language ={"C","C++","Java",".NET","iPhone","Android","ASP.NET","PHP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_edit_text);

        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1) ;
        ListView listView1 = (ListView) findViewById(R.id.listView1);


//        ArrayAdapter<String > adapter = new ArrayAdapter<String>
//                (this,android.R.layout.select_dialog_item, Categories.categoriesItems);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, MobilePhones.mobileItems);


        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
        //autoCompleteTextView.setAdapter(adapter1);





        imageButton=(ImageButton)findViewById(R.id.bachbtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AutoEditTextActivity.this,MainActivity.class));
            }
        });

    }


}
