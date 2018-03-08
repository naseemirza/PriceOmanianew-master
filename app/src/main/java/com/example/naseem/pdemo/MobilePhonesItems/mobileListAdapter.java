package com.example.naseem.pdemo.MobilePhonesItems;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naseem.pdemo.R;

/**
 * Created by Naseem on 16-02-2018.
 */

public class mobileListAdapter extends ArrayAdapter<String> {

    String[] category_itme;

    Context context;

    public mobileListAdapter(Activity context, String[] text){
        super(context, R.layout.list_row, text);
        // TODO Auto-generated constructor stub
        this.category_itme = text;

        this.context = context;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.list_row, null,
                true);

        TextView textView = (TextView) single_row.findViewById(R.id.textView);

        textView.setText(category_itme[position]);




        return single_row;
    }

}
