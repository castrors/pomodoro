package com.castrodev.pomodoro.newpomodoro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.castrodev.pomodoro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class NewPomodoroFragment extends Fragment implements NewPomodoroContract.View {

    @BindView(R.id.textCountDownTime)
    TextView textCountDownTime;
    @BindView(R.id.fabStartStopPomodoro)
    FloatingActionButton fabStartStopPomodoro;

    private boolean running;

    private NewPomodoroContract.UserActionsListener mActionListener;

    public static NewPomodoroFragment newInstance() {
        return new NewPomodoroFragment();
    }

    public NewPomodoroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActionListener = new NewPomodoroPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_newpomodoro, container, false);
        ButterKnife.bind(this, root);
        setupView();
        return root;
    }

    private void setupView() {
        setCountDownTimeRunning(false);
    }


    @OnClick(R.id.fabStartStopPomodoro)
    public void startStopPomodoro() {
        if (isCountDownTimeRunning()) {
            mActionListener.stopCounter();
        } else {
            mActionListener.startCounter();
        }

    }

    public boolean isCountDownTimeRunning() {
        return running;
    }

    @Override
    public void showCountDownTime(String time) {

    }

    @Override
    public void setCountDownTimeRunning(boolean running) {
        this.running = running;
        changeFabIcon(running ? R.drawable.ic_stop : R.drawable.ic_start );

    }

    private void changeFabIcon(int iconRes) {
        fabStartStopPomodoro.setImageResource(iconRes);
    }


}
