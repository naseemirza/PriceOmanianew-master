package com.example.naseem.pdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.naseem.pdemo.CardDetailsPkg.CardDetails;
import com.example.naseem.pdemo.CardDetailsPkg.RecyclerViewItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Naseem on 07-02-2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {
    private List<App> mApps;
    private boolean mHorizontal;
    private boolean mPager;
    private Context mContext;

//    ImageLoader imageLoader;
//
//    Context c;

    //,boolean horizontal,boolean pager, after context
    public Adapter(Context context,List<App> apps){
        mContext=context;
        mApps=apps;
//        mHorizontal=horizontal;
//        mPager=pager;

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final App app=mApps.get(position);


        String imageUrl = app.getmImageUrl();
        String productName = app.getmName();
        String currencyType = app.getmCurrency();
        String totalPrice = app.getmPrice();
        String totalCount = app.getmCount();

        holder.mTextViewName.setText(productName);
        holder.mTextViewCurrency.setText(currencyType);
        holder.mTextViewPrice.setText(totalPrice);
        holder.mTextViewCount.setText(totalCount+" Online Store(s)");
        Glide.with(mContext)
                .load(imageUrl)
                .fitCenter()
                .into(holder.mImageView);


        //Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);


        holder.setItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                String prd_id=app.getmID().toString();
                String prd_name=app.getmName().toString();
                String prd_image=app.getmImageUrl().toString();
                String prd_crny=app.getmCurrency().toString();
                String prd_price=app.getmPrice().toString();

                Log.e("responce",prd_id);

                SharedPreferences pref = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();

                edit.putString("usermessage",prd_id);
                edit.putString("price",prd_price);
                edit.putString("currency",prd_crny);
                edit.putString("name",prd_name);
                edit.putString("cardimage",prd_image);


                edit.commit();
                Intent intent = new Intent(view.getContext(), CardDetails.class);
                view.getContext().startActivity(intent);


            }
        });


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

        public ImageView mImageView;
        public TextView mTextViewName;
        public TextView mTextViewCurrency;
        public TextView mTextViewCount;
        public TextView mTextViewPrice;

        private RecyclerViewItemClickListener itemClickListener;


        public ViewHolder(View itemView ) {

            super(itemView);
            mImageView=(ImageView)itemView.findViewById(R.id.imageview);
            mTextViewName=(TextView) itemView.findViewById(R.id.nameTextview);
            mTextViewCurrency=(TextView) itemView.findViewById(R.id.crncytype);
            mTextViewPrice=(TextView) itemView.findViewById(R.id.pricetext1);
            mTextViewCount=(TextView) itemView.findViewById(R.id.countext);

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
