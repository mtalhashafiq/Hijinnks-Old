package com.example.mtalh.hijinnks;

/**
 * Created by mtalh on 23-Nov-17.
 */

public class Model_addEvent_addIntrest {
    String event_name;

    public String getText_show() {
        return event_name;
    }

    public void setText_show(String text_show) {
        this.event_name = text_show;
    }

    public Model_addEvent_addIntrest(String text_show) {
        this.event_name = text_show;

    }
}
