package com.example.studymore.ui.FlashCards;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FlashCards.class}, version = 1)
public abstract class FlashCardsDatabase extends RoomDatabase {
    public abstract FlashCardsDao flashCardsDao();

    private static FlashCardsDatabase instance;

    public static FlashCardsDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, FlashCardsDatabase.class, "fcDb")
                    .build();
            //no running on main thread!!!
        }
        return instance;
    }
}
