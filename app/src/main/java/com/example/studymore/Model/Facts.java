package com.example.studymore.Model;

import java.util.ArrayList;

//model class for facts
public class Facts {
    //for cat facts
    String[] data;
    //for dog facts
    String[] facts;
    //for random image
    String link;

    public void setData(String[] data) {
        this.data = data;
    }

    public String getData() {
        return data[0];
    }

    public String getFacts() {
        return facts[0];
    }

    public String getLink() {
        return link;
    }

    public void setFacts(String[] facts) {
        this.facts = facts;
    }
}
