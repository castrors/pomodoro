package com.castrodev.pomodoro.model;

import android.content.Context;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class Pomodoro extends RealmObject {

    @PrimaryKey
    private long id;
    private String duration;
    private String status;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public void save(Context context){
        RealmHelper.getRealm(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(Pomodoro.this);
            }
        });
    }

    @Override
    public String toString() {
        return String.format("{duration: %s, status: %s, date: %s}", duration, status, date);
    }
}
