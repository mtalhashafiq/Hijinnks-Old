package com.example.mtalh.hijinnks;

import android.app.Application;

/**
 * Created by CP on 12/21/2017.
 */

public class fragment_Back_Track extends Application {

    private  int setting_checked_backtrack;
    private  int home_checked_backtrack;

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
