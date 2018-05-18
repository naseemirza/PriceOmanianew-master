package com.example.naseem.pdemo.CardDetailsPkg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.naseem.pdemo.R;

import java.util.List;

/**
 * Created by User on 3/9/2018.
 */

public class StorageAdapter extends ArrayAdapter<StorageModel> {

    private List<StorageModel> storageList;
    private Context mCtx;

    public StorageAdapter(List<StorageModel> storageList, Context mCtx) {
        super(mCtx, R.layout.storagelist, storageList);
        this.storageList = storageList;
        this.mCtx = mCtx;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

//        LayoutInflater inflater = (LayoutInflater) mCtx
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.storagelist, null, true);
        TextView textViewname = (TextView)listViewItem.findViewById(R.id.textViewstorage);

        StorageModel storage = storageList.get(position);

        textViewname.setText(storage.getStorage());

        return listViewItem;
    }


}
