package thinkbiz.solutions.tbs.com;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import thinkbiz.solutions.tbs.com.CardDetailsPkg.CardDetails;
import thinkbiz.solutions.tbs.com.CardDetailsPkg.RecyclerViewItemClickListener;
import thinkbiz.solutions.tbs.com.DetailPage.DetailPageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 04-Jul-18.
 */

public class AutoTextAdapter  extends RecyclerView.Adapter<AutoTextAdapter.ViewHolder> {


    private List<AutoTextModel> mApps;
    private Context mContext;

    public AutoTextAdapter(Context context,List<AutoTextModel> apps){
        mContext=context;
        mApps=apps;
    }


    @Override
    public AutoTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.autocompletetext, parent, false);
        return new AutoTextAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AutoTextAdapter.ViewHolder holder, int position) {
        final AutoTextModel app=mApps.get(position);


        String imageUrl = app.getmImageUrl();
        String productName = app.getmName();
        String currencyType = app.getmCurrency();
        String totalPrice = app.getmPrice();
        String totalCount = app.getmCount();

        holder.mTextViewName.setText(productName);
//        holder.mTextViewCurrency.setText(currencyType);
//        holder.mTextViewPrice.setText(totalPrice);
//        holder.mTextViewCount.setText(totalCount+" Online Store(s)");
//        Glide.with(mContext)
//                .load(imageUrl)
//                .fitCenter()
//                .into(holder.mImageView);


        holder.setItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                String prd_id=app.getmID();
                String prd_name=app.getmName();
                String prd_image=app.getmImageUrl();
                String prd_crny=app.getmCurrency();
                String prd_price=app.getmPrice();
                String slug=app.getSlug();
                String slug_suffix=app.getSlug_suffix();
                Log.e("responce",prd_id);

                SharedPreferences pref = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();

                edit.putString("usermessage",prd_id);
                edit.putString("price",prd_price);
                edit.putString("currency",prd_crny);
                edit.putString("name",prd_name);
                edit.putString("cardimage",prd_image);
                edit.putString("slug",slug);
                edit.putString("slug_suffix",slug_suffix);

                edit.apply();                                 //CardDetails
                Intent intent = new Intent(view.getContext(), DetailPageActivity.class);
                view.getContext().startActivity(intent);


            }
        });
    }

    public void filterList(ArrayList<AutoTextModel> filterdNames) {
        this.mApps = filterdNames;
        notifyDataSetChanged();
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

       // public ImageView mImageView;
        public TextView mTextViewName;
       // public TextView mTextViewCurrency;
       // public TextView mTextViewCount;
       // public TextView mTextViewPrice;

        private RecyclerViewItemClickListener itemClickListener;


        public ViewHolder(View itemView ) {

            super(itemView);
            //mImageView=(ImageView)itemView.findViewById(R.id.imageview);
            mTextViewName=(TextView) itemView.findViewById(R.id.textView);
            //mTextViewCurrency=(TextView) itemView.findViewById(R.id.crncytype);
            //mTextViewPrice=(TextView) itemView.findViewById(R.id.pricetext1);
            //mTextViewCount=(TextView) itemView.findViewById(R.id.countext);

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

