package com.example.myapplication.model;

public class Notification {
    private String userIndex;
    private String message;
    private double lat;
    private double lon;

    public Notification(){

    }

    public Notification(String userIndex, String message, double lat, double lon){
        this.userIndex = userIndex;
        this.message = message;
        this.lat = lat;
        this.lon = lon;
    }

    public String getUserIndex(){return userIndex;}
    public String getMessage(){return message;}
    public double getLatitude(){return lat;}
    public double getLongitude(){return lon;}
}
