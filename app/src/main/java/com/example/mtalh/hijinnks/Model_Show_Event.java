package com.example.mtalh.hijinnks;

/**
 * Created by CP on 12/20/2017.
 */

public class Model_Show_Event {
    String profile_image;
    String name;
    String location_address;
    String distance;
    int video;
    String event_name;
    int date_of_event;
    int time_of_event;
    String event_description;
    int comment_count;
    int views_count;
    int favourit_count;
    int favourit_click;
    int view_click;
    int share_click;

    public Model_Show_Event(String profile_image, String name, String location_address, String distance, int video, String event_name, int date_of_event, int time_of_event, String event_description, int comment_count, int views_count, int favourit_count, int favourit_click, int view_click, int share_click) {
        this.profile_image = profile_image;
        this.name = name;
        this.location_address = location_address;
        this.distance = distance;
        this.video = video;
        this.event_name = event_name;
        this.date_of_event = date_of_event;
        this.time_of_event = time_of_event;
        this.event_description = event_description;
        this.comment_count = comment_count;
        this.views_count = views_count;
        this.favourit_count = favourit_count;
        this.favourit_click = favourit_click;
        this.view_click = view_click;
        this.share_click = share_click;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation_address() {
        return location_address;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public int getDate_of_event() {
        return date_of_event;
    }

    public void setDate_of_event(int date_of_event) {
        this.date_of_event = date_of_event;
    }

    public int getTime_of_event() {
        return time_of_event;
    }

    public void setTime_of_event(int time_of_event) {
        this.time_of_event = time_of_event;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public int getFavourit_count() {
        return favourit_count;
    }

    public void setFavourit_count(int favourit_count) {
        this.favourit_count = favourit_count;
    }

    public int getFavourit_click() {
        return favourit_click;
    }

    public void setFavourit_click(int favourit_click) {
        this.favourit_click = favourit_click;
    }

    public int getView_click() {
        return view_click;
    }

    public void setView_click(int view_click) {
        this.view_click = view_click;
    }

    public int getShare_click() {
        return share_click;
    }

    public void setShare_click(int share_click) {
        this.share_click = share_click;
    }
}
