package com.example.astrology.BottomFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.astrology.Fragments.AllFragment;
import com.example.astrology.Fragments.BusinessFragment;
import com.example.astrology.Fragments.CareerFragment;
import com.example.astrology.Fragments.EducationFragment;
import com.example.astrology.Fragments.FinanceFragment;
import com.example.astrology.Fragments.HealthFragment;
import com.example.astrology.Fragments.KidsFragment;
import com.example.astrology.Fragments.LegalFragment;
import com.example.astrology.Fragments.LoveFragment;
import com.example.astrology.Fragments.MarriageFragment;
import com.example.astrology.Fragments.ParentsFragment;
import com.example.astrology.Fragments.RemediesFragment;
import com.example.astrology.Fragments.WealthFragment;
import com.example.astrology.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentManager childFragmentManager;

    //icon for tabLayout
    private int[] tabIcons = {
            R.drawable.ic_all, R.drawable.ic_love, R.drawable.ic_career, R.drawable.ic_marriage,
            R.drawable.ic_health, R.drawable.ic_wealth, R.drawable.ic_finance, R.drawable.ic_business,
            R.drawable.ic_legal, R.drawable.ic_education, R.drawable.ic_remedies, R.drawable.ic_kids,
            R.drawable.ic_parents
    };

    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        viewPager = rootView.findViewById(R.id.viewpager);
        tabLayout = rootView.findViewById(R.id.tabs);

        childFragmentManager = getChildFragmentManager();
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        return rootView;
    }

    //Call the method for icon in tabLayout
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
        tabLayout.getTabAt(6).setIcon(tabIcons[6]);
        tabLayout.getTabAt(7).setIcon(tabIcons[7]);
        tabLayout.getTabAt(8).setIcon(tabIcons[8]);
        tabLayout.getTabAt(9).setIcon(tabIcons[9]);
        tabLayout.getTabAt(10).setIcon(tabIcons[10]);
        tabLayout.getTabAt(11).setIcon(tabIcons[11]);
        tabLayout.getTabAt(12).setIcon(tabIcons[12]);
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(childFragmentManager);
        adapter.addFragment(new AllFragment(), "All");
        adapter.addFragment(new LoveFragment(), "Love");
        adapter.addFragment(new CareerFragment(), "Career");
        adapter.addFragment(new MarriageFragment(), "Marriage");
        adapter.addFragment(new HealthFragment(), "Health");
        adapter.addFragment(new WealthFragment(), "Wealth");
        adapter.addFragment(new FinanceFragment(), "Finance");
        adapter.addFragment(new BusinessFragment(), "Business");
        adapter.addFragment(new LegalFragment(), "Legal");
        adapter.addFragment(new EducationFragment(), "Education");
        adapter.addFragment(new RemediesFragment(), "Remedies");
        adapter.addFragment(new KidsFragment(), "Kids");
        adapter.addFragment(new ParentsFragment(), "Parents");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return FragmentStatePagerAdapter.POSITION_NONE;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}