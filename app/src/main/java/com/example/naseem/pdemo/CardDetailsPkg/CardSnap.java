package com.example.naseem.pdemo.CardDetailsPkg;

import java.util.List;

/**
 * Created by User on 3/12/2018.
 */

public class CardSnap {

    private int mGravity;
    private String mText;
    private List<CardDetailsApp> mApps;

    public CardSnap(int gravity, String text, List<CardDetailsApp> apps) {
        mGravity = gravity;
        mText = text;
        mApps = apps;
    }

    public int getGravity() {
        return mGravity;
    }

    public String getText() {
        return mText;
    }

    public List<CardDetailsApp> getApps() {
        return mApps;
    }
}
