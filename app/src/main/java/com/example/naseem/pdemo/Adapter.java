package com.example.naseem.pdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naseem.pdemo.CardDetails.CardDetails;

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
        App app=mApps.get(position);
       // holder.imageview.setImageResource(app.getDrawable());
        holder.nameTextview.setText(app.getName());
        holder.onlinestoreTextview2.setText(String.valueOf(app.getSite()));
        holder.priceTextview1.setText(String.valueOf(app.getPrice()));
        holder.imageview.setImageResource(app.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView mobilename,mobileprice;
                mobileprice=(TextView)v.findViewById(R.id.onlinestoreTextview2);
                mobilename=(TextView)v.findViewById(R.id.nameTextview);
                String s=mobileprice.getText().toString();
                String s1=mobilename.getText().toString();
                Intent intent = new Intent(v.getContext(), CardDetails.class);
                intent.putExtra("price",s);
                intent.putExtra("name",s1);
                v.getContext().startActivity(intent);

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




    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageview;
        public TextView nameTextview;
        public TextView onlinestoreTextview2;
        public TextView priceTextview1;




        public ViewHolder(View itemView) {

            super(itemView);
            imageview=(ImageView)itemView.findViewById(R.id.imageview);
            nameTextview=(TextView) itemView.findViewById(R.id.nameTextview);
            onlinestoreTextview2=(TextView) itemView.findViewById(R.id.onlinestoreTextview2);
            priceTextview1=(TextView) itemView.findViewById(R.id.priceTextview1);




//        itemView.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View v) {
//
//             TextView mobilename,mobileprice;
//
//             mobileprice=(TextView)v.findViewById(R.id.onlinestoreTextview2);
//             mobilename=(TextView)v.findViewById(R.id.nameTextview);
//               String s=mobileprice.getText().toString();
//               String s1=mobilename.getText().toString();
//             Intent intent = new Intent(v.getContext(), CardDetails.class);
//             intent.putExtra("price",s);
//                intent.putExtra("name",s1);
//             v.getContext().startActivity(intent);
//
//
//           }
//        });



        }

//        @Override
//        public void onClick(View v) {
//            TextView mobilename,mobileprice;
//            mobileprice=(TextView)v.findViewById(R.id.onlinestoreTextview2);
//            mobilename=(TextView)v.findViewById(R.id.nameTextview);
//            String s=mobileprice.getText().toString();
//            String s1=mobilename.getText().toString();
//            Intent intent = new Intent(v.getContext(), CardDetails.class);
//            intent.putExtra("price",s);
//            intent.putExtra("name",s1);
//            v.getContext().startActivity(intent);
//
//        }
    }

}
