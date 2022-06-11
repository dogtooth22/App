package com.example.myapplication.model;

import android.widget.ImageView;

public class Pet {
    private int id;
    private String petName;
    private String description;
    public ImageView image;

    public Pet(){

    }

    public Pet(int id, String petName, String description, ImageView image){
        this.id = id;
        this.petName = petName;
        this.description = description;
        this.image = image;
    }
}