package com.example.naseem.pdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naseem.pdemo.CardDetails.CardDetails;
import com.example.naseem.pdemo.CardDetails.RecyclerViewItemClickListener;

import java.util.List;

/**
 * Created by Naseem on 07-02-2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {
    private List<App> mApps;
    private boolean mHorizontal;



    private boolean mPager;

    public Adapter(boolean horizontal,boolean pager,List<App> apps){
        mApps=apps;
        mHorizontal=horizontal;
        mPager=pager;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mPager){
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pager,parent,false));

        }else {
            return mHorizontal ? new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter,parent,false)):
            new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vertical,parent,false));
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final App app=mApps.get(position);
       // holder.imageview.setImageResource(app.getDrawable());
        holder.nameTextview.setText(app.getName());
        holder.onlinestoreTextview2.setText(String.valueOf(app.getSite()));
        holder.priceTextview1.setText(String.valueOf(app.getPrice()));
        holder.imageview.setImageResource(app.getImage());

        holder.setItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Intent i=new Intent(view.getContext(),CardDetails.class);
                //TextView mobilename,mobileprice;
                //ImageView imageView;
               // mobileprice=(TextView)view.findViewById(R.id.onlinestoreTextview2);
               // mobilename=(TextView)view.findViewById(R.id.nameTextview);
                //imageView=(ImageView)view.findViewById(R.id.imageview);

                //String s=mobileprice.getText().toString();
                //String s1=mobilename.getText().toString();
                Intent intent = new Intent(view.getContext(), CardDetails.class);
                intent.putExtra("price",app.getPrice());
                intent.putExtra("name",app.getName());
                intent.putExtra("cardimage",app.getImage());
                view.getContext().startActivity(intent);
            }
        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView mobilename,mobileprice;
//                ImageView imageView;
//                mobileprice=(TextView)v.findViewById(R.id.onlinestoreTextview2);
//                mobilename=(TextView)v.findViewById(R.id.nameTextview);
//                imageView=(ImageView)v.findViewById(R.id.imageview);
//
//                String s=mobileprice.getText().toString();
//                String s1=mobilename.getText().toString();
//                Intent intent = new Intent(v.getContext(), CardDetails.class);
//                intent.putExtra("price",s);
//                intent.putExtra("name",s1);
//                //intent.putExtra("cardimage",images);
//                v.getContext().startActivity(intent);
//
//            }
//        });





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

        public ImageView imageview;
        public TextView nameTextview;
        public TextView onlinestoreTextview2;
        public TextView priceTextview1;
        private RecyclerViewItemClickListener itemClickListener;



        public ViewHolder(View itemView) {

            super(itemView);
            imageview=(ImageView)itemView.findViewById(R.id.imageview);
            nameTextview=(TextView) itemView.findViewById(R.id.nameTextview);
            onlinestoreTextview2=(TextView) itemView.findViewById(R.id.onlinestoreTextview2);
            priceTextview1=(TextView) itemView.findViewById(R.id.priceTextview1);
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
