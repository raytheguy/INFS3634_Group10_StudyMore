package com.example.studymore.Multithreader;

import android.os.AsyncTask;

import com.example.studymore.ui.FlashCards.FlashCards;
import com.example.studymore.ui.FlashCards.FlashCardsDatabase;

import java.util.ArrayList;
import java.util.UUID;

public class InsertFlashCardsAsyncTask extends AsyncTask<FlashCards, Integer, String> {
    private AsyncTaskDelegateString delegate;
    private FlashCardsDatabase database;
    private String front;
    private String back;
    private UUID randomUUID;
    private ArrayList<FlashCards> firstTimeAdd;

    public void setDelegate(AsyncTaskDelegateString delegate)  {
        this.delegate = delegate;
    }

    public void setDatabase(FlashCardsDatabase database) {
        this.database = database;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public void setRandomUUID(UUID randomUUID) {
        this.randomUUID = randomUUID;
    }

    public void setFirstTimeAdd(ArrayList<FlashCards> firstTimeAdd) {
        this.firstTimeAdd = firstTimeAdd;
    }

    @Override
    protected String doInBackground(FlashCards... flashCards) {
        // insert flash cards of what the user has entered
        //get from activity
        //if it is not the first time, then add it normally
        if (firstTimeAdd == null) {
            database.flashCardsDao().insert(new FlashCards(randomUUID.toString(), front, back));
        }
        //if it is the first time, add the generic flash cards to the database
        else {
            //add all the generic flash cards if it is the first time
            for (int i =0; i < firstTimeAdd.size();i++){
                database.flashCardsDao().insert(firstTimeAdd.get(i));
            }
        }
        //return message to the user of what it is doing in the background
        return "Inserting FlashCards ";
    }

    @Override
    protected void onPostExecute(String result) {
        //tell the activity that the task has completed
        if (delegate != null) {
            delegate.handleTaskResult(result);
        }
    }
}
