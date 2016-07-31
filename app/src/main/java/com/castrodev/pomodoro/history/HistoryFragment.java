package com.castrodev.pomodoro.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.castrodev.pomodoro.R;
import com.castrodev.pomodoro.model.Pomodoro;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class HistoryFragment extends Fragment implements HistoryContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private HistoryContract.UserActionsListener mActionListener;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    public HistoryFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActionListener = new HistoryPresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void showPomodoroHistory(List<Pomodoro> pomodoroList) {

    }
}
