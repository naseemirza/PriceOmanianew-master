package thinkbiz.solutions.tbs.com;

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

import com.bumptech.glide.Glide;
import thinkbiz.solutions.tbs.com.CardDetailsPkg.RecyclerViewItemClickListener;
import thinkbiz.solutions.tbs.com.CategoryItems.GridActivity;

import java.util.ArrayList;

/**
 * Created by User on 05-Jun-18.
 */

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder>  {

    private ArrayList<CatModel> glist;
    private Context context;

    public CatAdapter(Context context,ArrayList<CatModel> glist) {
        this.glist = glist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.morecategory, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CatModel app=glist.get(position);



        String Pname = app.getName();
        String imageurl = app.getImageUrl();


        holder.catname.setText(Pname);

        Glide.with(context)
                .load(imageurl)
                .fitCenter()
                .into(holder.catimage);

        holder.setItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                String clistid=app.getId().toString();
                String prdname=app.getName().toString();
                Log.e("responce",clistid);

                SharedPreferences pref = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("cid", clistid);
                edit.putString("pname",prdname);

                edit.commit();

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
        return glist.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView catimage;
        public TextView catname ;




        private RecyclerViewItemClickListener itemClickListener;


        public ViewHolder(View itemView ) {

            super(itemView);
            catimage=(ImageView)itemView.findViewById(R.id.imageViewName);
            catname=(TextView) itemView.findViewById(R.id.textViewname);
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

