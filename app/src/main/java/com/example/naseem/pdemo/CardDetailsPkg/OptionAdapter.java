package com.example.naseem.pdemo.CardDetailsPkg;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.naseem.pdemo.R;

/**
 * Created by User on 3/9/2018.
 */

public class OptionAdapter extends ArrayAdapter<String> {

    String[] category_itme;
    String[] prce;
    Context context;

    public OptionAdapter(Activity context, String[] item, String[] price){
        super(context, R.layout.seealloption, item);
        // TODO Auto-generated constructor stub
        this.category_itme = item;
       this.prce=price;
        this.context = context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.seealloption, null,
                true);

        TextView textView = (TextView) single_row.findViewById(R.id.textViewname);
        TextView textView1 = (TextView) single_row.findViewById(R.id.textViewprc);

        textView.setText(category_itme[position]);
        textView1.setText(prce[position]);


        return single_row;

    }
}

