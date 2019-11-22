package com.example.studymore.ActivityDB;

//this is the class for activity and its related views
public class Activity {
    private String activityName;
    private int imageDrawableId;

    public Activity(String activityName, int imageDrawableId) {
        this.activityName = activityName;
        this.imageDrawableId = imageDrawableId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getImageDrawableId() {
        return imageDrawableId;
    }

    public void setImageDrawableId(int imageDrawableId) {
        this.imageDrawableId = imageDrawableId;
    }
}
