package com.castrodev.pomodoro.newpomodoro;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public interface NewPomodoroContract {

    interface View {

        void showCountDownTime(String time);

        void setCountDownTimeRunning(boolean running);

    }

    interface UserActionsListener {

        void startCounter();

        void stopCounter();

        void updateTimerLength();
    }
}