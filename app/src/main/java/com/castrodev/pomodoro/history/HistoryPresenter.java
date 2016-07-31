package com.castrodev.pomodoro.history;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class HistoryPresenter implements HistoryContract.UserActionsListener {

    @NonNull
    private final HistoryContract.View mHistoryView;

    public HistoryPresenter(@NonNull HistoryContract.View historyView) {
        mHistoryView = checkNotNull(historyView);
    }

    @Override
    public void requestPomodoroHistory() {

    }
}
