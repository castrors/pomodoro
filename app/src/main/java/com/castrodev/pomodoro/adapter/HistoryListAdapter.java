package com.castrodev.pomodoro.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.castrodev.pomodoro.R;
import com.castrodev.pomodoro.helper.DateHelper;
import com.castrodev.pomodoro.model.ContentItem;
import com.castrodev.pomodoro.model.HeaderItem;
import com.castrodev.pomodoro.model.HistoryItem;
import com.castrodev.pomodoro.model.Pomodoro;
import com.castrodev.pomodoro.viewholder.HistoryContentViewHolder;
import com.castrodev.pomodoro.viewholder.HistoryHeaderViewHolder;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by rodrigocastro on 01/08/16.
 */
public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Pomodoro> mPomodoros;
    private List<HistoryItem> mHistoryItems;
    private TreeMap<Date,List<Pomodoro>> mTreeMapPomodoros;
    private final Context context;

    public HistoryListAdapter(List<Pomodoro> pomodoros, Context context) {
        this.mPomodoros = pomodoros;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        if (viewType == HistoryItem.TYPE_HEADER) {
            View itemView = mInflater.inflate(R.layout.item_history_header, viewGroup, false);
            return new HistoryHeaderViewHolder(itemView);
        } else {
            View itemView = mInflater.inflate(R.layout.item_history_content, viewGroup, false);
            return new HistoryContentViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        int type = getItemViewType(position);
        if (type == HistoryItem.TYPE_HEADER) {
            HeaderItem header = (HeaderItem) mHistoryItems.get(position);
            HistoryHeaderViewHolder holder = (HistoryHeaderViewHolder) viewHolder;
            holder.textHeader.setText(DateHelper.getRelativeDateText(header.getDate()));
        } else {
            ContentItem content = (ContentItem) mHistoryItems.get(position);
            HistoryContentViewHolder holder = (HistoryContentViewHolder) viewHolder;
            holder.textTime.setText(content.getPomodoro().getDuration());
            holder.textRelativeTime.setText(DateHelper.getRelativeTimeText(context, content.getPomodoro().getDate()));
            holder.textStatus.setText(content.getPomodoro().getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return mHistoryItems.size();
    }


    @Override
    public int getItemViewType(int position) {
        return mHistoryItems.get(position).getType();
    }

    public void replaceData(List<Pomodoro> pomodoros) {
        setList(pomodoros);
        notifyDataSetChanged();
    }

    private void setList(List<Pomodoro> pomodoros) {
        mPomodoros = checkNotNull(pomodoros);
        createTreeMap();
        createHistoryListItem();
    }

    private void createTreeMap() {
        mTreeMapPomodoros = new TreeMap<>(new Comparator<Date>() {
            @Override
            public int compare(Date older, Date mostRecent) {
                return mostRecent.compareTo(older);
            }
        });
        for (Pomodoro pomodoro: mPomodoros) {
            Date currentDate = pomodoro.getDate();
            DateTime dateTime = new DateTime(currentDate);
            dateTime = dateTime.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
            List<Pomodoro> pomodoroList = mTreeMapPomodoros.get(dateTime.toDate());
            if(pomodoroList==null)
                pomodoroList = new ArrayList<>();
            pomodoroList.add(pomodoro);
            mTreeMapPomodoros.put(dateTime.toDate(), pomodoroList);
        }
    }

    public void createHistoryListItem(){

        mHistoryItems = new ArrayList<>();
        for (Date date : mTreeMapPomodoros.keySet()) {
            HeaderItem header = new HeaderItem();
            header.setDate(date);
            mHistoryItems.add(header);
            for (Pomodoro pomodoro : mTreeMapPomodoros.get(date)) {
                ContentItem content = new ContentItem();
                content.setPomodoro(pomodoro);
                mHistoryItems.add(content);
            }
        }
    }


}
