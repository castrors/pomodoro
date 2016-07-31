package com.castrodev.pomodoro.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class ActionsFragmentPagerAdapter extends FragmentPagerAdapter {

    final int TABS_COUNT = 2;
    private String tabTitles[] = new String[] { "New", "History"};
    private Context context;

    public ActionsFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return SampleFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return TABS_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
