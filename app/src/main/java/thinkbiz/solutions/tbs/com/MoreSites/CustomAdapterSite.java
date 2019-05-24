package thinkbiz.solutions.tbs.com.MoreSites;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import thinkbiz.solutions.tbs.com.CardDetailsPkg.RecyclerViewItemClickListener;
import thinkbiz.solutions.tbs.com.MoreOptionsPkg.OptionsActivity;
import thinkbiz.solutions.tbs.com.R;


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

        View v = LayoutInflater.from(mContext).inflate(R.layout.sitelayoutwthoption, parent, false);
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

//        Log.e("url",productName);
//        Log.e("url",currencyType);
//        Log.e("url",totalPrice);
//        Log.e("url",sitename);
//        Log.e("url",siteurl);

        holder.mTextViewName.setText(productName);
        holder.mTextViewCurrency.setText(currencyType);
        holder.mTextViewPrice.setText(totalPrice);
        holder.mTextViewSitename.setText(sitename);

        Glide.with(mContext)
                .load(imageUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mImageViewsite);

        holder.mLinearLayoutMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mLinearLayoutMore.setVisibility(View.GONE);
                holder.mLinearLayoutDetalis.setVisibility(View.VISIBLE);
                // ((OptionsActivity)mContext).expand();
                // expand();
            }
        });

        holder.mLinearLayoutLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mLinearLayoutDetalis.setVisibility(View.GONE);
                holder.mLinearLayoutMore.setVisibility(View.VISIBLE);
            }
        });

        holder.moreoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, OptionsActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        holder.buttonSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {

                String siteurl=app.getSiteurl();
                Log.e("url",siteurl);

                Intent browserIntent = new Intent( Intent.ACTION_VIEW, Uri.parse((siteurl)));
                v.getContext().startActivity(browserIntent);
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

                                                        //implements View.OnClickListener
    public class ViewHolder extends RecyclerView.ViewHolder  {

        public ImageView mImageViewsite;
        public TextView mTextViewName;
        public TextView mTextViewCurrency;
        public TextView mTextViewSitename;
        public TextView mTextViewPrice;
        public Button buttonSite;
        TextView moreoption;

        LinearLayout mLinearLayoutDetalis;
        LinearLayout mLinearLayoutMore;
        LinearLayout mLinearLayoutLess;


       // private RecyclerViewItemClickListener itemClickListener;

        public ViewHolder(View itemView ) {

            super(itemView);
            mImageViewsite=(ImageView)itemView.findViewById(R.id.imageview);
            mTextViewName=(TextView) itemView.findViewById(R.id.prodname);
            mTextViewCurrency=(TextView) itemView.findViewById(R.id.crncytext);
            mTextViewPrice=(TextView) itemView.findViewById(R.id.pricetext);
            mTextViewSitename=(TextView) itemView.findViewById(R.id.namecmp);
            buttonSite=(Button) itemView.findViewById(R.id.buttonsite1);
            moreoption=(TextView) itemView.findViewById(R.id.moreeoption);

            mLinearLayoutLess=(LinearLayout)itemView.findViewById(R.id.linrless);
            mLinearLayoutDetalis = (LinearLayout)itemView.findViewById(R.id.expandable);
            mLinearLayoutMore = (LinearLayout) itemView.findViewById(R.id.linrtextmore);

           // itemView.setOnClickListener(this);


        }

//        @Override
//        public void onClick(View v) {
//            this.itemClickListener.onClick(v,getLayoutPosition());
//        }
//
//        public void setItemClickListener(RecyclerViewItemClickListener ic)
//        {
//            this.itemClickListener=ic;
//
//        }
    }


}

