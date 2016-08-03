package com.castrodev.pomodoro.newpomodoro;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.castrodev.pomodoro.MainActivity;
import com.castrodev.pomodoro.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by rodrigocastro on 02/08/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NewPomodoroTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void newPomodoro_defaultTimerIs25() {
        onView(withId(R.id.text_count_down_time)).check(matches(withText("25:00")));
    }


    @Test
    public void newPomodoro_runningTimerIsNot25() {
        onView(withId(R.id.fab_start_stop_pomodoro)).perform(click());
        onView(withId(R.id.text_count_down_time)).check(matches(not(withText("25:00"))));
    }


}
