package com.castrodev.pomodoro.model;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by rodrigocastro on 31/07/16.
 */
public class RealmHelper {

    public static Realm getRealm(Context context){
        RealmConfiguration realmConfig;
        realmConfig = new RealmConfiguration.Builder(context).build();
        return Realm.getInstance(realmConfig);
    }
}
