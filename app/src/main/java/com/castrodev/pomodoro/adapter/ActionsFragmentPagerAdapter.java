package com.castrodev.pomodoro.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.castrodev.pomodoro.history.HistoryFragment;
import com.castrodev.pomodoro.newpomodoro.NewPomodoroFragment;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class ActionsFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final String TAB_NEW = "NEW";
    private static final String TAB_HISTORY = "HISTORY";

    private static final int POSITION_NEW = 0;
    private static final int POSITION_HISTORY = 1;


    final int TABS_COUNT = 2;
    private String tabTitles[] = new String[]{TAB_NEW, TAB_HISTORY};
    private Context context;

    public ActionsFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case POSITION_NEW:
                return NewPomodoroFragment.newInstance();
            case POSITION_HISTORY:
                return HistoryFragment.newInstance();
        }
        return new Fragment();
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
