package com.example.getgo.getgo_v1;

public class Course {

    public String id;
    public String name;
    public String credits;
    public String description;

    public Course(String id, String title, String credits, String description) {
        this.id = id;
        this.name = title;
        this.credits = credits;
        this.description = description;
    }

    public String getCourseId() {
        return id;
    }

    public String getCourseTitle() {
        return name;
    }

    public String getCourseDescription() {
        return description;
    }

    public String  getCourseCredits() {
        return credits;
    }

}
