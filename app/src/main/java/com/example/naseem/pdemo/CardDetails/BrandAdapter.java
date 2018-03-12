package com.example.naseem.pdemo.CardDetails;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.naseem.pdemo.R;

/**
 * Created by User on 3/12/2018.
 */

public class BrandAdapter extends ArrayAdapter<String> {

    String[] colorname;
    Context context;

    public BrandAdapter(Activity context, String[] item){
        super(context, R.layout.brandlist, item);
        // TODO Auto-generated constructor stub
        this.colorname = item;

        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.brandlist, null,
                true);

        TextView textView = (TextView) single_row.findViewById(R.id.textViewbrand);
        RadioButton radioButton=(RadioButton)single_row.findViewById(R.id.rbuttonn);


        textView.setText(colorname[position]);



        return single_row;

    }
}
