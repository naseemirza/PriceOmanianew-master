package thinkbiz.solutions.tbs.com.ProductDetailPage.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thinkbiz.solutions.tbs.com.R;

/**
 * Created by User on 18-Feb-19.
 */

public class FAQTabActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.faqtab, container, false);
    }
}