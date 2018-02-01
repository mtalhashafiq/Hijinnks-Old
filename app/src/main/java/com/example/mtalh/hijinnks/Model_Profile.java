package com.example.mtalh.hijinnks;

/**
 * Created by CP on 12/20/2017.
 */

public class Model_Profile {

String name;
int profile_image;
String location_address;
String description;
int followers;
int following;
int intrest;

    public Model_Profile(String name, int profile_image, String location_address, String description, int followers, int following, int intrest) {
        this.name = name;
        this.profile_image = profile_image;
        this.location_address = location_address;
        this.description = description;
        this.followers = followers;
        this.following = following;
        this.intrest = intrest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(int profile_image) {
        this.profile_image = profile_image;
    }

    public String getLocation_address() {
        return location_address;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getIntrest() {
        return intrest;
    }

    public void setIntrest(int intrest) {
        this.intrest = intrest;
    }
}
