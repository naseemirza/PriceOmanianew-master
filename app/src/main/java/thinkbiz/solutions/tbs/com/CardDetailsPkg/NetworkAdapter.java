package thinkbiz.solutions.tbs.com.CardDetailsPkg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import thinkbiz.solutions.tbs.com.R;

import java.util.List;

/**
 * Created by User on 3/12/2018.
 */

public class NetworkAdapter extends ArrayAdapter<NetworkModel> {

    private List<NetworkModel> netList;
    private Context mCtx;

    public NetworkAdapter(List<NetworkModel> netList, Context mCtx) {
        super(mCtx, R.layout.networklist, netList);
        this.netList = netList;
        this.mCtx = mCtx;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

//        LayoutInflater inflater = (LayoutInflater) mCtx
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.networklist, null, true);
        TextView textViewname = (TextView)listViewItem.findViewById(R.id.textViewbrand);

        NetworkModel netwrk = netList.get(position);

        textViewname.setText(netwrk.getNetwork());

        return listViewItem;
    }

}
