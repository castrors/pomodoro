package com.castrodev.pomodoro.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.castrodev.pomodoro.R;
import com.castrodev.pomodoro.model.Pomodoro;
import com.castrodev.pomodoro.viewholder.HistoryContentViewHolder;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by rodrigocastro on 01/08/16.
 */
public class HistoryListAdapter extends RecyclerView.Adapter<HistoryContentViewHolder> {

    private static final int ONE_MINUTE_IN_MILLIS = 60 * 1000;
    private static final int ONE_HOUR_IN_MILLIS = 60 * ONE_MINUTE_IN_MILLIS;
    private static final int THREE_HOURS_IN_MILLIS = 3 * ONE_HOUR_IN_MILLIS;
    private List<Pomodoro> mPomodoros;
    private final Context context;

    public HistoryListAdapter(List<Pomodoro> pomodoros, Context context) {
        this.mPomodoros = pomodoros;
        this.context = context;
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
        viewHolder.textRelativeTime.setText(getRelativeTimeText(pomodoro.getDate()));
        viewHolder.textStatus.setText(pomodoro.getStatus());
    }

    private String getRelativeTimeText(Date date) {
        Duration duration = new Duration(date.getTime(), DateTime.now().getMillis());
        String relativeTime = "";
        if(duration.getMillis()<ONE_MINUTE_IN_MILLIS)
            relativeTime = context.getResources().getQuantityString(R.plurals.seconds, ((int) duration.getStandardSeconds()), duration.getStandardSeconds());
        else if(duration.getMillis()<ONE_HOUR_IN_MILLIS)
            relativeTime = context.getResources().getQuantityString(R.plurals.minutes, ((int) duration.getStandardMinutes()), duration.getStandardMinutes());
        else if(duration.getMillis()<THREE_HOURS_IN_MILLIS)
            relativeTime = context.getResources().getQuantityString(R.plurals.hours, ((int) duration.getStandardHours()), duration.getStandardHours());
        else{
            DateFormat dateFormat = new SimpleDateFormat(context.getString(R.string.format_hour_am_pm), Locale.getDefault());
            relativeTime = dateFormat.format(date);
        }
        return relativeTime;
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
