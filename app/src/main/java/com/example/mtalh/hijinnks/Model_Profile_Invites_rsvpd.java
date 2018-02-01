package com.example.mtalh.hijinnks;

/**
 * Created by mtalh on 23-Nov-17.
 */

public class Model_Profile_Invites_rsvpd {
    String name;
    String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public Model_Profile_Invites_rsvpd(String name, String type) {

        this.name = name;
        this.type = type;
    }
}
