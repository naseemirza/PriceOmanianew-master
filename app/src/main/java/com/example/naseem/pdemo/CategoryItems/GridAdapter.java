package com.example.naseem.pdemo.CategoryItems;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.naseem.pdemo.CardDetailsPkg.CardDetails;
import com.example.naseem.pdemo.CardDetailsPkg.RecyclerViewItemClickListener;
import com.example.naseem.pdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 23-May-18.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private List<GridModel> mApps;
    private Context mContext;

    public GridAdapter(Context context,List<GridModel> apps){
        mContext=context;
        mApps=apps;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.forgridview, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final GridModel app=mApps.get(position);


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
                .diskCacheStrategy(DiskCacheStrategy.ALL)
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


