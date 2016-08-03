package com.castrodev.pomodoro.model;

import java.util.Date;

/**
 * Created by rodrigocastro on 02/08/16.
 */
public class HeaderItem extends HistoryItem {


    private Date date;


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public int getType() {
        return TYPE_HEADER;
    }


}
