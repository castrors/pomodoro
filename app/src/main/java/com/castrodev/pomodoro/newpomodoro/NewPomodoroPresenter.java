package com.castrodev.pomodoro.newpomodoro;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class NewPomodoroPresenter implements NewPomodoroContract.UserActionsListener {

    @NonNull
    private final NewPomodoroContract.View mNewPomodoroView;

    public NewPomodoroPresenter(@NonNull NewPomodoroContract.View newPomodoroView) {
        mNewPomodoroView = checkNotNull(newPomodoroView);
    }

    @Override
    public void startCounter() {

    }

    @Override
    public void stopCounter() {

    }
}
