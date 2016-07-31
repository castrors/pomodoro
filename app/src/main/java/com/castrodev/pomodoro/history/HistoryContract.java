package com.castrodev.pomodoro.history;

import java.util.List;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class HistoryContract {

    interface View {

        void showPomodoroHistory(List<String> pomodoroList);

    }

    interface UserActionsListener {

        void requestPomodoroHistory();

    }
}
