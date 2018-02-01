package com.example.mtalh.hijinnks;

/**
 * Created by CP on 12/20/2017.
 */

public class Model_Search {
    String image_url;
    String name;
    int number_of_invites;
    int follow_or_following_check;


    public Model_Search(String image_url, String name, int number_of_invites, int follow_or_following_check) {
        this.image_url = image_url;
        this.name = name;
        this.number_of_invites = number_of_invites;
        this.follow_or_following_check = follow_or_following_check;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_invites() {
        return number_of_invites;
    }

    public void setNumber_of_invites(int number_of_invites) {
        this.number_of_invites = number_of_invites;
    }

    public int getFollow_or_following_check() {
        return follow_or_following_check;
    }

    public void setFollow_or_following_check(int follow_or_following_check) {
        this.follow_or_following_check = follow_or_following_check;
    }
}
