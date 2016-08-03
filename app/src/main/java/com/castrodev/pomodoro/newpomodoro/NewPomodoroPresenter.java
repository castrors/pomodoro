package com.castrodev.pomodoro.newpomodoro;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.castrodev.pomodoro.R;
import com.castrodev.pomodoro.model.Pomodoro;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class NewPomodoroPresenter implements NewPomodoroContract.UserActionsListener {

    public static final String FINISHED = "Finished";
    public static final String STOPPED = "Stopped";
    private static final String POMODORO_DURATION = "pomodoro_duration";
    private static final int DEFAULT_DURATION = 25;


    private Context context;

    @NonNull
    private final NewPomodoroContract.View mNewPomodoroView;
    private CountDownTimer countDownTimer;
    private long displayableDuration;
    private int durationMinutes = DEFAULT_DURATION;
    private long defaultDurationMillis;


    public NewPomodoroPresenter(@NonNull NewPomodoroContract.View newPomodoroView, Context context) {
        mNewPomodoroView = checkNotNull(newPomodoroView);
        this.context = context;
    }

    @Override
    public void startCounter() {
        updateDurationTime();
        mNewPomodoroView.setCountDownTimeRunning(true);
        countDownTimer = new CountDownTimer(defaultDurationMillis, 1000) {

            public void onTick(long millisUntilFinished) {
                displayableDuration = millisUntilFinished;
                mNewPomodoroView.showCountDownTime(getHumanReadableDuration(displayableDuration));
            }

            public void onFinish() {
                Log.i("NEW_POMODORO", "Finished the pomodoro");
                displayableDuration = 0;
                savePomodoro(FINISHED);
                mNewPomodoroView.setCountDownTimeRunning(false);
            }
        }.start();
    }

    @Override
    public void stopCounter() {
        if (countDownTimer != null) {
            Log.i("NEW_POMODORO", "Canceled the pomodoro");
            countDownTimer.cancel();
            savePomodoro(STOPPED);
            mNewPomodoroView.setCountDownTimeRunning(false);
        }
    }

    @Override
    public void updateTimerLength() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        durationMinutes = Integer.parseInt(prefs.getString(POMODORO_DURATION, String.valueOf(DEFAULT_DURATION)));
        updateDurationTime();
        mNewPomodoroView.showCountDownTime(getHumanReadableDuration(defaultDurationMillis));
    }

    private void savePomodoro(String status) {
        updateDurationTime();
        long millis = defaultDurationMillis - displayableDuration;
        Pomodoro pomodoro = new Pomodoro();
        pomodoro.setDuration(getHumanReadableDuration(millis));
        pomodoro.setStatus(status);
        pomodoro.setDate(new Date());
        pomodoro.save(context);
        Log.i("NEW_POMODORO", "Saved: " + pomodoro.toString());
    }

    private String getHumanReadableDuration(long millis) {
        return String.format(context.getString(R.string.clock_minutes_and_seconds),
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
    }

    private void updateDurationTime() {
        defaultDurationMillis =  durationMinutes * 60 * 1000;
    }
}
