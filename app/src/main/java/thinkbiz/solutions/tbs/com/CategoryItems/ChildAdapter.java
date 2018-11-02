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
 * Created by User on 3/30/2018.
 */

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {
    private List<Child> childList;
    private Context mCtx;

    public ChildAdapter(Context context, List<Child> apps){
        mCtx=context;
        childList=apps;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mCtx).inflate(R.layout.child_list, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ChildAdapter.ViewHolder holder, int position) {
        final Child app=childList.get(position);

        //for arrow
        String catgType=app.getCateg_type();
        //Log.e("responce",catgType);

        if (catgType.equals("Category"))
        {
            holder.arrowimage.setVisibility(View.VISIBLE);
        }
        if (catgType.equals("Brand"))
        {
            holder.arrowimage.setVisibility(View.GONE);
        }

        String Pname = app.getName();
        holder.mTextViewName.setText(Pname);

        holder.setItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                String clistid = app.getId();
                String Prdname = app.getName();
                String catgType = app.getCateg_type();
                //Log.e("responce", clistid);
                SharedPreferences pref = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();

                if (catgType.equalsIgnoreCase("Category")) {

                    edit.putString("cid", clistid);
                    edit.putString("pname", Prdname);
                    edit.apply();

                    Intent intent = new Intent(view.getContext(), ChildActivity.class);
                    view.getContext().startActivity(intent);

                }
                if (catgType.equalsIgnoreCase("Brand")) {

                    edit.putString("cid", clistid);
                    edit.putString("pname", Prdname);
                    edit.apply();
                    Intent intent = new Intent(view.getContext(), GridActivity.class);
                    view.getContext().startActivity(intent);
                }
            }
        });

    }


    @Override
    public int getItemViewType(int position){

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //public ImageView mImageView;
        public TextView mTextViewName;
        public ImageView arrowimage;

        private RecyclerViewItemClickListener itemClickListener;


        public ViewHolder(View itemView ) {

            super(itemView);
            //mImageView=(ImageView)itemView.findViewById(R.id.imageViewName);
            mTextViewName=(TextView) itemView.findViewById(R.id.textViewname);
            arrowimage=(ImageView)itemView.findViewById(R.id.arrow);
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

