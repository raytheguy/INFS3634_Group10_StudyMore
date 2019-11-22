package com.example.studymore.ui.Dog;

import com.example.studymore.ui.Cat.Cat;

import java.io.Serializable;
import java.util.ArrayList;

public class Dog implements Serializable{

    //arraylist of dogs
    public ArrayList<Dog> dogs;

    //all the characteristics of a dog (Strings)
    private String id;
    private String bred_for;
    private String breed_group;
    private String life_span;
    private String name;
    private String origin;
    private String temperament;
    private Weight weight;
    private Height height;


    //getters and setters
    //setters are for in case Room is ever used to store dogs
    public ArrayList<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(ArrayList<Dog> dogs) {
        this.dogs = dogs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBred_for() {
        return bred_for;
    }

    public void setBred_for(String bred_for) {
        this.bred_for = bred_for;
    }

    public String getBreed_group() {
        return breed_group;
    }

    public void setBreed_group(String breed_group) {
        this.breed_group = breed_group;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public Weight getWeight() {
        return weight;
    }

    public Height getHeight(){
        return height;
    }

    //for arrayList of heights
    public class Height implements Serializable{
        private String imperial;
        private String metric;

        public String getImperial() {
            return imperial;
        }

        public void setImperial(String imperial) {
            this.imperial = imperial;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }

    //for arrayList of weights
    public class Weight implements Serializable{
        private String imperial;
        private String metric;

        public String getImperial() {
            return imperial;
        }

        public void setImperial(String imperial) {
            this.imperial = imperial;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }
}
