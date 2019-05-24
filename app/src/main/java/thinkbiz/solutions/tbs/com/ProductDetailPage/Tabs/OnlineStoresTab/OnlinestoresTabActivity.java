package thinkbiz.solutions.tbs.com.ProductDetailPage.Tabs.OnlineStoresTab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import thinkbiz.solutions.tbs.com.MoreSites.CardModel;
import thinkbiz.solutions.tbs.com.MoreSites.CustomAdapterSite;
import thinkbiz.solutions.tbs.com.R;

/**
 * Created by User on 18-Feb-19.
 */

public class OnlinestoresTabActivity extends Fragment {

    List<SiteModel> productList1;
    RecyclerView recyclerViewshp;
    String prdname;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.onlinetab, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        prdname = pref.getString("name", "");

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(prdname);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewshp = (RecyclerView)view.findViewById(R.id.siterecycl);
        recyclerViewshp.setNestedScrollingEnabled(false);
        recyclerViewshp.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerViewshp.setHasFixedSize(true);

        productList1 = new ArrayList<>();
        productList1.add(new SiteModel("Souq",R.drawable.souq,"Apple","AED","2072"));
        productList1.add(new SiteModel("Souq",R.drawable.souq,"Apple","AED","2072"));
        productList1.add(new SiteModel("Souq",R.drawable.souq,"Apple","AED","2072"));
        productList1.add(new SiteModel("Souq",R.drawable.souq,"Apple","AED","2072"));
        productList1.add(new SiteModel("Souq",R.drawable.souq,"Apple","AED","2072"));
        productList1.add(new SiteModel("Souq",R.drawable.souq,"Apple","AED","2072"));
        productList1.add(new SiteModel("Souq",R.drawable.souq,"Apple","AED","2072"));
        productList1.add(new SiteModel("Souq",R.drawable.souq,"Apple","AED","2072"));
        productList1.add(new SiteModel("Souq",R.drawable.souq,"Apple","AED","2072"));
        productList1.add(new SiteModel("Souq",R.drawable.souq,"Apple","AED","2072"));
        productList1.add(new SiteModel("Souq",R.drawable.souq,"Apple","AED","2072"));

        SiteAdapter adapter1 = new SiteAdapter(getActivity(), productList1);
        recyclerViewshp.setAdapter(adapter1);

        return view;
    }
}
