package thinkbiz.solutions.tbs.com.MoreOptionsPkg;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import thinkbiz.solutions.tbs.com.CardDetailsPkg.RecyclerViewItemClickListener;
import thinkbiz.solutions.tbs.com.MoreSites.CardModel;
import thinkbiz.solutions.tbs.com.MoreSites.CustomAdapterSite;
import thinkbiz.solutions.tbs.com.R;

/**
 * Created by User on 21-May-19.
 */

public class OpAdapter extends RecyclerView.Adapter<OpAdapter.ViewHolder> {

    private List<OpModel> mApps;
    private Context mContext;

    public OpAdapter(Context context, List<OpModel> apps){
        mContext=context;
        mApps=apps;
    }

    @Override
    public OpAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.comparesitelist, parent, false);
        return new OpAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final OpAdapter.ViewHolder holder, int position) {
        final OpModel app=mApps.get(position);


        String productName = app.getMobileName();
        String currencyType = app.getMobileCurrency();
        String totalPrice = app.getMobilePrice();
        String sitename = app.getSiteName();
        //final String siteurl=app.getSiteurl();

        holder.mTextViewName.setText(productName);
        holder.mTextViewCurrency.setText(currencyType);
        holder.mTextViewPrice.setText(totalPrice);
        holder.mTextViewSitename.setText(sitename);


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

//        holder.buttonSite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v ) {
//
//                String siteurl=app.getSiteurl();
//
//                Intent browserIntent = new Intent( Intent.ACTION_VIEW, Uri.parse((siteurl)));
//                v.getContext().startActivity(browserIntent);
//            }
//        });
//
//
//        Glide.with(mContext)
//                .load(imageUrl)
//                .fitCenter()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.mImageViewsite);

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
        TextView moreinfotxt;
        public Button buttonSite;

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
            moreinfotxt=(TextView) itemView.findViewById(R.id.texviewtmore);
            buttonSite=(Button) itemView.findViewById(R.id.buttonsite1);

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