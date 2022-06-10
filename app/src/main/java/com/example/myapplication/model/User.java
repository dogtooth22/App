package com.example.myapplication.model;

public class User {
    private int id;
    private String email;
    private String password;
    private String username;
    private double lat;
    private double lon;

    public User(){

    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String password, String username, double lat, double lon){
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double getLatitude() {
        return lat;
    }
    public void setLatitude(float lat) {
        this.lat = lat;
    }
    public double getLongitude() {
        return lon;
    }
    public void setLongitude(float lon) {
        this.lon = lon;
    }

}
