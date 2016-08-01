package com.castrodev.pomodoro.newpomodoro;

import android.content.Context;
import android.os.CountDownTimer;
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
    private int DEFAULT_DURATION_MINUTES = 25;
    private long DEFAULT_DURATION_MILLIS = DEFAULT_DURATION_MINUTES * 60 * 1000;

    private Context context;

    @NonNull
    private final NewPomodoroContract.View mNewPomodoroView;
    private CountDownTimer countDownTimer;
    private long duration;

    public NewPomodoroPresenter(@NonNull NewPomodoroContract.View newPomodoroView, Context context) {
        mNewPomodoroView = checkNotNull(newPomodoroView);
        this.context = context;
    }

    @Override
    public void startCounter() {
        mNewPomodoroView.setCountDownTimeRunning(true);
        countDownTimer = new CountDownTimer(DEFAULT_DURATION_MILLIS, 1000) {

            public void onTick(long millisUntilFinished) {
                duration = millisUntilFinished;
                mNewPomodoroView.showCountDownTime(getHumanReadableDuration(duration));
            }

            public void onFinish() {
                Log.i("NEW_POMODORO", "Finished the pomodoro");
                duration = 0;
                savePomodoro(FINISHED);
                mNewPomodoroView.setCountDownTimeRunning(false);
            }
        }.start();
    }

    @Override
    public void stopCounter() {
        if(countDownTimer!= null){
            Log.i("NEW_POMODORO", "Canceled the pomodoro");
            countDownTimer.cancel();
            savePomodoro(STOPPED);
            mNewPomodoroView.setCountDownTimeRunning(false);
        }
    }

    private void savePomodoro(String status) {
        long millis = DEFAULT_DURATION_MILLIS-duration;
        Pomodoro pomodoro = new Pomodoro();
        pomodoro.setDuration(getHumanReadableDuration(millis));
        pomodoro.setStatus(status);
        pomodoro.setDate(new Date());
        pomodoro.save(context);
        Log.i("NEW_POMODORO", "Saved: "+pomodoro.toString());
    }

    private String getHumanReadableDuration(long millis) {
        return String.format(context.getString(R.string.clock_minutes_and_seconds),
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
    }
}
