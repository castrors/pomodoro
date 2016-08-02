package com.castrodev.pomodoro.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.castrodev.pomodoro.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rodrigocastro on 01/08/16.
 */
public class HistoryContentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_time)
    public TextView textTime;
    @BindView(R.id.text_status)
    public TextView textStatus;
    @BindView(R.id.text_relative_time)
    public TextView textRelativeTime;

    public HistoryContentViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}