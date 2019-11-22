package com.example.studymore.ui.Cat;

import java.util.ArrayList;

//this class is to get back images of cats from API
public class CatBreedsImage {

    private ArrayList<Cat> breeds;
    private String id;
    private String url;

    public ArrayList<Cat> getBreeds() {
        return breeds;
    }

    public void setBreeds(ArrayList<Cat> breeds) {
        this.breeds = breeds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

