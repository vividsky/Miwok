package com.example.miwok;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    Context mContext;
    String[] tabTitles = {"Numbers", "Phrases", "Colors", "Family Members"};
    public PagerAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return new KitchenFragment();
            case 1 : return new BathFragment();
            case 2 : return new ExtraFragment();
            case 3: return new ToBuyFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
