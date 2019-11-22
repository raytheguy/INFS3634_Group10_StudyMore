package com.example.studymore.ActivityDB;

import com.example.studymore.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ActivityDatabase {
        /***
     * Retrieves an Article object using the provided id.
     */
    public static Activity getActivityById(int articleID) {
        return activities.get(articleID);
    }

    /***
     * Return an ArrayList containing all the articles in the database.
     */
    public static ArrayList<Activity> getAllActivity() {
        return new ArrayList<Activity>((List) Arrays.asList(activities.values().toArray()));
    }


    // Create a hashmap database for all the activities in the application
    private static final HashMap<Integer, Activity> activities = new HashMap<>();

    static {
        activities.put(1, new Activity(
                "Cat and Dog Facts",
                R.drawable.cdfacts
        ));
        activities.put(2, new Activity(
                "Cat and Dog Breeds",
                R.drawable.cdbreeds
        ));

        activities.put(3, new Activity(
                "Flash Cards",
                R.drawable.flashcard
        ));

        activities.put(4, new Activity(
                "Quiz Yourself",
                R.drawable.quiz
        ));

        activities.put(5, new Activity(
                "Learning Videos",
                R.drawable.vids
        ));

        activities.put(6, new Activity(
                "Quiz Scores",
                R.drawable.scores
        ));

    }
}

