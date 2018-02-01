package com.example.mtalh.hijinnks;

import android.app.Application;
import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by mtalh on 08-Dec-17.
 */

public class SharedPrefrencesClass extends Application {
    private  int setting_checked_backtrack;
    private  int home_checked_backtrack;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the Prefs class
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

    }

    public int getSetting_checked_backtrack() {
        return setting_checked_backtrack;
    }

    public void setSetting_checked_backtrack(int setting_checked_backtrack) {
        this.setting_checked_backtrack = setting_checked_backtrack;
    }

    public int getHome_checked_backtrack() {
        return home_checked_backtrack;
    }

    public void setHome_checked_backtrack(int home_checked_backtrack) {
        this.home_checked_backtrack = home_checked_backtrack;
    }


}
