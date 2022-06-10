package com.example.myapplication.model;

import java.util.List;

public class User {
    private int id;
    private String email;
    private String password;
    private String username;
    private double lat;
    private double lon;
    private List<Pet> pets;

    public User(){

    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String password, String username, double lat, double lon, List<Pet> pets){
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.lat = lat;
        this.lon = lon;
        this.pets = pets;
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
    public void setLatitude(double lat) {
        this.lat = lat;
    }
    public double getLongitude() {
        return lon;
    }
    public void setLongitude(double lon) {
        this.lon = lon;
    }
    public List<Pet> getPets() {
        return pets;
    }
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
