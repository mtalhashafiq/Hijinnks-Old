package com.example.mtalh.hijinnks;

/**
 * Created by CP on 12/20/2017.
 */

public class Model_Profile_Setting {
    String cover_photo_url;
    String profile_photo_url;
    String name;
    String location;
    String email;
    String description;

    public Model_Profile_Setting(String cover_photo_url, String profile_photo_url, String name, String location, String email, String description) {
        this.cover_photo_url = cover_photo_url;
        this.profile_photo_url = profile_photo_url;
        this.name = name;
        this.location = location;
        this.email = email;
        this.description = description;
    }

    public String getCover_photo_url() {
        return cover_photo_url;
    }

    public void setCover_photo_url(String cover_photo_url) {
        this.cover_photo_url = cover_photo_url;
    }

    public String getProfile_photo_url() {
        return profile_photo_url;
    }

    public void setProfile_photo_url(String profile_photo_url) {
        this.profile_photo_url = profile_photo_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
