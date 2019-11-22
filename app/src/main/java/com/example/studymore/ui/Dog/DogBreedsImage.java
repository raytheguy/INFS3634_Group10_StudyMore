package com.example.studymore.ui.Dog;

import com.example.studymore.ui.Cat.Cat;

import java.util.ArrayList;

//class for dog breed images
public class DogBreedsImage {
    private ArrayList<Dog> breeds;
    private String id;
    private String url;

    public ArrayList<Dog> getBreeds() {
        return breeds;
    }

    public void setBreeds(ArrayList<Dog> breeds) {
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
