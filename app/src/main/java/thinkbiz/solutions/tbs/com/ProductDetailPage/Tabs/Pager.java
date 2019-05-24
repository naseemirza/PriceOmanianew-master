package thinkbiz.solutions.tbs.com.ProductDetailPage.Tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import thinkbiz.solutions.tbs.com.ProductDetailPage.Tabs.OnlineStoresTab.OnlinestoresTabActivity;

/**
 * Created by User on 18-Feb-19.
 */

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                OnlinestoresTabActivity tab1 = new OnlinestoresTabActivity();
                return tab1;
            case 1:
                DescTabActivity tab2 = new DescTabActivity();
                return tab2;
            case 2:
                FAQTabActivity tab3 = new FAQTabActivity();
                return tab3;
            case 3:
                SpecTabActivity tab4 = new SpecTabActivity();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}