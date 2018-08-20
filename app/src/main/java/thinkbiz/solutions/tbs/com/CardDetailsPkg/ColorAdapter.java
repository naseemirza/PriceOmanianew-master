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
 * Created by User on 3/9/2018.
 */

public class ColorAdapter extends ArrayAdapter<ColorModel> {

    private List<ColorModel> colorList;
    private Context mCtx;

    public ColorAdapter(List<ColorModel> colorList, Context mCtx) {
        super(mCtx, R.layout.colorlist, colorList);
        this.colorList = colorList;
        this.mCtx = mCtx;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

//        LayoutInflater inflater = (LayoutInflater) mCtx
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.colorlist, null, true);
        TextView textViewname = (TextView)listViewItem.findViewById(R.id.textViewcolor);

        ColorModel color = colorList.get(position);

        textViewname.setText(color.getColorname());

        return listViewItem;
    }

//
}
