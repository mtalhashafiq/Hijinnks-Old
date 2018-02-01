package com.example.mtalh.hijinnks;

/**
 * Created by CP on 12/20/2017.
 */

public class Model_AddEvent {
    String images_url;
    String video_url;
    String catogery;
    String Title;
    String Location;
    int event_time_hour;
    int event_time_minutes;
    String add_event_item;
    String description_of_event;

    public Model_AddEvent(String images_url, String video_url, String catogery, String title, String location, int event_time_hour, int event_time_minutes, String add_event_item, String description_of_event) {
        this.images_url = images_url;
        this.video_url = video_url;
        this.catogery = catogery;
        Title = title;
        Location = location;
        this.event_time_hour = event_time_hour;
        this.event_time_minutes = event_time_minutes;
        this.add_event_item = add_event_item;
        this.description_of_event = description_of_event;
    }

    public String getImages_url() {
        return images_url;
    }

    public void setImages_url(String images_url) {
        this.images_url = images_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getCatogery() {
        return catogery;
    }

    public void setCatogery(String catogery) {
        this.catogery = catogery;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getEvent_time_hour() {
        return event_time_hour;
    }

    public void setEvent_time_hour(int event_time_hour) {
        this.event_time_hour = event_time_hour;
    }

    public int getEvent_time_minutes() {
        return event_time_minutes;
    }

    public void setEvent_time_minutes(int event_time_minutes) {
        this.event_time_minutes = event_time_minutes;
    }

    public String getAdd_event_item() {
        return add_event_item;
    }

    public void setAdd_event_item(String add_event_item) {
        this.add_event_item = add_event_item;
    }

    public String getDescription_of_event() {
        return description_of_event;
    }

    public void setDescription_of_event(String description_of_event) {
        this.description_of_event = description_of_event;
    }
}
