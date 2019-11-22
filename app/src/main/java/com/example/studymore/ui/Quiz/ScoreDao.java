package com.example.studymore.ui.Quiz;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

@androidx.room.Dao
public interface ScoreDao {
    //get all the scores back
    @Query("SELECT * FROM Score")
    List<Score> getScores();

    //insert updated version if it exists already
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Score score);

    //method to delete
    @Query("DELETE FROM Score")
    void nukeTable();
}

