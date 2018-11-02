package thinkbiz.solutions.tbs.com;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import thinkbiz.solutions.tbs.com.CardDetailsPkg.CardDetails;
import thinkbiz.solutions.tbs.com.CardDetailsPkg.RecyclerViewItemClickListener;
import thinkbiz.solutions.tbs.com.DetailPage.DetailPageActivity;

import java.util.List;

/**
 * Created by Naseem on 07-02-2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {
    private List<App> mApps;
    private Context mContext;

    public Adapter(Context context,List<App> apps){
        mContext=context;
        mApps=apps;
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
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(holder.mImageView);

        holder.setItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                //String url= "https://ae.priceomania.com/hybridproductdetailapp/";

                String prd_id=app.getmID();
                String prd_name=app.getmName();
                String prd_image=app.getmImageUrl();
                String prd_crny=app.getmCurrency();
                String prd_price=app.getmPrice();
                String prd_count=app.getmCount();
                String slug=app.getSlug();
                String slug_suffix=app.getSlug_suffix();

//                Log.e("responce",slug);
//                Log.e("responce",slug_suffix);

                SharedPreferences pref = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();

                edit.putString("usermessage",prd_id);
                edit.putString("price",prd_price);
                edit.putString("currency",prd_crny);
                edit.putString("name",prd_name);
                edit.putString("cardimage",prd_image);
                edit.putString("totalcount",prd_count);
                edit.putString("slug",slug);
                edit.putString("slug_suffix",slug_suffix);


                edit.apply();                                 // CardDetails

                Intent intent = new Intent(view.getContext(), DetailPageActivity.class);
                view.getContext().startActivity(intent);
                //                Uri uriUrl = Uri.parse(url+slug+slug_suffix);
//                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
//                view.getContext().startActivity(launchBrowser);


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
