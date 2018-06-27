package com.example.naseem.pdemo.MoreSites;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.naseem.pdemo.CardDetailsPkg.RecyclerViewItemClickListener;
import com.example.naseem.pdemo.R;


import java.util.List;

/**
 * Created by User on 23-Apr-18.
 */

public class CustomAdapterSite extends RecyclerView.Adapter<CustomAdapterSite.ViewHolder> {

    private List<CardModel> mApps;

    private Context mContext;

    public CustomAdapterSite(Context context, List<CardModel> apps){
        mContext=context;
        mApps=apps;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.sitelist, parent, false);
        return new ViewHolder(v);


    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CardModel app=mApps.get(position);


        String imageUrl = app.getSitelogoUrl();
        String productName = app.getMobileName();
        String currencyType = app.getMobileCurrency();
        String totalPrice = app.getMobilePrice();
        String sitename = app.getSiteName();
        final String siteurl=app.getSiteurl();

        holder.mTextViewName.setText(productName);
        holder.mTextViewCurrency.setText(currencyType);
        holder.mTextViewPrice.setText(totalPrice);
        holder.mTextViewSitename.setText(sitename);
        holder.buttonSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {

                String siteurl=app.getSiteurl();

                Intent browserIntent = new Intent( Intent.ACTION_VIEW, Uri.parse((siteurl)));
                v.getContext().startActivity(browserIntent);
            }
        });


        Glide.with(mContext)
                .load(imageUrl)
                .fitCenter()
                .into(holder.mImageViewsite);

    }


    @Override
    public int getItemViewType(int position){

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mImageViewsite;
        public TextView mTextViewName;
        public TextView mTextViewCurrency;
        public TextView mTextViewSitename;
        public TextView mTextViewPrice;
        public Button buttonSite;



        private RecyclerViewItemClickListener itemClickListener;


        public ViewHolder(View itemView ) {

            super(itemView);
            mImageViewsite=(ImageView)itemView.findViewById(R.id.imageview);
            mTextViewName=(TextView) itemView.findViewById(R.id.mobilename);
            mTextViewCurrency=(TextView) itemView.findViewById(R.id.crncytext);
            mTextViewPrice=(TextView) itemView.findViewById(R.id.pricetext);
            mTextViewSitename=(TextView) itemView.findViewById(R.id.siteTextview);
            buttonSite=(Button) itemView.findViewById(R.id.buttonsite1);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onClick(v,getLayoutPosition());
        }

        public void setItemClickListener(RecyclerViewItemClickListener ic)
        {
            this.itemClickListener=ic;

        }
    }


}

