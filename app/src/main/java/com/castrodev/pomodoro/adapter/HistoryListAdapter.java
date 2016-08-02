package com.castrodev.pomodoro.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.castrodev.pomodoro.R;
import com.castrodev.pomodoro.model.Pomodoro;
import com.castrodev.pomodoro.viewholder.HistoryContentViewHolder;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by rodrigocastro on 01/08/16.
 */
public class HistoryListAdapter extends RecyclerView.Adapter<HistoryContentViewHolder> {

    private List<Pomodoro> mPomodoros;

    public HistoryListAdapter(List<Pomodoro> pomodoros) {
        this.mPomodoros = pomodoros;
    }

    @Override
    public HistoryContentViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        ViewGroup viewGroupDefault = (ViewGroup) mInflater.inflate(R.layout.item_history_content, viewGroup, false);
        return new HistoryContentViewHolder(viewGroupDefault);
    }

    @Override
    public void onBindViewHolder(HistoryContentViewHolder viewHolder, final int position) {
        Pomodoro pomodoro = mPomodoros.get(position);
        viewHolder.textTime.setText(pomodoro.getDuration());
        viewHolder.textRelativeTime.setText(pomodoro.getDate().toString());
        viewHolder.textStatus.setText(pomodoro.getStatus());
    }

    @Override
    public int getItemCount() {
        return mPomodoros.size();
    }

    public void replaceData(List<Pomodoro> pomodoros) {
        setList(pomodoros);
        notifyDataSetChanged();
    }

    private void setList(List<Pomodoro> pomodoros) {
        mPomodoros = checkNotNull(pomodoros);
    }


}
