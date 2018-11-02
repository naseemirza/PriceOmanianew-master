package thinkbiz.solutions.tbs.com.CategoryItems;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import thinkbiz.solutions.tbs.com.CardDetailsPkg.RecyclerViewItemClickListener;
import thinkbiz.solutions.tbs.com.R;

import java.util.List;

/**
 * Created by User on 24-May-18.
 */

public class SubChildAdapter extends RecyclerView.Adapter<SubChildAdapter.ViewHolder> {

    private List<SubChild> schildList;
    private Context mCtx;

    public SubChildAdapter(Context context, List<SubChild> apps){
        mCtx=context;
        schildList=apps;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mCtx).inflate(R.layout.child_items, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final SubChildAdapter.ViewHolder holder, int position) {
        final SubChild app=schildList.get(position);
        // arrow in list

        String catgType=app.getCateg_type();
        Log.e("responce",catgType);

        if (catgType.equals("Brand"))
        {
            holder.arrowimage.setVisibility(View.GONE);
        }
        if (catgType.equals("Category"))
        {
            holder.arrowimage.setVisibility(View.VISIBLE);
        }

        String Pname = app.getName();
        // String imageurl = app.getImageUrl();

        holder.mTextViewName.setText(Pname);

        holder.setItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                String prd_id=app.getId();
                String Prdname=app.getName();
                Log.e("responce",prd_id);
                SharedPreferences pref = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();

                edit.putString("cid",prd_id);
                edit.putString("pname",Prdname);
                edit.apply();

                Intent intent = new Intent(view.getContext(), GridActivity.class);
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
        return schildList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //public ImageView mImageView;
        public TextView mTextViewName;
        public ImageView arrowimage;


        private RecyclerViewItemClickListener itemClickListener;

        public ViewHolder(View itemView) {

            super(itemView);
            //mImageView=(ImageView)itemView.findViewById(R.id.imageViewName);
            mTextViewName = (TextView) itemView.findViewById(R.id.textViewname);
            arrowimage=(ImageView)itemView.findViewById(R.id.arrow);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onClick(v, getLayoutPosition());
        }

        public void setItemClickListener(RecyclerViewItemClickListener ic) {
            this.itemClickListener = ic;

        }
    }

}
