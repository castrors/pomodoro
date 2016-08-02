package com.castrodev.pomodoro.model;

import android.content.Context;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class Pomodoro extends RealmObject {

    private String duration;
    private String status;
    private Date date;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static List<Pomodoro> getAll(Context context) {
        return RealmHelper.getRealm(context).where(Pomodoro.class).findAll();
    }

    public void save(Context context) {
        Realm realm = RealmHelper.getRealm(context);
        realm.beginTransaction();

        Pomodoro managedPomodoro = realm.createObject(Pomodoro.class);
        managedPomodoro.clone(this);
        realm.commitTransaction();

    }

    private void clone(Pomodoro pomodoro) {
        this.date = pomodoro.date;
        this.duration = pomodoro.duration;
        this.status = pomodoro.status;
    }

    @Override
    public String toString() {
        return String.format("{duration: %s, status: %s, date: %s}", duration, status, date);
    }
}
