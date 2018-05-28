package com.example.naseem.pdemo.CategoryItems;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naseem.pdemo.CardDetailsPkg.CardDetails;
import com.example.naseem.pdemo.R;

import java.util.ArrayList;

/**
 * Created by User on 23-May-18.
 */

public class GridAdapter extends BaseAdapter {

    Context c;
    ArrayList<GridModel> gridmodel;

    public GridAdapter(Context c, ArrayList<GridModel> gridmodel) {
        this.c = c;
        this.gridmodel = gridmodel;
    }

    @Override
    public int getCount() {
        return gridmodel.size();
    }

    @Override
    public Object getItem(int i) {
        return gridmodel.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.forgridview,viewGroup,false);
        }

        final GridModel s= (GridModel) this.getItem(i);

        ImageView img= (ImageView) view.findViewById(R.id.imageview);
        TextView nameTxt= (TextView) view.findViewById(R.id.nameTextview);
        TextView curncy= (TextView) view.findViewById(R.id.crncytype);
        TextView price= (TextView) view.findViewById(R.id.pricetext1);
        TextView tcount= (TextView) view.findViewById(R.id.countext);

        //BIND
        nameTxt.setText(s.getName());
        curncy.setText(s.getCurrency());
        price.setText(s.getPrice());
        tcount.setText(s.getTotalcount()+" Online Store(s)");

        img.setImageResource(s.getImage());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), CardDetails.class));
            }
        });

        return view;
    }
}

