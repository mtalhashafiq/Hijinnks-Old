package com.example.mtalh.hijinnks;

/**
 * Created by mtalh on 06-Nov-17.
 */

public class Model_Search_ALL {

    int profile_image;
    int follow_background;
    boolean model_check =false;
    private String profile_name;
    private String number_of_invites;
    private String text_follow;


    public Model_Search_ALL(String profile_name, String number_of_invites, String text_follow, int profile_image, int follow_background) {
        this.profile_name = profile_name;
        this.number_of_invites = number_of_invites;
        this.text_follow = text_follow;
        this.profile_image = profile_image;
        this.follow_background = follow_background;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }

    public String getNumber_of_invites() {
        return number_of_invites;
    }

    public void setNumber_of_invites(String number_of_invites) {
        this.number_of_invites = number_of_invites;
    }

    public String getText_follow() {
        return text_follow;
    }

    public void setText_follow(String text_follow) {
        this.text_follow = text_follow;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(int profile_image) {
        this.profile_image = profile_image;
    }

    public int getFollow_background() {
        return follow_background;
    }

    public void setFollow_background(int follow_background) {
        this.follow_background = follow_background;
    }
}
