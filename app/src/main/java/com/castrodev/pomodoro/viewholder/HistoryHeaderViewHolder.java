package com.castrodev.pomodoro.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.castrodev.pomodoro.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rodrigocastro on 02/08/16.
 */
public class HistoryHeaderViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_header)
    public TextView textHeader;

    public HistoryHeaderViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}