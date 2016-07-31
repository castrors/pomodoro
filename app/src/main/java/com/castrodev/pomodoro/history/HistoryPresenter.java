package com.castrodev.pomodoro.history;

import android.content.Context;
import android.support.annotation.NonNull;

import com.castrodev.pomodoro.model.Pomodoro;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class HistoryPresenter implements HistoryContract.UserActionsListener {

    @NonNull
    private final HistoryContract.View mHistoryView;
    private final Context context;

    public HistoryPresenter(@NonNull HistoryContract.View historyView, Context context) {
        mHistoryView = checkNotNull(historyView);
        this.context = context;
    }

    @Override
    public void requestPomodoroHistory() {
        mHistoryView.showPomodoroHistory(Pomodoro.getAll(context));
    }
}
