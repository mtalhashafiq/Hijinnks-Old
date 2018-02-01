package com.example.mtalh.hijinnks;

/**
 * Created by CP on 12/20/2017.
 */

public class Model_User {

    String username;
    String email;
    int phone_number;
    String password;

    public Model_User(String username, String email, int phone_number, String password) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
