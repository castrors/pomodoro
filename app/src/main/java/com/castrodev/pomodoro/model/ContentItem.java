package com.castrodev.pomodoro.model;


/**
 * Created by rodrigocastro on 02/08/16.
 */
public class ContentItem extends HistoryItem {


    private Pomodoro pomodoro;


    public Pomodoro getPomodoro() {
        return pomodoro;
    }


    public void setPomodoro(Pomodoro pomodoro) {
        this.pomodoro = pomodoro;
    }


    @Override
    public int getType() {
        return TYPE_CONTENT;
    }
}
