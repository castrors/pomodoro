package com.castrodev.pomodoro.history;

import com.castrodev.pomodoro.model.Pomodoro;

import java.util.List;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class HistoryContract {

    interface View {

        void setProgressIndicator(boolean active);
        void showPomodoroHistory(List<Pomodoro> pomodoroList);

    }

    interface UserActionsListener {

        void requestPomodoroHistory();

    }
}
