package thinkbiz.solutions.tbs.com.ProductDetailPage.Tabs.OnlineStoresTab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import thinkbiz.solutions.tbs.com.R;

/**
 * Created by User on 25-Feb-19.
 */

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<SiteModel> productList;

    public SiteAdapter(Context mCtx, List<SiteModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }
    @Override
    public SiteAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.mobsitelist, null);
        return new SiteAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SiteAdapter.ProductViewHolder holder, int position) {

        SiteModel product = productList.get(position);

        holder.textViewComname.setText(product.getCompName());
        holder.textViewname.setText(product.getName());
        holder.textViewcrncy.setText(product.getmCurrency());
        holder.textViewPrice.setText(product.getmPrice());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getmImageUrl()));


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

                                                           // implements View.OnClickListener
    class ProductViewHolder extends RecyclerView.ViewHolder  {

        TextView textViewComname, textViewname, textViewcrncy, textViewPrice;
        ImageView imageView;

      //  private RecyclerViewItemClickListener itemClickListener;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewComname = itemView.findViewById(R.id.cmpname);
            textViewname = itemView.findViewById(R.id.prdname);
            textViewcrncy = itemView.findViewById(R.id.crncytext);
            textViewPrice = itemView.findViewById(R.id.pricetext);
            imageView = itemView.findViewById(R.id.imageview);
            //itemView.setOnClickListener(this);
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
