package com.example.naseem.pdemo.CategoryItems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.naseem.pdemo.R;

import java.util.List;

/**
 * Created by User on 3/30/2018.
 */

public class ChildAdapter extends ArrayAdapter<Child> {
    private List<Child> childList;
    private Context mCtx;

    public ChildAdapter(List<Child> childList, Context mCtx) {
        super(mCtx, R.layout.child_items, childList);
        this.childList = childList;
        this.mCtx = mCtx;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.child_items, null, true);
        TextView textViewname = (TextView)listViewItem.findViewById(R.id.textViewname);


        Child child = childList.get(position);

        textViewname.setText(child.getName());

        return listViewItem;
    }




}
