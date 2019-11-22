package com.example.studymore.ui.Quiz;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Score.class}, version = 1)
public abstract class ScoreDatabase extends RoomDatabase {
    public abstract ScoreDao scoreDao();

    private static ScoreDatabase instance;

    public static ScoreDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, ScoreDatabase.class, "scoreDB").build();
            //no running on main thread!!!
        }
        return instance;
    }
}
