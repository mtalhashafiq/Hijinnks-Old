package com.example.mtalh.hijinnks;

public class Model_Comments {

    String name;
    String Date;
    String Comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public Model_Comments(String name, String date, String comment) {

        this.name = name;
        Date = date;
        Comment = comment;
    }
}
