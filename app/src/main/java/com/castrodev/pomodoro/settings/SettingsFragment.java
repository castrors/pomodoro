package com.castrodev.pomodoro.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.castrodev.pomodoro.R;

/**
 * Created by rodrigocastro on 02/08/16.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }

}
