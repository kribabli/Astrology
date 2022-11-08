package com.example.astrology.AllAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.astrology.Fragments.NewMatchingFragment;
import com.example.astrology.Fragments.OpenKundliFragment;

public class KundliPageAdapter extends FragmentPagerAdapter {
    int tabCount;

    public KundliPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OpenKundliFragment();
            case 1:
                return new NewMatchingFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
