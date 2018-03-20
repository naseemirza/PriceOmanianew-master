package com.example.naseem.pdemo.CardDetailsPkg;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naseem.pdemo.R;

import java.util.List;

/**
 * Created by User on 3/12/2018.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<CardDetailsApp> mApps;
    private boolean mHorizontal;


    private boolean mPager;

    public CardAdapter(boolean horizontal, boolean pager, List<CardDetailsApp> apps) {
        mApps = apps;
        mHorizontal = horizontal;
        mPager = pager;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mPager) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pager, parent, false));

        } else {
            return mHorizontal ? new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.carddetailspager, parent, false)) :
                    new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vertical, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardDetailsApp app=mApps.get(position);
        // holder.imageview.setImageResource(app.getDrawable());
        holder.mobilenameTextview.setText(app.getName());

        holder.priceTextview11.setText(String.valueOf(app.getPrice()));
        holder.onlinestoreTextview2.setText(String.valueOf(app.getSite()));
        holder.imageview.setImageResource(app.getImage());



    }

    @Override
    public int getItemViewType(int position){

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }






    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageview;
        public TextView mobilenameTextview;
        public TextView onlinestoreTextview2;
        public TextView priceTextview11;




        public ViewHolder(View itemView) {

            super(itemView);
            imageview=(ImageView)itemView.findViewById(R.id.imageview);
            mobilenameTextview=(TextView) itemView.findViewById(R.id.Mnametext);
            onlinestoreTextview2=(TextView) itemView.findViewById(R.id.priceTextview1);
            priceTextview11=(TextView) itemView.findViewById(R.id.Mpricetext);




        }

    }

}
