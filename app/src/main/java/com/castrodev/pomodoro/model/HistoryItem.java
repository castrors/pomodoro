package com.castrodev.pomodoro.model;

/**
 * Created by rodrigocastro on 02/08/16.
 */
public abstract class HistoryItem {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_CONTENT = 1;

    abstract public int getType();
}
